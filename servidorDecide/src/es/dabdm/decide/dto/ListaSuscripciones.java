package es.dabdm.decide.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import es.dabdm.decide.modelo.Suscripcion;

@XmlRootElement
public class ListaSuscripciones {

  private List<Suscripcion> suscripciones;

  public List<Suscripcion> getSuscripciones() {
		return suscripciones;
  }
	
  public void setSuscripciones(List<Suscripcion> suscripciones) {
		this.suscripciones = suscripciones;
  }
	
}
