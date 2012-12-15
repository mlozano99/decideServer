package es.dabdm.decide.servicio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import util.UtilidadHibernate;
import es.dabdm.decide.modelo.Comunidad;
import es.dabdm.decide.modelo.Pregunta;
import es.dabdm.decide.modelo.RespuestaPosible;
import es.dabdm.decide.modelo.RespuestaUsuario;
import es.dabdm.decide.modelo.Suscripcion;
import es.dabdm.decide.modelo.Usuario;

public class ServicioConsultas {

	private static ServicioConsultas srvConsultas;

	public static ServicioConsultas getServicioConsultas(){
	    if(ServicioConsultas.srvConsultas==null){
	    	ServicioConsultas.srvConsultas=new ServicioConsultas();
	    }
		return ServicioConsultas.srvConsultas;	
	}
	
	
	
	
	/* **************************************************************************************************************************************
	**************************************************************** COMUNIDADES ***********************************************************
     ****************************************************************************************************************************************/	
	public List<Comunidad> getComunidades(){
		Session sesion = UtilidadHibernate.getSesion();
		return sesion.createQuery("FROM es.dabdm.decide.modelo.Comunidad").list();
	}
	
	
	public List<Comunidad> getComunidades(String email, String latitud, String logitud){
		Session sesion = UtilidadHibernate.getSesion();
		
		@SuppressWarnings("unchecked")
		List<Comunidad> comunidades= sesion.createQuery("FROM es.dabdm.decide.modelo.Comunidad ORDER BY nombre" )   	
									       .list();
		
		List<Comunidad> comunidadesSuscritas = getComunidadSuscritas(email);
		for(Comunidad c :comunidades){
		    if(comunidadesSuscritas.contains(c)){
			     c.setSuscrito("S");	
		    }else{
		    	c.setSuscrito("N");
		    }
		}
		
		return comunidades;
		
	}
	
	public List<Comunidad> getComunidadSuscritas(String email){
		Session sesion = UtilidadHibernate.getSesion();
		return sesion.createQuery("SELECT comunidad d" +
				                   " FROM es.dabdm.decide.modelo.Suscripcion " +
				                  " WHERE usuario.email = :email ")
				     .list();
	}
	
	
	
	
	
	/* **************************************************************************************************************************************
	**************************************************************** USUARIO ***********************************************************
     ****************************************************************************************************************************************/	

	public Usuario getUsuarioByEmail(String email){
		Session sesion = UtilidadHibernate.getSesion();
		return (Usuario) sesion.createQuery(" FROM es.dabdm.decide.modelo.Usuario " +
				                           " WHERE email = :email ")
				                .setString("email", email)
				                .uniqueResult();
	}
	
	public void altaUsuario( es.dabdm.decide.dto.Usuario usuario){
		try {			
			grabarObjeto(new Usuario(usuario.getEmail(), usuario.getIdRegistration(), usuario.getTelefono(), usuario.getNombre(), usuario.getPublicidad()));
			UtilidadHibernate.commit();
			System.out.println("Usuario dado de alta. email->"+usuario.getEmail());
		} catch (Exception e) {
			System.out.println("Error en altaUsuario a las " + new Date() + " " + e.getMessage());
		}
	}
	
	
	
	/* **************************************************************************************************************************************
	**************************************************************** SUSCRIPCION ***********************************************************
     ****************************************************************************************************************************************/	
	 public Suscripcion getSuscripcion(Usuario usuario, Comunidad comunidad){
		 try {
		    Session sesion = UtilidadHibernate.getSesion();
			return (Suscripcion) sesion.createQuery(" FROM es.dabdm.decide.modelo.Suscripcion " +
					                           " WHERE usuario = :usuario AND" +
					                                 " comunidad = :comunidad")
					                .setEntity("usuario", usuario)
					                .setEntity("comunidad", comunidad)
					                .uniqueResult();
		} catch (Exception e) {
			System.out.println("Error en getSuscripcion a las " + new Date() + " " + e.getMessage());
		} 		
		 return null;
	 }
	
	
	public void desuscribirUsuario(String email,Integer idComunidad){
		try {
			Session sesion = UtilidadHibernate.getSesion();
			Usuario usuario = getUsuarioByEmail(email);
			Comunidad comunidad = (Comunidad) sesion.get(Comunidad.class, idComunidad);

			Suscripcion suscripcion = getSuscripcion(usuario,comunidad);				
			borrarObjeto(suscripcion);
			UtilidadHibernate.commit();
		} catch (Exception e) {
			System.out.println("Error en desuscribirUsuario a las " + new Date() + " " + e.getMessage());
		}  		
	}
	
	
	
	public void suscribirUsuario(String email,Integer idComunidad){
		try {
			Session sesion = UtilidadHibernate.getSesion();
			Usuario usuario = getUsuarioByEmail(email);
			Comunidad comunidad = (Comunidad) sesion.get(Comunidad.class, idComunidad);
			Suscripcion suscripcion = new Suscripcion(comunidad, usuario, new Date());				
			grabarObjeto(suscripcion);
			UtilidadHibernate.commit();
		} catch (Exception e) {
			System.out.println("Error en suscribirUsuario a las " + new Date() + " " + e.getMessage());
		}  		
	}
	
	
	/* **************************************************************************************************************************************
	**************************************************************** Pregunta ***********************************************************
     ****************************************************************************************************************************************/
	public Pregunta getPregunta(Integer idPregunta){
		try {
			Session sesion = UtilidadHibernate.getSesion();
			return (Pregunta) sesion.get(Pregunta.class, idPregunta);
			
		} catch (Exception e) {
			System.out.println("Error en getPregunta a las " + new Date() + " " + e.getMessage());
		} 
		return null;
	}
	
	
	/* **************************************************************************************************************************************
	**************************************************************** Pregunta DTO ***********************************************************
     ****************************************************************************************************************************************/
	public es.dabdm.decide.dto.Pregunta getPreguntaDTO(Pregunta pregunta){
		es.dabdm.decide.dto.Pregunta preguntaDTO =null;
		try {
           
           preguntaDTO = new es.dabdm.decide.dto.Pregunta(pregunta.getIdPregunta(), pregunta.getComunidad(), pregunta.getTexto());
           preguntaDTO.setFechaLimite(pregunta.getEncuesta().getFechaLimite());
		   List<es.dabdm.decide.dto.RespuestaPosible> respuestasDTO = new ArrayList<es.dabdm.decide.dto.RespuestaPosible>();		   
           for(RespuestaPosible r :pregunta.getRespuestasPosibles()){
			   	 respuestasDTO.add(new es.dabdm.decide.dto.RespuestaPosible(r.getIdRespuestaPosible(),r.getValor()));
		   }
           
           System.out.println("preguntaDTO ->"+pregunta.getTexto());
		} catch (Exception e) {
			System.out.println("Error en getPreguntaDTO a las " + new Date() + " " + e.getMessage());
		} 
		return preguntaDTO;
	}
	
	
	
	/* **************************************************************************************************************************************
	 **************************************************************** RespuestaPosible ***********************************************************
     ****************************************************************************************************************************************/
	 public RespuestaPosible getRespuestaPosible(Integer idRespuesta){
		try {
			Session sesion = UtilidadHibernate.getSesion();
			return (RespuestaPosible) sesion.get(RespuestaPosible.class, idRespuesta);
			
		} catch (Exception e) {
			System.out.println("Error en getRespuestaPosible a las " + new Date() + " " + e.getMessage());
		} 
		return null;
	}
	
	/* **************************************************************************************************************************************
	**************************************************************** RespuestaUsuario ***********************************************************
     ****************************************************************************************************************************************/
	  public void responderPregunta(Integer idPregunta, String email, Integer idRespuesta){
		  try {
			  RespuestaPosible respuesta = getRespuestaPosible(idRespuesta);
			  Pregunta pregunta = getPregunta(idPregunta);
			  Usuario usuario = getUsuarioByEmail(email);
			  if(respuesta!=null && pregunta!=null && respuesta.getPregunta().getIdPregunta().equals(pregunta.getIdPregunta())){
				  RespuestaUsuario respondido = new RespuestaUsuario(usuario, respuesta);
				  grabarObjeto(respondido);
				  UtilidadHibernate.commit();
				  System.out.println("responderPregunta correctamente..." );
			  }
		 } catch (Exception e) {
			System.out.println("Error en responderPregunta a las " + new Date() + " " + e.getMessage());
		 } 
	  }


	  
	  
	  
	  
	  
	  
	  
	  
	  
	
	///////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////     UTILIDADES BBDD     //////////////////////////////////////////////	
    ///////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void grabarObjeto(Object objeto) {
		Session sesion = UtilidadHibernate.getSesion();
		sesion.saveOrUpdate(objeto);
		UtilidadHibernate.commit();//Guarda los cambios en bbdd
	}
	
	
	public void borrarObjeto(Object objeto) {
		Session sesion = UtilidadHibernate.getSesion();
		sesion.delete(objeto);
		UtilidadHibernate.commit();//Guarda los cambios en bbdd
	}
}
