package p2;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class ProgP2 {

	public static void main(String[] args) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		System.out.println("Voy a Inicializar mi BBDD con unos Departamentos y Empleados.");
		Session session = sf.openSession();
		session.beginTransaction();
		List<Departamento> dpto = new ArrayList<Departamento>();
		List<Empleado> emp = new ArrayList<Empleado>();
		for (int i = 1; i <= 3; ++i) {
			System.out.println("Generando Dpto " + i);
			dpto.add(new Departamento("Dpto" + i));
			session.persist(dpto.get(i - 1));
		}
		for (int i = 1; i <= 9; ++i) {
			System.out.println("Generando   Emp" + i);
			emp.add(new Empleado("Emp" + i));
			session.persist(emp.get(i - 1));
		}
		try {
			session.getTransaction().commit();
		} catch (Exception SQLIntegrityConstraintViolationException) {
			System.out.println("--->> ERROR ACCESO A DATOS Integrity Constraint Violation al generar empleados y deptos\n\n");
			session.getTransaction().rollback();
		}

		for (int i = 0; i < 3; ++i) {
			session.beginTransaction();
			for (int j = 0; j < 3; ++j) {
				System.out.println("Dpto " + (i+1) + " Emp " + (j+1));
				session.persist(new DepartamentoEmpleado(dpto.get(i), emp.get(j + i * 3)));
			}
			
			try {
				session.getTransaction().commit();
			} catch (Exception SQLIntegrityConstraintViolationException) {
				System.out.println("--->> ERROR ACCESO A DATOS Integrity Constraint Violation al grabar depto "+(i+1)+"\n\n");
				session.getTransaction().rollback();
			}
		}
		session.close();// Si no cierro la sesion no veo desde Dpto sus hijos
		session = sf.openSession();
		Query<Departamento> qryDpto = session.createQuery("From Departamento ", Departamento.class);
		List<Departamento> listaDpto = qryDpto.getResultList();
		System.out.println("Contenido de los Departamentos: ");
		for (int i=0; i<listaDpto.size(); ++i) 
			System.out.println("Depto " + (i+1) +": " + listaDpto.get(i).toString());
		
		System.out.println("\nPrograma Finalizado");
		sf.close();
	}
}
