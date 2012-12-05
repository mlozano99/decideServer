package es.dabdm.decide.modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
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
@Table(name="COMUNIDADES")
public class Comunidad implements Serializable {

	private static final long serialVersionUID = 2544867380337063679L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="COMUNIDADES_seq")
	@SequenceGenerator(name="COMUNIDADES_seq",sequenceName="COMUNIDADES_seq")		
	private Integer idComunidad;
	
	private String nombre;
	
	private String twitter; 
	
    @Embedded
	@AttributeOverrides({ @AttributeOverride(name="longitud",column=@Column(name="LONGITUD")),
			              @AttributeOverride(name="latitud",column=@Column(name="LATITUD"))
	})
	private PosicionGPS gps;
	
	private String alcance;
	
	private String tipo;
	
	/**
	 * Para JOSE: Hará falta que haya una función que tenga esta API:
	 *   
	 *   public boolean isUsuarioEnRadioComunidad(PosicionGPS gpsUsuario, PosicionGPS gpsComunidad);
	 *   
	 *   Esta función dirá si esta o no en el radio de la comunidad.
	 */
	private Integer radio;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDMANAGER")
	private ComunityManager gestor;
	
    @OneToMany(mappedBy="comunidad",fetch=FetchType.LAZY)    
	private List<Suscripcion> suscriptores;
	
    @OneToMany(mappedBy="comunidad",fetch=FetchType.LAZY)    
	private List<Encuesta> encuestas;
    
    public Comunidad() {
	}
    
    
    
	public Comunidad(String nombre, String twitter, PosicionGPS gps,
			String alcance, String tipo, Integer radio, ComunityManager gestor) {
		super();
		this.nombre = nombre;
		this.twitter = twitter;
		this.gps = gps;
		this.alcance = alcance;
		this.tipo = tipo;
		this.radio = radio;
		this.gestor = gestor;
	}



	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTwitter() {
		return twitter;
	}

	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}

	public PosicionGPS getGps() {
		return gps;
	}

	public void setGps(PosicionGPS gps) {
		this.gps = gps;
	}

	public String getAlcance() {
		return alcance;
	}

	public void setAlcance(String alcance) {
		this.alcance = alcance;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Integer getRadio() {
		return radio;
	}

	public void setRadio(Integer radio) {
		this.radio = radio;
	}

	public ComunityManager getGestor() {
		return gestor;
	}

	public void setGestor(ComunityManager gestor) {
		this.gestor = gestor;
	}

	public List<Suscripcion> getSuscriptores() {
		return suscriptores;
	}

	public void setSuscriptores(List<Suscripcion> suscriptores) {
		this.suscriptores = suscriptores;
	}

	public Integer getIdComunidad() {
		return idComunidad;
	}

	public void setIdComunidad(Integer idComunidad) {
		this.idComunidad = idComunidad;
	}

	public List<Encuesta> getEncuestas() {
		return encuestas;
	}

	public void setEncuestas(List<Encuesta> encuestas) {
		this.encuestas = encuestas;
	}
	
	
	
}
