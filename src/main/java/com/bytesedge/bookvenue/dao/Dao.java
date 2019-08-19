package com.bytesedge.bookvenue.dao;

import java.io.Serializable;
import java.util.List;

import com.bytesedge.bookvenue.common.DaoException;
import com.bytesedge.bookvenue.model.Persistent;

public abstract interface Dao {	
	
	public Persistent saveOrUpdate(Persistent perObj) throws DaoException;
	
	public Persistent saveOrUpdate(String entityName, Persistent perObj) throws DaoException;
	
	public void delete(Persistent perObj) throws DaoException;
	
	public void flush() throws DaoException;
	
	public void merge(Persistent perObj) throws DaoException;

	public Persistent get(Class<? extends Persistent> entityClass, Serializable id) throws DaoException;
	
	public List<Persistent> getAll(Class<? extends Persistent> entityClass) throws DaoException;
}