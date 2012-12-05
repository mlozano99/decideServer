package es.dabdm.decide.modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="PREGUNTAS")
public class Pregunta implements Serializable{

	private static final long serialVersionUID = 2974432651255159694L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="PREGUNTAS_seq")
	@SequenceGenerator(name="PREGUNTAS_seq",sequenceName="PREGUNTAS_seq")
	private Integer idPregunta;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDENCUESTA")
	private Encuesta encuesta;

	@ManyToOne
	@JoinColumn(name="IDCOMUNIDAD")
	private Comunidad comunidad;
	
	private String texto;

    @OneToMany(mappedBy="pregunta",fetch=FetchType.LAZY)    
	private List<RespuestaPosible> respuestasPosibles;
	
    
    public Pregunta() {
	}
    
    
	public Pregunta(Encuesta encuesta, Comunidad comunidad, String texto) {
		super();
		this.encuesta = encuesta;
		this.comunidad = comunidad;
		this.texto = texto;
	}



	public Encuesta getEncuesta() {
		return encuesta;
	}

	public void setEncuesta(Encuesta encuesta) {
		this.encuesta = encuesta;
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

	public List<RespuestaPosible> getRespuestasPosibles() {
		return respuestasPosibles;
	}

	public void setRespuestasPosibles(List<RespuestaPosible> respuestasPosibles) {
		this.respuestasPosibles = respuestasPosibles;
	}

	public Integer getIdPregunta() {
		return idPregunta;
	}

	public void setIdPregunta(Integer idPregunta) {
		this.idPregunta = idPregunta;
	}
	
	
}
