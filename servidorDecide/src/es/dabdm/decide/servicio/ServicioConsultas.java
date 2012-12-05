package es.dabdm.decide.servicio;

import java.util.List;

import org.hibernate.Session;

import es.dabdm.decide.modelo.*;

import util.UtilidadHibernate;

public class ServicioConsultas {

	private static ServicioConsultas srvConsultas;

	public static ServicioConsultas getServicioConsultas(){
	    if(ServicioConsultas.srvConsultas==null){
	    	ServicioConsultas.srvConsultas=new ServicioConsultas();
	    }
		return ServicioConsultas.srvConsultas;	
	}
	
	public List<Comunidad> getComunidades(){
		Session sesion = UtilidadHibernate.getSesion();
		return sesion.createQuery("FROM es.dabdm.decide.modelo.Comunidad").list();
	}
	
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////     UTILIDADES BBDD     //////////////////////////////////////////////	
    ///////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void grabarObjeto(Object objeto) {
		Session sesion = UtilidadHibernate.getSesion();
		sesion.saveOrUpdate(objeto);
		UtilidadHibernate.commit();//Guarda los cambios en bbdd
	}
	
	
	public void borrarObjeto(Object objeto) {
		Session sesion = UtilidadHibernate.getSesion();
		sesion.delete(objeto);
		UtilidadHibernate.commit();//Guarda los cambios en bbdd
	}
}
