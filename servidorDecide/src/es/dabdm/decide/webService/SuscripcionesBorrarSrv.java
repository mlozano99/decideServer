package es.dabdm.decide.webService;

import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import es.dabdm.decide.servicio.ServicioConsultas;

@Path("/desuscribir")
public class SuscripcionesBorrarSrv {

	public static final ServicioConsultas srvConsultas = ServicioConsultas.getServicioConsultas();

	
	@POST
	@Consumes("application/x-www-form-urlencoded")
	public void desuscribirUsuarioComunidad(@FormParam("email") String email, @FormParam("idComunidad")Integer idComunidad) {
		System.out.println("desuscribirUsuarioComunidad peticion a las " + new Date() + "  ,email=" + email );
		srvConsultas.desuscribirUsuario(email, idComunidad);
	}	
	


}