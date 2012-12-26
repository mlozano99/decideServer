package es.dabdm.decide.webService;

import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONObject;

import com.google.gson.Gson;


import es.dabdm.decide.dto.ListaComunidades;
import es.dabdm.decide.servicio.ServicioConsultas;


@Path("/usuarios")
public class UsuariosSrv {

	public static final ServicioConsultas srvConsultas = ServicioConsultas.getServicioConsultas();
	
	

	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes("application/x-www-form-urlencoded")
	public es.dabdm.decide.dto.Usuario getUsuario(@QueryParam("email") String email) {
		
		System.out.println("getUsuario peticion a las " + new Date() + "  ,email=" + email );
        if(email==null||"".equals(email)){
        	email="vacio";
        }
        es.dabdm.decide.dto.Usuario usuario = srvConsultas.getUsuarioDTOByEmail(email);//Usuario recuperado en formato JSON
        
        //TEMPORAL INVENTADO!!!! para que devuelve siempres
        if(usuario==null){
        	usuario = new es.dabdm.decide.dto.Usuario (email,email,"9999999",email,"N");
        }
        
        return usuario;

	}
	
	@PUT
	@Consumes("application/x-www-form-urlencoded")
	public void altaUsuario(@FormParam("usuario") String jsonEntity) {
		System.out.println("altaUsuario peticion a las " + new Date() );
		Gson gson = new Gson();
		es.dabdm.decide.dto.Usuario usuario = gson.fromJson(jsonEntity, es.dabdm.decide.dto.Usuario.class);
		srvConsultas.altaUsuario(usuario);		
	}
}
