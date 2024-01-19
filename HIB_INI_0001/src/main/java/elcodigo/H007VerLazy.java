package elcodigo;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class H007VerLazy {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.println(
				"\nEste programa mira el comportamiento de una query en Hibernate. Necesita tener fetch=FetchType.LAZY o fetch=FetchType.EAGER puesto en el mappedby de Padre,\n"
						+ " y para ver el resultado, que en hibernate.cfg tengas el ver SQl activado\n Comprobándolo...\n");

		if (!Util.isShowSqlActivated())
			System.out.println(
					"ERROR ----> No has activado <property name=\"show_sql\">true</property> en hibernate.cfg");
		else {

			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session session = sf.openSession();

			List<Padre> listaPad = session.createQuery("From Padre  ", Padre.class).list();

			System.out.println("\n\n" + Util.printFetchType(Padre.class)
					+ "\nPresiona ENTER para acceder a la BBDD y listar un único Padre y sus Hijos.\nEl programa aun NO ha accedido a ningún objeto Java\n");
			scanner.nextLine();

			listaPad.get(1);

			System.out.println("\t*******************\n\t" + listaPad.get(0).toString() + "\n\t*******************");

			session.close();
			System.out.println("\n\n*********************************************************\n¿Qué hemos visto?\nCon Eager, al definir la listaPad ya leemos todos los datos, \n"
					+ "En cambio con LAZY sólo lee cuando accedemos a listaPad.get(1)\nRecuerda que LAZY es el valor por defecto\n"+
					"*********************************************************");

			sf.close();
		}
		scanner.close();

	}
}