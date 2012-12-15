package es.dabdm.decide.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="ENCUESTAS")
public class Encuesta implements Serializable{

	private static final long serialVersionUID = -8414195943489605518L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="ENCUESTAS_seq")
	@SequenceGenerator(name="ENCUESTAS_seq",sequenceName="ENCUESTAS_seq")		
	private Integer idEncuesta;
	
	@Temporal(TemporalType.DATE)
	private Date fechaLimite;
	
    @ManyToMany
    @JoinTable(name="ENCUESTASUSUARIOS",joinColumns={@JoinColumn(name="idEncuesta")},inverseJoinColumns={@JoinColumn(name="idUsuario")})
	private List<Usuario> usuarios;
	
    @OneToMany(mappedBy="encuesta")    
	private List<Pregunta> preguntas;
   
	@ManyToOne
	@JoinColumn(name="idComunidad") 
	private Comunidad comunidad;
	
	
	
	public Encuesta() {
	}
	
	
	public Encuesta(Date fechaLimite, Comunidad comunidad) {
		super();
		this.fechaLimite = fechaLimite;
		this.comunidad = comunidad;
	}




	public Date getFechaLimite() {
		return fechaLimite;
	}
	public void setFechaLimite(Date fechaLimite) {
		this.fechaLimite = fechaLimite;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	public List<Pregunta> getPreguntas() {
		return preguntas;
	}
	public void setPreguntas(List<Pregunta> preguntas) {
		this.preguntas = preguntas;
	}
	public Integer getIdEncuesta() {
		return idEncuesta;
	}
	public void setIdEncuesta(Integer idEncuesta) {
		this.idEncuesta = idEncuesta;
	}
	public Comunidad getComunidad() {
		return comunidad;
	}
	public void setComunidad(Comunidad comunidad) {
		this.comunidad = comunidad;
	}
	
}
