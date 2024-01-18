package elcodigo;

import java.lang.reflect.Field;
import java.util.Scanner;

import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import jakarta.persistence.OneToMany;

public class Util {

	public static boolean hasMappedBy(Class<?> entityClass, String fieldName) {
		try {
			Field field = entityClass.getDeclaredField(fieldName);
			OneToMany oneToManyAnnotation = field.getAnnotation(OneToMany.class);
			return oneToManyAnnotation != null && oneToManyAnnotation.mappedBy() != null
					&& !oneToManyAnnotation.mappedBy().isEmpty();
		} catch (NoSuchFieldException e) {
			return false;
		}
	}

	public static void init() {
		Scanner scanner = new Scanner(System.in);
		boolean mappedby = hasMappedBy(Padre.class, "hijos");
		System.out.println("Este programa pretende usarse variando una linea en el codigo de la clase Padre: \n\n"
				+ "\t1) La primera vez debes tener este código: @OneToMany" + (mappedby ? "" : "  <---CONFIG ACTUAL")
				+ "\n\t2) La segunda este: @OneToMany(mappedBy=\"padre\")" + (mappedby ? "  <---CONFIG ACTUAL" : ""));

		System.out.println("\n\nPresiona ENTER para continuar\n");
		scanner.nextLine();
		scanner.close();
	}

	public static String otroTexto() {
		return hasMappedBy(Padre.class, "hijos") ? "@OneToMany" : "@OneToMany(mappedBy=\"padre\")";
	}

	public static String textoModif() {
		return hasMappedBy(Padre.class, "hijos") ? "_mpby" : "_xx";
	}

	public static String autoddl() {
		StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		String hbm2ddlAuto;
		try {
			Configuration configuration = new Configuration().configure();
			hbm2ddlAuto = configuration.getProperties().getOrDefault("hibernate.hbm2ddl.auto", "none").toString();
		} finally {
			StandardServiceRegistryBuilder.destroy(registry);
			
		}
		return hbm2ddlAuto;
	}

}
