package util;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class UtilidadHibernate {

	
    private static SessionFactory sessionFactory;// = buildSessionFactory();
    private static Session sesion = null;
    
       
    private static SessionFactory buildSessionFactory() {
        try {
        	System.out.println("Iniciando la creación de la SessionFactory.....");
            // Create the SessionFactory from hibernate.cfg.xml

        	SessionFactory sf = new Configuration().configure().buildSessionFactory();
        	System.out.println("Creación de la SessionFactory finalizada.....");
        	return sf;
        }
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    
    public static Session getSesion(){
    	if(UtilidadHibernate.sesion==null){    		
    		if(UtilidadHibernate.sessionFactory==null){
    			UtilidadHibernate.sessionFactory = UtilidadHibernate.buildSessionFactory();
    		}
    		UtilidadHibernate.sesion = UtilidadHibernate.sessionFactory.openSession();
    		UtilidadHibernate.getSesion().beginTransaction();// Inicia ya la transaccion al crear la sesion

    	}
    	return UtilidadHibernate.sesion;    	
    }

    
 
    public static void commit(){
    	UtilidadHibernate.getSesion().getTransaction().commit();
    	UtilidadHibernate.getSesion().beginTransaction();// Inicia la transaccion siguiente
    }

    public static void rollBack(){
    	UtilidadHibernate.getSesion().getTransaction().rollback();
    	UtilidadHibernate.getSesion().beginTransaction();// Inicia la transaccion siguiente
    }    
    
}