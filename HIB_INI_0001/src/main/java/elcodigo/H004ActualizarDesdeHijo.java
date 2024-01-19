package elcodigo;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import jakarta.persistence.OneToMany;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class H004ActualizarDesdeHijo {

	public static void main(String[] args) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		System.out.println(
				"\nVoy a acceder a la BBDD y modificar el Nombre de cada hijo y de su Padre DESDE LA CLASE HIJO, para ver cómo cambia el comportamiento segun el valor del mappedBy\n");
		Util.init();
		Session session = sf.openSession();

		Query<Hijo> qryHij = session.createQuery("From Hijo ", Hijo.class);
		List<Hijo> listaHij = qryHij.getResultList();

		System.out.println("Registros Iniciales: \n");
		H002Listar.listarTodo(sf);
		Transaction trans = session.beginTransaction();

		for (Hijo hij : listaHij) {
			hij.setNombre(hij.getNombre() + "_02");

			Padre pater = hij.getPadre();
			pater.setNombre(pater.getNombre() + Util.textoModif());
			hij.setPadre(pater);
			
			session.merge(hij);
		}
		trans.commit();
		session.close();
		System.out.println("Registros Modificados: \n");
		H002Listar.listarTodo(sf);
		System.out.println(
				"¿Qué hemos visto?\nSIN mappedBy hijos funciona igual que con el mappedBy \nCambia ahora el valor del mappedBy a "
						+ Util.otroTexto() + " y compruébalo\n\n\nFinalizado");
		sf.close();
	}
}