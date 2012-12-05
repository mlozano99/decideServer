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
@Table(name="RESPUESTASUSUARIO")
public class RespuestaUsuario implements Serializable{

	private static final long serialVersionUID = 7740757546884517889L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="RESPUESTASUSUARIO_seq")
	@SequenceGenerator(name="RESPUESTASUSUARIO_seq",sequenceName="RESPUESTASUSUARIO_seq")	
	private Integer idRespuestaUsuario;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDUSUARIO")
	private Usuario usuario;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDRESPUESTAPOSIBLE")
	private RespuestaPosible respuesta;

	
	public RespuestaUsuario() {
	}
	
	
	public RespuestaUsuario(Usuario usuario, RespuestaPosible respuesta) {
		super();
		this.usuario = usuario;
		this.respuesta = respuesta;
	}


	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public RespuestaPosible getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(RespuestaPosible respuesta) {
		this.respuesta = respuesta;
	}

	public Integer getIdRespuestaUsuario() {
		return idRespuestaUsuario;
	}

	public void setIdRespuestaUsuario(Integer idRespuestaUsuario) {
		this.idRespuestaUsuario = idRespuestaUsuario;
	}
	
	
}
