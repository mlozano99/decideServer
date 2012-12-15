package es.dabdm.decide.webService;

import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;


import es.dabdm.decide.servicio.ServicioConsultas;


@Path("/usuarios")
public class UsuariosSrv {

	public static final ServicioConsultas srvConsultas = ServicioConsultas.getServicioConsultas();
	
	

	@PUT
	@Consumes("application/json")
	public void altaUsuario( es.dabdm.decide.dto.Usuario usuario) {
		System.out.println("altaUsuario peticion a las " + new Date() );
		srvConsultas.altaUsuario(usuario);		
	}
}
