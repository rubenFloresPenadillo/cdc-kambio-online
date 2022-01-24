package hibernate.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import loggerUtil.LoggerUtil;


/**
 * @author Deepak Kumar 
 * Web: http://www.roseindia.net
 */
public class HibernateUtil {
	
//	private static final SessionFactory sessionFactory;
//
//	static {
//		try {
//			StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
//			Metadata metaData = new MetadataSources(standardRegistry).getMetadataBuilder().build();
//			sessionFactory = metaData.getSessionFactoryBuilder().build();
//		} catch (Throwable th) {
//			System.err.println("Enitial SessionFactory creation failed" + th);
//			throw new ExceptionInInitializerError(th);
//
//		}
//	}
//
//	public static SessionFactory getSessionFactory() {
//		return sessionFactory;
//	}
	
	private static StandardServiceRegistry registry;
	  private static SessionFactory sessionFactory;

	  public static SessionFactory getSessionFactory() {
	    if (sessionFactory == null) {
	      try {
	        // Create registry
	        registry = new StandardServiceRegistryBuilder()
	            .configure()
	            .build();

	        // Create MetadataSources
	        MetadataSources sources = new MetadataSources(registry);

	        // Create Metadata
	        Metadata metadata = sources.getMetadataBuilder().build();

	        // Create SessionFactory
	        sessionFactory = metadata.getSessionFactoryBuilder().build();

	      } catch (Exception e) {
	        e.printStackTrace();
	        LoggerUtil.getInstance().getLogger().error("Error en getSessionFactory");
	        LoggerUtil.getInstance().getLogger().error(e.getMessage());
	        LoggerUtil.getInstance().getLogger().error(e);
	        if (registry != null) {
	          StandardServiceRegistryBuilder.destroy(registry);
	        }
	      }
	    }
	    return sessionFactory;
	  }

	  public static void shutdown() {
	    if (registry != null) {
	      StandardServiceRegistryBuilder.destroy(registry);
	    }
	  }
}
