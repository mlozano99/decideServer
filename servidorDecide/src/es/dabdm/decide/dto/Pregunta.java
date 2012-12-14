package es.dabdm.decide.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class Pregunta implements Serializable{

	private static final long serialVersionUID = 8974664891175454462L;

	private Integer idPregunta;
	
	private Integer idComunidad;

	private String texto;
    
	private Date fechaLimite;
	
	private List<RespuestaPosible> respuestasPosibles;

	public Pregunta() {
	}
	
	public Pregunta(Integer idPregunta, Integer idComunidad, String texto) {
		super();
		this.idPregunta = idPregunta;
		this.idComunidad = idComunidad;
		this.texto = texto;
	}

	public Integer getIdPregunta() {
		return idPregunta;
	}

	public void setIdPregunta(Integer idPregunta) {
		this.idPregunta = idPregunta;
	}

	public Integer getIdComunidad() {
		return idComunidad;
	}

	public void setIdComunidad(Integer idComunidad) {
		this.idComunidad = idComunidad;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Date getFechaLimite() {
		return fechaLimite;
	}

	public void setFechaLimite(Date fechaLimite) {
		this.fechaLimite = fechaLimite;
	}

	public List<RespuestaPosible> getRespuestasPosibles() {
		return respuestasPosibles;
	}

	public void setRespuestasPosibles(List<RespuestaPosible> respuestasPosibles) {
		this.respuestasPosibles = respuestasPosibles;
	}

}
