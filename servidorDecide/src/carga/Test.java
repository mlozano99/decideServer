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

		for(int i = 0;i<10;i++){
			sesion.save(new ComunityManager("nombre apellido "+i));
		}
		UtilidadHibernate.commit();
	}
	
	public void crearComunidad(){
		Session sesion = UtilidadHibernate.getSesion();

		PosicionGPS gps;
		for(int i = 0;i<10;i++){
			gps = new PosicionGPS(i, i);
			sesion.save(new Comunidad("comunidad "+i, "twitter", gps, "A", "P", 200+i, (ComunityManager) sesion.get(ComunityManager.class, i)));
		}
		UtilidadHibernate.commit();
	}	
	
	/**
	 *  Este incluye todos los demas, en el orden correcto para crear los datos
	 */	
	public void crearBBDDCompleta(){

		//......
	}
	
	
	/**
	 * Se podría hacer este para que borre todos los datos
	 */
	public void borraBBDDCompleta(){
		// HACERLO o hacer un script SQL
	}
	

	public static void main(String[] args) {

        
		Test cargaBBDD = new Test();	
		//cargaBBDD.borrarBBDDCompleta();
		//cargaBBDD.crearBBDDCompleta(); //Solo llamarlo la primera vez, descomentarlo
		cargaBBDD.crearUsuarios();
		cargaBBDD.crearComunityManager();
		cargaBBDD.crearComunidad();
        //cargaBBDD.compruebaEntidades();	


	}
	
}
