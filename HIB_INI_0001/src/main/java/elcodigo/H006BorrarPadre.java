package elcodigo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class H006BorrarPadre {

	public static void main(String[] args) {

		SessionFactory sf = HibernateUtil.getSessionFactory();

		System.out.println("\n\nVeamos como se comporta el borrado segun el parametro de cascade del hibernate.cfg.xml"
				+ "\nPrueba poniendo al @OneToMany(mappedBy=\"padre\", cascade={CascadeType.ALL}) en la clase padre, o bien elimina el cascade\n");
		Util.printCascadeTypes(Padre.class);

		Session session = sf.openSession();

		Query<Padre> qryPad = session.createQuery("From Padre ", Padre.class);
		List<Padre> listaPad = qryPad.getResultList();

		System.out.print("Registros Iniciales: ");
		H002Listar.listarTodo(sf);
		Transaction trans = session.beginTransaction();
		try {
			session.remove(listaPad.get(0));
			trans.commit();
		} catch (Exception ConstraintViolationException) {
			System.out.println(
					"\n\n*************************************Error al Borrar por ConstraintViolationException");
		}

		session.close();
		System.out.print("Registros Modificados: ");
		H002Listar.listarTodo(sf);
		System.out.println(
				"¿Qué hemos visto?\nEn función del valor del CASCADE puedo, o no, borrar un padre y sus hijos de una tacada");
		sf.close();
	}

}