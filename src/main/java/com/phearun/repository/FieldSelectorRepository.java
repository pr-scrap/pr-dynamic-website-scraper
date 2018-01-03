package com.phearun.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.phearun.model.FieldSelector;

@Transactional
@Repository
public class FieldSelectorRepository {

	@PersistenceContext
	private EntityManager em;
	
	public int removeFieldSelectorByWebsiteId(Integer id){
		return em.createQuery("delete from FieldSelector fs where fs.dynamicWebsite.id = :id")
		  .setParameter("id", id)
		  .executeUpdate();
	}
	
	public FieldSelector findOne(Integer id){
		return em.find(FieldSelector.class, id);
	}
	
}
