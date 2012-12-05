package es.dabdm.decide.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="SUSCRIPCIONES")
public class Suscripcion implements Serializable{
	
	private static final long serialVersionUID = 6352666378580854630L;
	
    @Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SUSCRIPCIONES_seq")
	@SequenceGenerator(name="SUSCRIPCIONES_seq",sequenceName="SUSCRIPCIONES_seq")	
    private Integer idSuscripcion;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDCOMUNIDAD")
	private Comunidad comunidad;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDUSUARIO")
	private Usuario usuario;

	@Temporal(TemporalType.DATE)
	private Date fechaAlta;
	
	
	
	public Suscripcion() {	
	}	
	
	
	public Suscripcion(Comunidad comunidad, Usuario usuario, Date fechaAlta) {
		super();
		this.comunidad = comunidad;
		this.usuario = usuario;
		this.fechaAlta = fechaAlta;
	}




	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public Comunidad getComunidad() {
		return comunidad;
	}

	public void setComunidad(Comunidad comunidad) {
		this.comunidad = comunidad;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Integer getIdSuscripcion() {
		return idSuscripcion;
	}

	public void setIdSuscripcion(Integer idSuscripcion) {
		this.idSuscripcion = idSuscripcion;
	}
	
	
}
