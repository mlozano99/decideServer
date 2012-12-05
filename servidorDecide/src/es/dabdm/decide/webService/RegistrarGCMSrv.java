package es.dabdm.decide.webService;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;


@Path("/register")
public class RegistrarGCMSrv {
	
	@POST
	@Consumes("application/x-www-form-urlencoded")
	public void putFriend(@FormParam("regId") String regId) {	
		System.out.println("regId="+regId);
	}

}

