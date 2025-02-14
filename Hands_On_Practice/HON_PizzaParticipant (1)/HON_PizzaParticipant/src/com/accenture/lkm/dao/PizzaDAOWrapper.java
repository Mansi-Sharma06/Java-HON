package com.accenture.lkm.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.accenture.lkm.bean.PizzaBean;
import com.accenture.lkm.bean.PizzaOrderBean;
import com.accenture.lkm.entity.PizzaEntity;
import com.accenture.lkm.entity.PizzaOrderEntity;

@Repository
@Transactional(value = "txManager")
public class PizzaDAOWrapper {

	@Autowired
	private PizzaDAO pizzaDAO;

	@Autowired
	private PizzaOrderDAO pizzaOrderDAO;

	
	@PersistenceContext
	private EntityManager entityManager;
	
	
	
	/**
	  To-Do Item 1.4: This method should retrieve all Pizza details. 
	  			NOTE:This requirement needs to be implemented using Spring JPA Data
	  TODO:
	  	--Invoke findAllPizzaDetails method of PizzaDAO to get details of pizza.
	 	--Convert all PizzaEntities to PizzaBeans and store them in a list.(Hint: Utilize provided utility methods)
	 	--Return the list of PizzaBeans.	 		 
	 */
	public List<PizzaBean> findAllPizzaDetails()throws Exception {		
		
	List<PizzaBean>pizzaList = null;
		
	List<PizzaEntity>pizzaEntitiesList = pizzaDAO.findAllPizzaDetails();
	
	if (!pizzaEntitiesList.isEmpty()||pizzaEntitiesList != null) {
		pizzaList=new ArrayList<PizzaBean>();
		for(PizzaEntity entity:pizzaEntitiesList) {
			
			PizzaBean bean =convertPizzaEntityToBean(entity);
			pizzaList.add(bean);
			
			
		}
		
	}
	else 
		System.out.println("Find All Pizza Detail method did not found anything");
	return pizzaList;
	}
	
	
	
	/**
	  To-Do Item 1.5: This method should add Pizza order details to database.
	  		NOTE: This requirement needs to be implemented using Spring JPA Data.
	  TODO:
	  	--Convert PizzaOrderBean to PizzaOrderEntity (Hint: Utilize provided utility methods)
	  	--Invoke the save method of the PizzaOrderDAO interface to persist the order.
	  	--Convert the saved PizzaOrderEntity back to a PizzaOrderBean
	  	--Return the details of the newly added PizzaOrderBean.	
	 */
	public PizzaOrderBean addPizzaOrderDetails(PizzaOrderBean pizzaOrderBean) throws Exception{
		
		PizzaOrderEntity entity=convertPizzaOrderBeanToEntity(pizzaOrderBean);
		entity=pizzaOrderDAO.save(entity);
		//pizzaOrderBean.setOrderId(entity.getOrderId());
		PizzaOrderBean bean=convertPizzaOrderEntityToBean(entity);
		return bean;
	}
	
	/**
	  To-Do Item 1.6: This method should retrieve price for a given Pizza.
	  	NOTE:This requirement needs to implemented using JPA EntityManager.	  		
	  TODO:
	   	--Invoke the appropriate method using EntityManager to fetch Pizza details by using pizzaId.
	  	--Retrieve the price of the Pizza and return it.	
	 */
	
	public Double getPizzaPrice(Integer pizzaId) throws Exception{	
		PizzaEntity pizzaEntity = entityManager.find(PizzaEntity.class,pizzaId);
		
		return pizzaEntity.getPrice();
	}
	
	
	/**
	 To-Do Item 1.7: This method should fetch all order details between given bill amount range.
	 	Note: This requirement needs to implemented using JPA EntityManager. 	 		   
	 TODO:
 		--Write a valid JPQL query to fetch all Pizza orders between the specified bill amount range.
 		--Use positional/Named parameters in the above JPQL query to bind fromBill and toBill values.
 		--Execute the query to retrieve a list of PizzaOrderEntities.
 		--Iterate over the retrieved PizzaOrderEntities, converting each entity to its corresponding Bean and add to the list.
 		--Return a list of PizzaOrderBeans containing the details of orders within the specified bill range..	 	 
	 */
	public List<PizzaOrderBean> getOrderDetails(Double fromBill, Double toBill) throws Exception{
		Query query = entityManager.createQuery("select k from PizzaOrderEntity k where k.bill between '"+fromBill+"' and'"+toBill+"'");
		List<PizzaOrderEntity>pizzaOrderEntity=new ArrayList<PizzaOrderEntity>();
		pizzaOrderEntity=query.getResultList();
		List<PizzaOrderBean>pizzaOrderBean=new ArrayList<PizzaOrderBean>();
		if (pizzaOrderEntity !=null) {
			for(PizzaOrderEntity each:pizzaOrderEntity) {
				pizzaOrderBean.add(convertPizzaOrderEntityToBean(each));
			}
		}
		else
			System.out.println("Function did not find any data");
		return pizzaOrderBean;
	}
	
	
	//Utility Methods.......
	public static PizzaOrderBean convertPizzaOrderEntityToBean(PizzaOrderEntity entity){
		PizzaOrderBean pizzaOrderBean = new PizzaOrderBean();
		BeanUtils.copyProperties(entity, pizzaOrderBean);
		return pizzaOrderBean;
	}
	public static PizzaOrderEntity convertPizzaOrderBeanToEntity(PizzaOrderBean bean){
		PizzaOrderEntity entity = new PizzaOrderEntity();
		BeanUtils.copyProperties(bean,entity);
		return entity;
	}
	
	
	//Utility Methods.......
		public static PizzaBean convertPizzaEntityToBean(PizzaEntity entity){
			PizzaBean pizzaBean = new PizzaBean();
			BeanUtils.copyProperties(entity, pizzaBean);
			return pizzaBean;
		}
		public static PizzaEntity convertPizzaBeanToEntity(PizzaBean bean){
			PizzaEntity entity = new PizzaEntity();
			BeanUtils.copyProperties(bean,entity);
			return entity;
		}
}
