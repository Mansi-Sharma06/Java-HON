package com.accenture.lkm.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.lkm.bean.PizzaBean;
import com.accenture.lkm.bean.PizzaOrderBean;
import com.accenture.lkm.dao.PizzaDAOWrapper;
import com.accenture.lkm.exception.NoRecordFoundException;

@Service
public class PizzaServiceImpl implements PizzaService{
	
	@Autowired
	private  PizzaDAOWrapper pizzaDAOWrapper;

	/*
		To-Do Item 1.8: This method should fetch all Pizza details.
		TODO:
			--Invoke the getOrderDetails method of pizzaDAOWrapper.
			--Return all pizza order details.
			--If no order exists within the given range then throw a custom exception as NoRecordFoundException.
	 */
	@Override
	
	
	
	public List<PizzaOrderBean> getOrderDetails(Double fromBill,Double toBill) throws Exception {
		List<PizzaOrderBean> pizzaOrderBean = pizzaDAOWrapper.getOrderDetails(fromBill, toBill);
		if(pizzaOrderBean.isEmpty() || pizzaOrderBean==null) {
			throw new NoRecordFoundException("No records were found for the entered Bill Range");
		}
		return pizzaOrderBean;
	}
 

	
	/* 
		To-Do Item 1.9: This method should add Pizza details.
		TODO:
			--Invoke getPizzaPrice of pizzaDAOWrapper to get the price of the selected Pizza.
			--Calculate the total bill using the unit price and the number of pieces ordered. 
			--Set the calculated bill value for the PizzaOrderBean.
			--Invoke the addPizzaOrderDetails of pizzaDAOWrapper and return the PizzaOrderBean object.
	 */
	@Override
	public PizzaOrderBean addPizzaOrderDetails(PizzaOrderBean pizzaOrderBean)throws Exception {
		double price = pizzaDAOWrapper.getPizzaPrice(pizzaOrderBean.getPizzaId());
		pizzaOrderBean.setBill(price*pizzaOrderBean.getNumberOfPiecesOrdered());
		
		return pizzaDAOWrapper.addPizzaOrderDetails(pizzaOrderBean);
	}
	
	
	/*
	 To-Do Item 1.10: This method should find all Pizza details.
		TODO:
	 		--Invoke the findAllPizzaDetails of pizzaDAOWrapper to get all Pizza details.
	 		--Populate a Map with pizza id as key and pizza name as value.
	 		--Return the populated map.
	 */
	@Override
	public Map<Integer,String> findAllPizzaDetails() throws Exception {
		Map<Integer,String> map= new HashMap<Integer,String>();
		List<PizzaBean> pizzaBean=new ArrayList<PizzaBean>();
		pizzaBean=pizzaDAOWrapper.findAllPizzaDetails();
		if(pizzaBean!=null) {
			for(PizzaBean each:pizzaBean) {
				map.put(each.getPizzaId(),each.getPizzaName());
			}
		}
		
				return map;
		
	}
}
