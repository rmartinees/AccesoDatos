package elcodigo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.query.Query;

import jakarta.persistence.OneToMany;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class H002Leer {

	public static void main(String[] args) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		System.out.println("\nVoy a acceder a la BBDD y listar Padre e Hijo.\n");
		Util.init();
		listarTodo(sf);
		System.out.println(
				"¿Qué hemos visto?\nSIN mappedBy el padre no conoce a sus hijos pero en cambio desde el hijo si veo al padre\nCambia ahora el valor del mappedBy a "
						+ Util.otroTexto() + " y mira qué pasa\n\n\nFinalizado");

		sf.close();
	}

	public static void listarTodo(SessionFactory sf) {
		System.out.println("\n\nMostrando Padres e Hijos");
		Session session = sf.openSession();
		session.beginTransaction();

		Query<Padre> qryPad = session.createQuery("From Padre", Padre.class);
		Query<Hijo> qryHij = session.createQuery("From Hijo", Hijo.class);

		List<Padre> listaPad = qryPad.getResultList();
		List<Hijo> listaHij = qryHij.getResultList();

		System.out.println("Veamos los Padres y sus Hijos");
		int j = 0;
		for (Padre pater : listaPad) {
			System.out.println(pater.toString());
		}

		System.out.println("\nVeamos los Hijos Sueltos");
		j = 0;
		for (Hijo hijus : listaHij) {
			System.out.println(hijus.toString());
		}
		System.out.println("\n");
		session.close();
	}
}