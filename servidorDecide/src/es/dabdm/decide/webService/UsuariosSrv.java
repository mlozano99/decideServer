package es.dabdm.decide.webService;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;

import es.dabdm.decide.modelo.Usuario;
import es.dabdm.decide.servicio.ServicioConsultas;


@Path("/usuarios")
public class UsuariosSrv {

	public static final ServicioConsultas srvConsultas = ServicioConsultas.getServicioConsultas();
	
	

	@PUT
    @Consumes("application/json")
	public void putFriend(Usuario usuario) {

        System.out.println("");
		
	}
}
