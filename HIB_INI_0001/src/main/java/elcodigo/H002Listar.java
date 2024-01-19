package elcodigo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class H002Listar {

	public static void main(String[] args) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		System.out.println("\nVoy a acceder a la BBDD y listar Padre e Hijo.\n");
		Util.init();
		listarTodo(sf);
		System.out.println(
				"¿Qué hemos visto?\nSIN mappedBy el padre no conoce a sus hijos pero en cambio desde el hijo si veo al padre\nCambia ahora el valor del mappedBy a "
						+ Util.otroTexto() + " y mira qué pasa\n\n\nFinalizado");

		sf.close();
	}

	public static void listarTodo(SessionFactory sf) {
		Session session = sf.openSession();

		Query<Padre> qryPad = session.createQuery("From Padre", Padre.class);
		Query<Hijo> qryHij = session.createQuery("From Hijo", Hijo.class);

		List<Padre> listaPad = qryPad.getResultList();
		List<Hijo> listaHij = qryHij.getResultList();

		System.out.println("Mostrando Padres e Hijos");
		
		System.out.println("\tVeamos los Padres y sus Hijos");
		for (Padre pater : listaPad) {
			System.out.println("\t***********************\t"+pater.getNombre());
			System.out.println("\t" + pater.toString());
		}
		System.out.println("\t***********************");
		System.out.println("\n\tVeamos los Hijos Sueltos");
		for (Hijo hijus : listaHij)
			System.out.println("\t" + hijus.toString());

		System.out.println("\n");
		session.close();
	}
}