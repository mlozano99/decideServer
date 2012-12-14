package es.dabdm.decide.webService;

import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import es.dabdm.decide.dto.ListaComunidades;
import es.dabdm.decide.servicio.ServicioConsultas;

@Path("/suscripciones")
public class SuscripcionesSrv {

	public static final ServicioConsultas srvConsultas = ServicioConsultas.getServicioConsultas();

	@DELETE
	@Consumes("application/x-www-form-urlencoded")
	public void deSuscribirUsusarioComunidad(@FormParam("email") String email, @FormParam("idComunidad")Integer idComunidad) {
		System.out.println("deSuscribirUsusarioComunidad peticion a las " + new Date() + "  ,email=" + email );
		srvConsultas.desuscribirUsuario(email, idComunidad);
	}	
	
	
	@PUT
	@Consumes("application/x-www-form-urlencoded")
	public void suscribirUsusarioComunidad(@FormParam("email") String email, @FormParam("idComunidad")Integer idComunidad) {
		System.out.println("suscribirUsusarioComunidad peticion a las " + new Date() + "  ,email=" + email );
		srvConsultas.suscribirUsuario(email, idComunidad);
	}	
	

	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes("application/x-www-form-urlencoded")
	public ListaComunidades getComunidadesSuscritas(@QueryParam("email") String email) {
		
		System.out.println("getComunidadesSuscritas peticion a las " + new Date() + "  ,email=" + email );
        if(email==null||"".equals(email)){
        	email="vacio";
        }
		ListaComunidades lista = new ListaComunidades();
		lista.setComunidades( srvConsultas.getComunidadSuscritas(email) );

		return lista;

	}
	
}