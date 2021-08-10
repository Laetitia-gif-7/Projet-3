package fr.eql.ai109.projet3.idao;

import java.util.List;

public interface GenericIDao<T> {

	T add(T t);
	void delete(T t);
	T update(T t);
	T getById(int i);
	List<T> getAll();
	// refresh, maybe convenient ?
	void refresh(T t);
	//void refresh(T t, Map<String, O>);
	
}
