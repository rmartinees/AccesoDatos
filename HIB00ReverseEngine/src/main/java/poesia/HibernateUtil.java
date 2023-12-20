package poesia;


import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory sessionFactory;
    private static final StandardServiceRegistry serviceRegistry;

    static {
        try {
            // Crear la configuración de Hibernate
            Configuration configuration = new Configuration().configure();

            // Crear el registro de servicios
            serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();

            // Crear la SessionFactory
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            System.err.println("Error al crear la SessionFactory: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    // Método para cerrar la SessionFactory al cerrar la aplicación
    public static void shutdown() {
        StandardServiceRegistryBuilder.destroy(serviceRegistry);
    }
}