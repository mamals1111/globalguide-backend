package com.myrmicatech.globalguidebackend.service.generic;

import com.myrmicatech.globalguidebackend.exception.ServiceException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * @param <T> entity type
 */
public abstract class GlobalGuideEntityServiceImpl<K extends Serializable & Comparable<K>, E>
	implements GlobalGuideEntityService<K, E> {
	
	/**
	 * Classe de l'entité, déterminé à partir des paramètres generics.
	 */
	private Class<E> objectClass;


    private JpaRepository<E, K> repository;

	@SuppressWarnings("unchecked")
	public GlobalGuideEntityServiceImpl(JpaRepository<E, K> repository) {
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
		this.objectClass = (Class<E>) genericSuperclass.getActualTypeArguments()[1];
		this.repository = repository;
	}
	
	protected final Class<E> getObjectClass() {
		return objectClass;
	}


	public E getById(K id) {
		return repository.getOne(id);
	}

	
	public void save(E entity) throws ServiceException {
		repository.saveAndFlush(entity);
	}
	
	public void saveAll(Iterable<E> entities) throws ServiceException {
		repository.saveAll(entities);
	}
	
	
	public void create(E entity) throws ServiceException {
		save(entity);
	}

	
	
	public void update(E entity) throws ServiceException {
		save(entity);
	}
	

	public void delete(E entity) throws ServiceException {
		repository.delete(entity);
	}
	
	
	public void flush() {
		repository.flush();
	}
	

	
	public List<E> list() {
		return repository.findAll();
	}
	

	public Long count() {
		return repository.count();
	}
	
	protected E saveAndFlush(E entity) {
		return repository.saveAndFlush(entity);
	}

}