package elcodigo;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Grabar {
	// private static final Logger logger = LoggerFactory.getLogger(App.class);
	public static void main(String[] args) {
		System.out.println("Grabando en la BBDD");
		////////////////////////////////////////////////////////////////////////////
		// En funcion de que haya o no mappedBy los Create TABLE son diferentes, y las
		// lecturas de Padre dan resultados diferentes
		//
		// --> Si hay mappedBy, solo creará las tablas padre e hijo, donde el hijo
		// tendrá el identificador del padre.
		// --------> Si leo el registro desde la clase Padre NO veré a sus hijos!!!
		//
		// --> Si no hay mappedBy tambien creará una tabla Padre_hijos intermedia para
		// guardar los id de ambas tablas cuando almacenamos DESDE la clase Padre.
		// --------> Si leo el registro desde la clase Padre tambien veré a los hijos
		////////////////////////////////////////////////////////////////////////////

		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
						
		////////////////////////////////////////////////////////////////////////////
		// Independientemente del mappedBy, estas modificaciones solo modifican las
		// tablas de Padres e Hijos, NUNCA modifa la Padres_hijos
		////////////////////////////////////////////////////////////////////////////
		Padre padre = new Padre("Papa 1 (grabado desde padre)");
		Hijo hijo1 = new Hijo("1º hijo/Papa 1 (grabado desde hijo)", padre);
		session.persist(padre); // Estas inserciones no insertan nada en Padre_Hijo
		session.persist(hijo1);

		////////////////////////////////////////////////////////////////////////////
		// Si NO hay mappedBy tambien se insertarán registros en la tabla Padres_Hijos
		////////////////////////////////////////////////////////////////////////////
		padre = new Padre("Papa 2 (grabado desde padre)");
		ArrayList<Hijo> e = new ArrayList<>();
		e.add(new Hijo("1º hijo/Papa 2 (grabado desde padre)", padre));
		e.add(new Hijo("2º hijo/Papa 2 (grabado desde padre)", padre));
		padre.setHijos(e);
		session.persist(padre);
		padre = new Padre("Papa 3 (grabado desde padre)");
		e = new ArrayList<>();
		e.add(new Hijo("1º hijo/Papa 3 (grabado desde padre)", padre));
		e.add(new Hijo("2º hijo/Papa 3 (grabado desde padre)", padre));
		e.add(new Hijo("3º hijo/Papa 3 (grabado desde padre)", padre));
		padre.setHijos(e);
		session.persist(padre);
	
		//Este código no borraria el registro en la BBDD con mappedBy porque dijimos que todo lo gestiona Hijo
		//Borro el segundo hijo de papa3
		//padre.getHijos().remove(1);
		//System.out.println(padre.toString());
		//session.merge(padre);   
		
		//Este si:
		//Hijo h = padre.getHijos().get(1);
	    //session.remove(h);
	    //padre.getHijos().remove(1);
		
	   	//Borro el 3er hijo de papa3
	    //	 Hijo hi = padre.getHijos().get(1);
	    //	 Hijo entityToDelete = session.get(Hijo.class, hi.getId());
		// session.remove(entityToDelete);
		  
		session.getTransaction().commit();

		////////////////////////////////////////////////////////////////////////////
		// Si no pongo esto, que no veo por qué ponerlo, no me lista bien el primer padre cuando mappedBy está puesto
		// ¿Bug de mi version de Hibernate?
		////////////////////////////////////////////////////////////////////////////
		session.close();
		session  = HibernateUtil.getSessionFactory().openSession();
		 
		////////////////////////////////////////////////////////////////////////////
		// Si no hay mapped by, desde Padre NO puedo ver el hijo de Papa1, no hay ruta
		// de acceso
		////////////////////////////////////////////////////////////////////////////
		System.out.println("***************** IMPRIMIENDO DESDE PADRE **********************");
		Query<Padre> qp = session.createQuery("From Padre", Padre.class);
		List<Padre> listaPadre = qp.getResultList();
		System.out.println("num de Padres:" + listaPadre.size());
		for (Padre sig : listaPadre) {
			System.out.println("***********> Padre:  " + sig.getNombre() + "[" + sig.getId() + "] Nº Hijos = "
					+ sig.getHijos().size());
			for (Hijo hij : sig.getHijos())
				System.out.println("***********>  -->  Hijo: " + hij.getNombre() + "[" + hij.getId() + "]");
		}

		////////////////////////////////////////////////////////////////////////////
		// Desde hijo siempre lo veo todo bien, con o sin mappedBy ya que hijo mapea
		// la relacion (al ser many to one) la variable padre
		////////////////////////////////////////////////////////////////////////////
		System.out.println("\n\n***************** IMPRIMIENDO DESDE HIJO **********************");
		Query<Hijo> q = session.createQuery("From Hijo", Hijo.class);
		List<Hijo> listaResult = q.getResultList();
		System.out.println("num de hijos:" + listaResult.size());
		for (Hijo sig : listaResult) {
			System.out.println("***********> Padre:  " + sig.getPadre().getNombre() + "[" + sig.getPadre().getId()
					+ "] -->  Hijo: " + sig.getNombre() + "[" + sig.getId() + "]");
		}

		session.close();
		
		sf.close();

		System.out.println("Finalizado");
	}
}