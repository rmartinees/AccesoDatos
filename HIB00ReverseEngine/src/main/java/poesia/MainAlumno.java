package poesia;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class MainAlumno {
	
	public static void main(String[] args) {
		// Obtengo la sesionFactory existente
		SessionFactory sesionfactory = HibernateUtil.getSessionFactory();

		// Creo mi sesion
		Session sesion = sesionfactory.openSession();

		// Creo una transaccion en mi sesion
		Transaction tx = sesion.beginTransaction();

		System.out.println("Voy a insertar una fila en Alumnos");

		Alumnos alu = new Alumnos(44, "Ricardito", "ESP", 62);
		sesion.persist(alu);
		tx.commit();
		sesion.close();

		HibernateUtil.shutdown();
	}
}
