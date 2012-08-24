package com.chinarewards.posnet2.report.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class BaseDao<T> extends HibernateDaoSupport {

	protected static String COUNT = "count";
	protected static String SEARCH = "search";

	protected Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * persist T to DB .
	 * 
	 * @param t
	 *            one of your entity bean instance
	 */
	public void save(T t) {
		this.getHibernateTemplate().save(t);
	}

	/**
	 * meger modification to DB.
	 * 
	 * @param t
	 *            one of your entity bean instance
	 */
	public void update(T t) {
		this.getHibernateTemplate().update(t);
	}

	/**
	 * 
	 * delete t to DB.
	 * 
	 * @param t
	 */
	public void delete(T t) {
		this.getHibernateTemplate().delete(t);
	}

	/**
	 * 
	 * merge t to DB.
	 * 
	 * @param t
	 */
	public void merge(T t) {
		this.getHibernateTemplate().merge(t);
	}

	/**
	 * Get the entity bean by id(if the object has not found then throws
	 * Exception )
	 * 
	 * @param t
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public T getById(Class<T> cl, String id) {
		Object result = this.getHibernateTemplate().get(cl, id);
		return (T) result;
	}

	/**
	 * 
	 * @param cl
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public T loadById(Class<T> cl, String id) {
		Object result = this.getHibernateTemplate().load(cl, id);
		return (T) result;
	}

	/**
	 * Remove the entity bean from hibernate cache.
	 * 
	 * @param t
	 */
	public void evict(T t) {
		this.getHibernateTemplate().evict(t);
	}

	protected String getEntityName(Class<T> clazz) {
		return clazz.getSimpleName();
	}

}