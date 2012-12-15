package es.dabdm.decide.modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="RESPUESTASPOSIBLES")
public class RespuestaPosible implements Serializable{

	private static final long serialVersionUID = 4876897886769836373L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="RESPUESTASPOSIBLES_seq")
	@SequenceGenerator(name="RESPUESTASPOSIBLES_seq",sequenceName="RESPUESTASPOSIBLES_seq")
	private Integer idRespuestaPosible;
	
	@ManyToOne
	@JoinColumn(name="IDPREGUNTA")
	private Pregunta pregunta;

    private String valor;
	
    
    public RespuestaPosible() {
	}
    
    
    
	public RespuestaPosible(Pregunta pregunta, String valor) {
		super();
		this.pregunta = pregunta;
		this.valor = valor;
	}



	public Pregunta getPregunta() {
		return pregunta;
	}

	public void setPregunta(Pregunta pregunta) {
		this.pregunta = pregunta;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public Integer getIdRespuestaPosible() {
		return idRespuestaPosible;
	}

	public void setIdRespuestaPosible(Integer idRespuestaPosible) {
		this.idRespuestaPosible = idRespuestaPosible;
	}
	
}
