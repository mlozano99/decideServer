package es.dabdm.decide.webService;

import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import es.dabdm.decide.servicio.ServicioConsultas;

@Path("/preguntas")
public class PreguntasSrv {

	public static final ServicioConsultas srvConsultas = ServicioConsultas.getServicioConsultas();

	/*
	 * Cuando responda el usuario se guarda la respuesta
	 */
	@PUT
	@Consumes("application/x-www-form-urlencoded")
	public void responderPregunta(@FormParam("idPregunta") Integer idPregunta,@FormParam("email") String email,@FormParam("idRespuesta") Integer idRespuesta) {
		
		System.out.println("responderPregunta peticion a las " + new Date() + "  ,idPregunta=" + idPregunta + ", email="+email+ ", idRespuesta="+idRespuesta);
		srvConsultas.responderPregunta(idPregunta, email, idRespuesta);		

	}
	
}