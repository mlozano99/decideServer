package carga;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import util.UtilidadHibernate;
import es.dabdm.decide.modelo.Comunidad;
import es.dabdm.decide.modelo.ComunityManager;
import es.dabdm.decide.modelo.Encuesta;
import es.dabdm.decide.modelo.PosicionGPS;
import es.dabdm.decide.modelo.Pregunta;
import es.dabdm.decide.modelo.RespuestaPosible;
import es.dabdm.decide.modelo.Suscripcion;
import es.dabdm.decide.modelo.Usuario;

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
        String descripcion = "Una comunidad es un grupo o conjunto de individuos, seres humanos, o de animales (o de cualquier otro tipo de vida) que comparten elementos en común, tales como un idioma, costumbres, valores, tareas, visión del mundo, edad, ubicación geográfica (un barrio por ejemplo), estatus social, roles.";
		PosicionGPS gps;
		for(int i = 0;i<20;i++){
			gps = new PosicionGPS(i, i);

			sesion.save(new Comunidad("comunidad "+i, "comunidad "+i + " " + descripcion,"twitter", gps, "A", "P", 200+i, managers.get( (i % managers.size()) ) ));
		}
		UtilidadHibernate.commit();
	}	
	
	public void crearSuscripciones(){
		Session sesion = UtilidadHibernate.getSesion();

		List<Comunidad> comunidades = sesion.createQuery("FROM Comunidad").list();
		List<Usuario> usuarios = sesion.createQuery("FROM Usuario").list();
		
		Comunidad comunidad;
		Usuario usuario;
		
	
		for(int i = 0;i<100;i++){
			usuario = usuarios.get(  ( i % usuarios.size() )  );	
			comunidad = comunidades.get(  ( i % comunidades.size() )  );			
			sesion.save(new  Suscripcion(comunidad, usuario, new Date()));
		}
		
		UtilidadHibernate.commit();
	}
	
	
	public void crearEncuestas(){
		Session sesion = UtilidadHibernate.getSesion();

		List<Comunidad> comunidades = sesion.createQuery("FROM Comunidad").list();
		Calendar calendar = Calendar.getInstance();
		calendar.set(2013, 3, 5);
		Comunidad comunidad;
		for(int i = 0;i<100;i++){
			
			comunidad = comunidades.get(  ( i % comunidades.size() )  );			
			sesion.save(new Encuesta(calendar.getTime(), comunidad));
		}
		UtilidadHibernate.commit();
	}
	 
	@SuppressWarnings("unchecked")
	public void crearEncuestasUsuarios(){
		Session sesion = UtilidadHibernate.getSesion();

		List<Encuesta> encuestas = sesion.createQuery("FROM Encuesta").list();
		List<Usuario> usuarios = sesion.createQuery("FROM Usuario").list();
		Encuesta encuesta;
				
		List<Encuesta> encuestasUsuario;
		int j = 0;
		for(Usuario usuario : usuarios){			
				j++;
				encuestasUsuario = new ArrayList<Encuesta>();
				for(int i = 0;i<10;i++){
					j++;
					encuesta = encuestas.get( j % encuestas.size() );
				    encuestasUsuario.add(encuesta);			    
				}
				usuario.setEncuestas(encuestasUsuario);     	
				sesion.save(usuario);
				UtilidadHibernate.commit();	
		}
		UtilidadHibernate.commit();
	}	
	
	public void crearPreguntas(){
		Session sesion = UtilidadHibernate.getSesion();

		List<Encuesta> encuestas = sesion.createQuery("FROM Encuesta").list();
	
		Pregunta pregunta;
		RespuestaPosible respuesta;
		for(Encuesta e : encuestas){
			pregunta = new Pregunta(e, e.getComunidad(), "Texto de la pregunta de la comunidad "+e.getComunidad().getNombre()+ " y fecha limiete "+e.getFechaLimite());
			sesion.save(pregunta);
			UtilidadHibernate.commit();
			sesion.refresh(pregunta);			
			sesion.save( new RespuestaPosible(pregunta, "Si (id pregunta "+ pregunta.getIdPregunta()));
			sesion.save( new RespuestaPosible(pregunta, "No (id pregunta "+ pregunta.getIdPregunta()));
			sesion.save( new RespuestaPosible(pregunta, "Ns/Nc (id pregunta "+ pregunta.getIdPregunta()));
			UtilidadHibernate.commit();
        }
		
	}
	 
	
	
	public void borrarBBDD(){
		Session sesion = UtilidadHibernate.getSesion();		
		
		sesion.createSQLQuery("DELETE FROM RESPUESTASUSUARIO").executeUpdate();
		sesion.createSQLQuery("DELETE FROM RESPUESTASPOSIBLES").executeUpdate();
		sesion.createSQLQuery("DELETE FROM PREGUNTAS").executeUpdate();		
		sesion.createSQLQuery("DELETE FROM ENCUESTASUSUARIOS").executeUpdate();
		sesion.createSQLQuery("DELETE FROM ENCUESTAS").executeUpdate();		
		sesion.createSQLQuery("DELETE FROM SUSCRIPCIONES").executeUpdate();
		sesion.createSQLQuery("DELETE FROM USUARIOS").executeUpdate();
		sesion.createSQLQuery("DELETE FROM comunidades").executeUpdate();
		sesion.createSQLQuery("DELETE FROM managers").executeUpdate();
		UtilidadHibernate.commit();
	}
	/**
	 *  Este incluye todos los demas, en el orden correcto para crear los datos
	 */	
	public void crearBBDDCompleta(){
	  	crearComunityManager();
		crearComunidad();
		crearUsuarios();
		crearSuscripciones();		
        crearEncuestas();
        crearEncuestasUsuarios();
        crearPreguntas();
	}
	

	

	public static void main(String[] args) {

        
		Test cargaBBDD = new Test();	
		cargaBBDD.borrarBBDD();
		cargaBBDD.crearBBDDCompleta(); //Solo llamarlo la primera vez, descomentarlo
	 	
//	  	cargaBBDD.crearComunityManager();
//		cargaBBDD.crearComunidad();
//		cargaBBDD.crearUsuarios();
//		cargaBBDD.crearSuscripciones();		
//      cargaBBDD.crearEncuestas();
//      cargaBBDD.crearPreguntas();
	}
	
}
