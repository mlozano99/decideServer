package es.dabdm.decide.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import es.dabdm.decide.modelo.Comunidad;


@XmlRootElement
public class ListaComunidades {

    private List<Comunidad> comunidades;

	public List<Comunidad> getComunidades() {
		return comunidades;
	}
	
	public void setComunidades(List<Comunidad> comunidades) {
		this.comunidades = comunidades;
	}

  
}
