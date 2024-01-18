package elcodigo;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import jakarta.persistence.OneToMany;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class H003ActualizarDesdePadre {

	public static void main(String[] args) {
		Hijo hij;
		System.out.println(
				"\nVoy a acceder a la BBDD y modificar un un Padre y su primer Hijo DESDE LA CLASE PADRE, para ver cómo cambia el comportamiento segun el valor del mappedBy\n");
		Util.init();
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();

		Query<Padre> qryPad = session.createQuery("From Padre ", Padre.class);
		List<Padre> listaPad = qryPad.getResultList(); 
		
		System.out.println("Registros Iniciales: \n");
		H002Leer.listarTodo(sf);
		Transaction trans = session.beginTransaction();
		for (Padre pater : listaPad) {
			pater.setNombre(pater.getNombre() + "_01");
			if (pater.getHijos().isEmpty())
				System.out.println("No hay hijos, no puedo modificarlos");
			else {
				hij = pater.getHijos().get(0);
				hij.setNombre(hij.getNombre() + Util.textoModif());
				pater.getHijos().set(0, hij);
			}
			session.merge(pater);
		}

		trans.commit();
		session.close();
		System.out.println("Registros Modificados: \n");
		H002Leer.listarTodo(sf);
		System.out.println(
				"¿Qué hemos visto?\nSIN mappedBy el padre no conoce a sus hijos y por tanto no los puedo cambiar, \nEn cambio con mappedBy desde el padre puedo modificar todo \nCambia ahora el valor del mappedBy a "
						+ Util.otroTexto() + " y mira qué pasa\n\n\nFinalizado");
		sf.close();
	}
}