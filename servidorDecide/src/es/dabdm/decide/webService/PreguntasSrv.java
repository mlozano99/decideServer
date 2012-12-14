package es.dabdm.decide.webService;

import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import es.dabdm.decide.dto.ListaComunidades;
import es.dabdm.decide.servicio.ServicioConsultas;

@Path("/preguntas")
public class PreguntasSrv {

	public static final ServicioConsultas srvConsultas = ServicioConsultas.getServicioConsultas();

	/*
	 * Cuando responda el usuario se guarda la respuesta
	 */
	@PUT
	@Consumes("application/x-www-form-urlencoded")
	public void responderPregunta(@QueryParam("idPregunta") Integer idPregunta,@QueryParam("email") String email,@QueryParam("idRespuesta") String idRespuesta) {
		
		System.out.println("responderPregunta peticion a las " + new Date() + "  ,idPregunta=" + idPregunta + ", email="+email+ ", idRespuesta="+idRespuesta);

		ListaComunidades lista = new ListaComunidades();
		lista.setComunidades( srvConsultas.getComunidades() );

		//return lista;

	}
	
}