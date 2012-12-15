package es.dabdm.decide.webService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import com.google.android.gcm.server.Constants;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Message.Builder;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
import com.google.gson.Gson;

import es.dabdm.decide.dto.Pregunta;
import es.dabdm.decide.dto.RespuestaPosible;
import es.dabdm.decide.servicio.ServicioConsultas;

@Path("/notificar")
public class EnviarNotificacionSrv {

	private static final ServicioConsultas srvConsultas = ServicioConsultas.getServicioConsultas();

	private static final Sender sender = new Sender("AIzaSyCuZugAylt6lK9hSIIMdQmcTQAJn0RWH3M");
	private static final Executor threadPool = Executors.newFixedThreadPool(5);
	private static final int MULTICAST_SIZE = 1000;

	
	
	@GET
	@Consumes("application/x-www-form-urlencoded")
	public String getFriendsHighScores(@QueryParam("regId") String regId) throws IOException {

		System.out.println("notificar peticion a las " + new Date() + "  ,notificar regId=" + regId);

		String status;
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
				Message message = builder.build();
				
				Result result = sender.send(message, registrationId, 5);
				status = "Sent message to one device: " + result;
                System.out.println("Despues 1. "+status);
				 
			} else {
				// ESTO CUANDO SEAN MAS DE UNO
				// send a multicast message using JSON
				// must split in chunks of 1000 devices (GCM limit)
				List<String> devices = new ArrayList<String>();
				int total = 10000;
				List<String> partialDevices = new ArrayList<String>(total);
				int counter = 0;
				int tasks = 0;
				for (String device : devices) {
					counter++;
					partialDevices.add(device);
					int partialSize = partialDevices.size();
					if (partialSize == MULTICAST_SIZE || counter == total) {
						asyncSend(partialDevices);
						partialDevices.clear();
						tasks++;
					}
				}
				status = "Asynchronously sending " + tasks + " multicast messages to " + total + " devices";
			}
		}
		System.out.println("Final="+status);
		return regId;
	}

	private void asyncSend(List<String> partialDevices) {
		// make a copy
		final List<String> devices = new ArrayList<String>(partialDevices);
		threadPool.execute(new Runnable() {

			public void run() {
				//Message message = new Message.Builder().build();
				Builder builder = new Message.Builder(); 				
				Message message = builder.build();
				MulticastResult multicastResult;
				try {
					multicastResult = sender.send(message, devices, 5);
				} catch (IOException e) {
					System.out.println("Error posting messages");
					return;
				}
				List<Result> results = multicastResult.getResults();
				// analyze the results
				for (int i = 0; i < devices.size(); i++) {
					String regId = devices.get(i);
					Result result = results.get(i);
					String messageId = result.getMessageId();
					if (messageId != null) {
						System.out.println("Succesfully sent message to device: " + regId + "; messageId = " + messageId);
						String canonicalRegId = result.getCanonicalRegistrationId();
						if (canonicalRegId != null) {
							// same device has more than on registration id:
							// update it
							System.out.println("canonicalRegId "+ canonicalRegId);

						}
					} else {
						String error = result.getErrorCodeName();
						if (error.equals(Constants.ERROR_NOT_REGISTERED)) {
							// application has been removed from device - unregister it
							System.out.println("Unregistered device: " + regId);

						} else {
							System.out.println("Error sending message to " + regId + ": " + error);
						}
					}
				}
			}
		});
	}

}