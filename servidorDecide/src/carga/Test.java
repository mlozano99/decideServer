package carga;

import java.util.Date;
import java.util.List;
import es.dabdm.decide.modelo.*;

import org.hibernate.Session;

import util.UtilidadHibernate;

public class Test {

	public void compruebaEntidades(){
		Session sesion = UtilidadHibernate.getSesion();
		
		sesion.createQuery("FROM es.dabdm.decide.modelo.Comunidad").list();
		sesion.createQuery("FROM es.dabdm.decide.modelo.ComunityManager").list();
		sesion.createQuery("FROM es.dabdm.decide.modelo.Encuesta").list();
		sesion.createQuery("FROM es.dabdm.decide.modelo.Pregunta").list();
		sesion.createQuery("FROM es.dabdm.decide.modelo.RespuestaPosible").list();
		sesion.createQuery("FROM es.dabdm.decide.modelo.RespuestaUsuario").list();
		sesion.createQuery("FROM es.dabdm.decide.modelo.Suscripcion").list();
		sesion.createQuery("FROM es.dabdm.decide.modelo.Usuario").list();
	}

	
	public void crearUsuarios(){
		Session sesion = UtilidadHibernate.getSesion();

		for(int i = 0;i<10;i++){
			sesion.save(new Usuario("email"+i+"@email.es", null, Integer.toString( 963600000+i ), "nombre apellido "+i, "N"));
		}
		UtilidadHibernate.commit();
	}
	 
	public void crearComunityManager(){
		Session sesion = UtilidadHibernate.getSesion();

		for(int i = 0;i<5;i++){
			sesion.save(new ComunityManager("nombre apellido "+i));
		}
		UtilidadHibernate.commit();
	}
	
	public void crearComunidad(){
		Session sesion = UtilidadHibernate.getSesion();

		List<ComunityManager> managers = sesion.createQuery("FROM ComunityManager").list();

		PosicionGPS gps;
		for(int i = 0;i<20;i++){
			gps = new PosicionGPS(i, i);

			sesion.save(new Comunidad("comunidad "+i, "twitter", gps, "A", "P", 200+i, managers.get( (i % managers.size()) ) ));
		}
		UtilidadHibernate.commit();
	}	
	
	
	public void borrarBBDD(){
		Session sesion = UtilidadHibernate.getSesion();		
		sesion.createSQLQuery("DELETE FROM comunidades").executeUpdate();
		sesion.createSQLQuery("DELETE FROM managers").executeUpdate();
		UtilidadHibernate.commit();
	}
	/**
	 *  Este incluye todos los demas, en el orden correcto para crear los datos
	 */	
	public void crearBBDDCompleta(){

		//......
	}
	

	

	public static void main(String[] args) {

        
		Test cargaBBDD = new Test();	
		cargaBBDD.borrarBBDD();
		//cargaBBDD.crearBBDDCompleta(); //Solo llamarlo la primera vez, descomentarlo
		cargaBBDD.crearUsuarios();
		cargaBBDD.crearComunityManager();
		cargaBBDD.crearComunidad();
        //cargaBBDD.compruebaEntidades();	


	}
	
}
