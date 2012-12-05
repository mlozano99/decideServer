package es.dabdm.decide.webService;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import es.dabdm.decide.dto.ListaComunidades;
import es.dabdm.decide.servicio.ServicioConsultas;

@Path("/comunidades")
public class ComunidadesSrv {

	public static final ServicioConsultas srvConsultas = ServicioConsultas.getServicioConsultas();

	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes("application/x-www-form-urlencoded")
	public ListaComunidades getFriendsHighScores(@QueryParam("latitud") String latitud,@QueryParam("longitud") String longitud) {
		
		System.out.println("longitud=" + longitud + ", latitud="+latitud);

		ListaComunidades lista = new ListaComunidades();
		lista.setComunidades( srvConsultas.getComunidades() );

		return lista;

	}
	
}