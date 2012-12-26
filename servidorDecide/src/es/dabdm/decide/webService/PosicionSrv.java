package es.dabdm.decide.webService;

import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;

import es.dabdm.decide.servicio.ServicioConsultas;


@Path("/posicion")
public class PosicionSrv {

	public static final ServicioConsultas srvConsultas = ServicioConsultas.getServicioConsultas();
	
	

	@PUT
	@Consumes("application/x-www-form-urlencoded")
	public void posicionUsuario(@FormParam("longitud")  double longitud,@FormParam("latitud")  double latitud,@FormParam("email") String email) {

		System.out.println("posicionUsuario peticion a las " + new Date() + ", longitud="+longitud  + ", latitud="+latitud + ", email="+ email);
		
	}
}
