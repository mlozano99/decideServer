package es.dabdm.decide.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import es.dabdm.decide.modelo.Encuesta;

@XmlRootElement
public class ListaEncuestas {

  private List<Encuesta> encuestas;

  public List<Encuesta> getEncuestas() {
	return encuestas;
  }
	
  public void setEncuestas(List<Encuesta> encuestas) {
	this.encuestas = encuestas;
  }
	
}
