package com.bytesedge.bookvenue.service;

import java.io.Serializable;
import java.util.List;

import com.bytesedge.bookvenue.common.DaoException;
import com.bytesedge.bookvenue.model.Persistent;

public interface BaseService {
	/**
	 * Generic method to save or update any persisting entity in the system.
	 * IPORTANT: Users SHOULD NOT WRITE THE ENTITY SPECIFIC method in the respective service layer 
	 *            unless there is any custom code required
	 *  
	 * @param perObj
	 * @throws DaoException
	 */
	public Persistent saveOrUpdate(Persistent perObj) throws DaoException;

	/**
	 * Generic method to removed any persisted entity supplied to this. 
	 * IPORTANT: User SHOULD NOT WRITE THE ENTITY SPECIFIC method in the respective service layer 
	 *            unless there is any custom code required
	 * @param perObj
	 * @throws DaoException
	 */
	public void delete(Persistent perObj) throws DaoException;
	
	/**
	 * Flushes the session content into the database 
	 * @param perObj
	 * @throws DaoException
	 */
	public void flush() throws DaoException;
	
	/**
	 * Merge the supplied object with the data in the session and pushes to database 
	 * IPORTANT: User SHOULD NOT WRITE THE ENTITY SPECIFIC method in the respective service layer 
	 *           unless there is any custom code required
	 * @param perObj
	 * @throws DaoException
	 */
	public void merge(Persistent perObj) throws DaoException;


	/**
	 * Return the object saved in persistent system given the entity class and Serializable unique ID of the object 
	 * IPORTANT: User SHOULD NOT WRITE THE ENTITY SPECIFIC method in the respective service layer 
	 *           unless there is any custom code required
	 * @param perObj
	 * @throws DaoException
	 */
	public Persistent get(Class entityClass, Serializable id) throws DaoException;
	
	/**
	 * Return all objects saved in persistent system given the entity class 
	 * IPORTANT: User SHOULD NOT WRITE THE ENTITY SPECIFIC method in the respective service layer 
	 *           unless there is any custom code required
	 * @param perObj
	 * @throws DaoException
	 */
	public List<Persistent> getAll(Class entityClass) throws DaoException;
}
