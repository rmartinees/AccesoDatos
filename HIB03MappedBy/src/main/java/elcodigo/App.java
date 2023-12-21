package elcodigo;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class App {
	// private static final Logger logger = LoggerFactory.getLogger(App.class);
	public static void main(String[] args) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		///////////////////////////////////////////////////////////////////
		// Los Create TABLE son diferentes, en funcion de que haya o no mappedBy
		// en esta linea de Padre.java: private List<Hijo> hijos = new
		// ArrayList<Hijo>();
		//
		// Si lo hay, solo creará las tablas padre e hijo, donde el hijo tendrá el
		// identificador del padre
		//
		// Si No está, tambien creará una tabla Padre_hijos intermedia para guardar los
		// id de ambas tablas CUANDO ALMACENEMOS la clase Padre Y su COLLECTION!!
		////////////////////////////////////////////////////////////////////////////

		// Independientemente del mappedBy, estas modificaciones solo modifican las tablas
		// de Padres e Hijos
		Padre padre = new Padre("Papa 1");
		Hijo hijo1 = new Hijo("Primer Hijo de Papa 1", padre);
		Hijo hijo2 = new Hijo("Segundo Hijo de Papa 1", padre);
		session.persist(padre); // Estas inserciones no insertan nada en Padre_Hijo
		session.persist(hijo1);
		session.persist(hijo2);

		// Si NO tengo mappedBy la inserción del Padre provocará que se inserte 
		// en el Padre, en el Hijo, y la relacion entre ambas tablas se mantendrá 
		//en la tabla Padres_hijos
		padre = new Padre("Papa 2");
		ArrayList<Hijo> e = new ArrayList<>();
		e.add(new Hijo("Primer hijo de Papa 2", padre));
		padre.setHijos(e);
		session.persist(padre); // Esta insercion es la que inserta los valores en Padre_Hijo, pero solo para
								// este registro

		session.getTransaction().commit();

		Query<Hijo> q = session.createQuery("From Hijo", Hijo.class);
		List<Hijo> listaResult = q.list();
		System.out.println("num de hijos:" + listaResult.size());
		for (Hijo sig : listaResult) {
			System.out.println("Padre:  " + sig.getPadre().getNombre() + "[" + sig.getPadre().getId() + "] -->  Hijo: "
					+ sig.getNombre() + "[" + sig.getId() + "]");
		}
	}
}