package es.dabdm.decide.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import es.dabdm.decide.modelo.Comunidad;


public class Pregunta implements Serializable{

	private static final long serialVersionUID = 8974664891175454462L;

	private Integer idPregunta;
	
	private Comunidad comunidad;

	private String texto;
    
	private Date fechaLimite;
	
	private List<RespuestaPosible> respuestasPosibles;

	public Pregunta() {
	}
	
	public Pregunta(Integer idPregunta, Comunidad comunidad, String texto) {
		super();
		this.idPregunta = idPregunta;
		this.comunidad = comunidad;
		this.texto = texto;
	}

	public Integer getIdPregunta() {
		return idPregunta;
	}

	public void setIdPregunta(Integer idPregunta) {
		this.idPregunta = idPregunta;
	}

	public Comunidad getComunidad() {
		return comunidad;
	}

	public void setComunidad(Comunidad comunidad) {
		this.comunidad = comunidad;
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

	@Override
	public String toString() {
		return "Pregunta [idPregunta=" + idPregunta + ", comunidad="
				+ comunidad + ", texto=" + texto + ", fechaLimite="
				+ fechaLimite + ", respuestasPosibles=" + respuestasPosibles
				+ "]";
	}

	
	
}
