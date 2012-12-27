package es.dabdm.decide.webService;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import com.google.android.gcm.server.Constants;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Message.Builder;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import es.dabdm.decide.servicio.ServicioConsultas;

@Path("/notificarPregunta")
public class NotificacionPreguntaSrv {

	private static final ServicioConsultas srvConsultas = ServicioConsultas.getServicioConsultas();

	private static final Sender sender = new Sender("AIzaSyCuZugAylt6lK9hSIIMdQmcTQAJn0RWH3M");
	private static final Executor threadPool = Executors.newFixedThreadPool(5);
	private static final int MULTICAST_SIZE = 1000;

	
	
	@GET
	@Consumes("application/x-www-form-urlencoded")
	public String notificaUnaPregunta(@QueryParam("idPregunta") Integer idPregunta,@QueryParam("regId") String regId) throws IOException {

		System.out.println("notificaUnaPregunta peticion a las " + new Date() + "  ,notificar idPregunta=" + idPregunta + "  ,notificar regId=" + regId);

		es.dabdm.decide.dto.Pregunta pregunta = srvConsultas.getPreguntaDTO( srvConsultas.getPregunta(idPregunta) );
		if(pregunta==null){
			return "No se ha encontrado la pregunta con id="+idPregunta;
		}
		
		String status="";
		if (regId == null || "".equals(regId)) {
			status = "Message ignored as there is no device registered!";
		} else {
			// NOTE: check below is for demonstration purposes; a real
			// application
			// could always send a multicast, even for just one recipient

			if ("1".equals("1")) {
				// send a single message using plain post
				String registrationId = regId;
				//Message message = new Message.Builder().build();
				Builder builder = new Message.Builder(); 
				
				
				Gson gson = new GsonBuilder().setDateFormat(Constants.FORMATO_FECHA).create();				
				String datosPreguntaGson =  gson.toJson(pregunta);
				builder.addData("pregunta", datosPreguntaGson);				
				//builder.addData("pregunta", "555444333222111");
				Message message = builder.build();
				
				Result result = sender.send(message, registrationId, 5);
				status = "Sent message to one device: " + result;
                System.out.println("Despues 1. "+status);
				 
			}
		}
		System.out.println("Final="+status);
		return regId;


	}
}