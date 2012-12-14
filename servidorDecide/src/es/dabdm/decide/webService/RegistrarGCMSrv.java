package es.dabdm.decide.webService;

import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

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

	