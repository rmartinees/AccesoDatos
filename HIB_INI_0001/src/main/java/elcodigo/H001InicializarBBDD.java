package elcodigo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;

public class H001InicializarBBDD {

	public static void main(String[] args) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		System.out.println("Voy a Inicializar mi BBDD con unos Padre e Hijos.");
		Session session = sf.openSession();
		session.beginTransaction();
		Padre padre;
		  
		for (int i = 1; i <= 3; ++i) {
			System.out.println("Generando Padre" + i);
			session.persist(padre = new Padre("Papa" + i));
			int lim = (int) (Math.random() * 3.0) + 1;// Siempre tendremos hijos

			for (int j = 1; j <= lim; ++j) {
				System.out.println("\tHijo " + j);
				session.persist(new Hijo("Hijo" + j + "_P" + i, padre));
			}
		}
		session.close();

		sf.close();
		System.out.println("Generación Finalizada");
	}

}
