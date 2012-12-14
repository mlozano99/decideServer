package es.dabdm.decide.webService;

import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;

import es.dabdm.decide.modelo.Usuario;
import es.dabdm.decide.servicio.ServicioConsultas;


@Path("/usuarios")
public class UsuariosSrv {

	public static final ServicioConsultas srvConsultas = ServicioConsultas.getServicioConsultas();
	
	

	@PUT
	@Consumes("application/x-www-form-urlencoded")
	public void putFriend(@FormParam("name") String name) {

		System.out.println("usuarios peticion a las " + new Date() );
		
	}
}
