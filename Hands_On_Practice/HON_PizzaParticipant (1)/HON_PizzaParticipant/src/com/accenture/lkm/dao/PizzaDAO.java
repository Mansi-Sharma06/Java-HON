package com.accenture.lkm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.transaction.annotation.Transactional;

import com.accenture.lkm.entity.PizzaEntity;

@RepositoryDefinition(idClass = Integer.class, domainClass = PizzaEntity.class)
@Transactional(value = "txManager")
public interface PizzaDAO {
	
	/**
	    To-Do Item 1.2:: This method should retrieve all Pizza details.
	 	TODO:
	 	--Annotate the method with @Query to use JPQL query defined in orm.xml.
	 */
	@Query(name="PIZZA.findAllPizzaDetails")
	public List<PizzaEntity> findAllPizzaDetails() throws Exception;
	
}
