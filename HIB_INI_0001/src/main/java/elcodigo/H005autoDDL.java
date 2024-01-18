package elcodigo;

import java.util.Scanner;

import org.hibernate.SessionFactory;

public class H005autoDDL {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		SessionFactory sf = HibernateUtil.getSessionFactory();

		System.out.println(
				"\n\nJugando con el parámetro hbm2ddl.auto del hibernate.cfg.xml <property name=\"hbm2ddl.auto\">update</property>"
						+ "[El valor actual de hbm2ddl.auto es >" + Util.autoddl() + "<]"
						+ "\n\nSe pretenden ver dos casos diferentes, variando este parámetro:"
						+ "\n\nA) Ver que con este parámetro a 'update', al añadir un atributo a cualquiera de las clases, \nse AÑADEN los campos a la BBDD automáticamente."
						+ "\nEl caso contrario quitar el atributo y que se haga el DROP automatico no está soportado"
						+ "\n\nB) Cambiar el parámetro a 'create-drop' y ver que si no existen las tablas se crean. En cualquier caso Hibernate las BORRA al finalizar el programa\n"
						+ "\n\nPor cierto, sólo por activar el Session Factory ya se han hecho los cambios!!!");

		System.out.println(
				"Comprueba los cambios en la BBDD. Recuerda que si estás en create-drop se eliminan automaticamente las tablas en lo que des al ENTER\n\nPresiona ENTER para finalizar y borrar tablas si en create-drop\n");
		scanner.nextLine();
		scanner.close();

		sf.close();
	}

}