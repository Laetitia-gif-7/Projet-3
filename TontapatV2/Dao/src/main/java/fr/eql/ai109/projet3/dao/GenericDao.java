package fr.eql.ai109.projet3.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import fr.eql.ai109.projet3.idao.GenericIDao;

public abstract class GenericDao<T> implements GenericIDao<T> {

	@PersistenceContext(unitName = "PUTontapatV2")
	protected EntityManager entityManager;
	
	@Override
	public T add(T t) {
		entityManager.persist(t);
		return t;
	}

	@Override
	public void delete(T t) {
		entityManager.remove(t);
		
	}

	@Override
	public T update(T t) {
		System.out.println("contains: " + entityManager.contains(t));
		entityManager.merge(t);
		System.out.println("contains: " + entityManager.contains(t));
		return t;
	}

	@Override
	public T getById(int i) {
		T t = null;
        try {
			String className = ((ParameterizedType)getClass().getGenericSuperclass())
			.getActualTypeArguments()[0].getTypeName();
			Class<?> clazz;
			clazz = Class.forName(className);
            t= (T)entityManager.find(clazz, i);
        } catch ( Exception e ) {
        	e.printStackTrace();
        }
		return t;
	}

	@Override
	public List<T> getAll() {
		List<T> objects = null;
		try {
			String className = ((ParameterizedType)getClass().getGenericSuperclass())
			.getActualTypeArguments()[0].getTypeName();
			Class<?> clazz;
			clazz = Class.forName(className);
            TypedQuery<T> query = (TypedQuery<T>) entityManager.createQuery("FROM "+ clazz.getName());
            return query.getResultList();
        } catch ( Exception e ) {
        	e.printStackTrace();
        }
		return objects;
	}

}
