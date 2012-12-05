package util;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;


public abstract class GenericHibernateDAO<T, ID extends Serializable>{
 
    private Class<T> persistentClass;
 
    
    public void iniciaSesionBaseDatos(){
    //	UtilidadHibernate.setSesion(UtilidadHibernate.getSessionFactory().openSession());
    	UtilidadHibernate.getSesion().beginTransaction();
    }
    
    public void guardarCambios(){
    	UtilidadHibernate.getSesion().flush();
    	UtilidadHibernate.getSesion().getTransaction().commit();
    	UtilidadHibernate.getSesion().beginTransaction();
    }
    
    public void rollBack(){
    	UtilidadHibernate.getSesion().getTransaction().rollback();
    	UtilidadHibernate.getSesion().getTransaction().begin();
    }
    
    public void cierraSesionBaseDatos(){
    	UtilidadHibernate.getSesion().getTransaction().rollback();
    	UtilidadHibernate.getSesion().close();
    	UtilidadHibernate.getSessionFactory().close();
    }
        
    
    public GenericHibernateDAO() {
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
                                .getGenericSuperclass()).getActualTypeArguments()[0];
     }
 

 
    protected Session getSession() {
    	return UtilidadHibernate.getSesion();
    }
 
    public Class<T> getPersistentClass() {
        return persistentClass;
    }
 
    @SuppressWarnings("unchecked")
    public T findById(ID id, boolean lock) {
        T entity;
        if (lock)
            entity = (T) getSession().load(getPersistentClass(), id, LockMode.UPGRADE);
        else
            entity = (T) getSession().load(getPersistentClass(), id);
 
        return entity;
    }
 
    
    
    
    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        return findByCriteria();
    }
 
    @SuppressWarnings("unchecked")
    public List<T> findByExample(T exampleInstance, String[] excludeProperty) {
        Criteria crit = getSession().createCriteria(getPersistentClass());
        Example example =  Example.create(exampleInstance);
        for (String exclude : excludeProperty) {
            example.excludeProperty(exclude);
        }
        crit.add(example);
        return crit.list();
    }
 
    @SuppressWarnings("unchecked")
    public T save(T entity) {
        getSession().save(entity);
       // getSession().refresh(entity);
        return entity;
    }
 
    
    @SuppressWarnings("unchecked")
    public T saveOrUpdate(T entity) {
        getSession().saveOrUpdate(entity);
        flush();
       // getSession().refresh(entity);
        return entity;
    }
 
    public void makeTransient(T entity) {
        getSession().delete(entity);
    }
 
    public void flush() {
        getSession().flush();
    }
 
    public void clear() {
        getSession().clear();
    }
 
    /**
     * Use this inside subclasses as a convenience method.
     */
    @SuppressWarnings("unchecked")
    protected List<T> findByCriteria(Criterion... criterion) {
        Criteria crit = getSession().createCriteria(getPersistentClass());
        for (Criterion c : criterion) {
            crit.add(c);
        }
        return crit.list();
   }
 
    

	/**
	 * Lanza un 'delete' del objeto en la sesión
	 */
	public void delete(T obj) {
		Session sesion = getSession();				
		sesion.delete(obj);
			
	}
    
	
	public T get(ID id){
		Session sesion = getSession();
		T objeto = (T) sesion.get(getPersistentClass(),id);
		return objeto;		
	 }		
}
