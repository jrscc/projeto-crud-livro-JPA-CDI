package br.edu.ifpb.mt.dac.dao;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public abstract class DAO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -87039751099137033L;
	
	@Inject
	private EntityManager em;
	
	protected EntityManager getEntityManager() {
		return em;
	}
}
