package es.dabdm.decide.webService;

import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import es.dabdm.decide.dto.ListaComunidades;
import es.dabdm.decide.servicio.ServicioConsultas;

@Path("/register")
public class RegistrarGCMSrv {


//	public static final ServicioConsultas srvConsultas = ServicioConsultas.getServicioConsultas();
	
	@POST
	@Consumes("application/x-www-form-urlencoded")
	public String getFriendsHighScores(@FormParam("regId") String regId) {
		System.out.println("register peticion a las " + new Date() + "  ,regId="+regId);
		
		return regId;

	}
}

	