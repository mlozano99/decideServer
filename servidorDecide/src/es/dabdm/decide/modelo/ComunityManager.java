package es.dabdm.decide.modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="MANAGERS")
public class ComunityManager implements Serializable{

	private static final long serialVersionUID = 2972103645772331624L;

    @Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="MANAGERS_seq")
	@SequenceGenerator(name="MANAGERS_seq",sequenceName="MANAGERS_seq")	
	private Integer idManager;
	/*
	 * Identificador único del comunity manager
	 */
	private String nombre;

	/*
	@OneToMany(mappedBy="gestor",fetch=FetchType.LAZY) 
	private List<Comunidad> comunidades;
	*/
	
	public ComunityManager() {
	}
	

	public ComunityManager(String nombre) {
		super();
		this.nombre = nombre;
	}



	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getIdManager() {
		return idManager;
	}
	public void setIdManager(Integer idManager) {
		this.idManager = idManager;
	}
	/*
	public List<Comunidad> getComunidades() {
	return comunidades;
}
	public void setComunidades(List<Comunidad> comunidades) {
		this.comunidades = comunidades;
	}*/
}
