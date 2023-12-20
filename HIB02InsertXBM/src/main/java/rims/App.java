package rims;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;


public class App {
	//private static final Logger logger = LoggerFactory.getLogger(App.class);
	public static void main(String[] args) {

		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();

		Departamento departamento = new Departamento("java");
		session.persist(departamento);
 
		session.getTransaction().commit();

		Query<Departamento> q = session.createQuery("From Departamento", Departamento.class);

		List<Departamento> listaResult = q.list();
		System.out.println("num de departamentos:" + listaResult.size());
		for (Departamento sig : listaResult) {
			System.out.println("Siguiente empleado: " + sig);
		}

	}

}