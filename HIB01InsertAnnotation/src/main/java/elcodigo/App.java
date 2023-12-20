package elcodigo;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {
	//private static final Logger logger = LoggerFactory.getLogger(App.class);
	public static void main(String[] args) {

		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();

		Departamento departamento = new Departamento("java");
		session.persist(departamento);

		session.persist(new Empleado("Jakab Gipsz", departamento));
		session.persist(new Empleado("Capitan Nemo", departamento));

		session.getTransaction().commit();

		Query<Empleado> q = session.createQuery("From Empleado", Empleado.class);

		List<Empleado> listaResult = q.list();
		System.out.println("num de empleados:" + listaResult.size());
		for (Empleado sig : listaResult) {
			System.out.println("Siguiente empleado: " + sig);
		}

	}

}