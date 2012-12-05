package es.dabdm.decide.modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="USUARIOS")
public class Usuario implements Serializable{

	private static final long serialVersionUID = -8594642118817237794L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="USUARIOS_seq")
	@SequenceGenerator(name="USUARIOS_seq",sequenceName="USUARIOS_seq")	
	private Integer idUsuario;
	
	/**
	 * Esto será la clave que identifique un usario
	 */
	private String email;	
	
	/**
	 * Registro GCM del usuario en la aplicacion
	 */
	private String idRegistration;
	
	private String telefono;
	
	private String nombre;	
	
	private String publicidad;

    @OneToMany(mappedBy="usuario",fetch=FetchType.LAZY)    
	private List<RespuestaUsuario> respuestas;
	
    @OneToMany(mappedBy="comunidad",fetch=FetchType.LAZY)    
	private List<Suscripcion> suscripciones;
	
    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(name="ENCUESTASUSUARIOS",joinColumns={@JoinColumn(name="idUsuario")},inverseJoinColumns={@JoinColumn(name="idEncuesta")})
    private List<Encuesta> encuestas;
    
    
    public Usuario() {
	}
 
	public Usuario(String email, String idRegistration, String telefono,String nombre, String publicidad) {
		super();
		this.email = email;
		this.idRegistration = idRegistration;
		this.telefono = telefono;
		this.nombre = nombre;
		this.publicidad = publicidad;
	}



	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getPublicidad() {
		return publicidad;
	}

	public void setPublicidad(String publicidad) {
		this.publicidad = publicidad;
	}

	public List<RespuestaUsuario> getRespuestas() {
		return respuestas;
	}

	public void setRespuestas(List<RespuestaUsuario> respuestas) {
		this.respuestas = respuestas;
	}

	public List<Suscripcion> getSuscripciones() {
		return suscripciones;
	}

	public void setSuscripciones(List<Suscripcion> suscripciones) {
		this.suscripciones = suscripciones;
	}

	public String getIdRegistration() {
		return idRegistration;
	}

	public void setIdRegistration(String idRegistration) {
		this.idRegistration = idRegistration;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public List<Encuesta> getEncuestas() {
		return encuestas;
	}

	public void setEncuestas(List<Encuesta> encuestas) {
		this.encuestas = encuestas;
	}

	
}
