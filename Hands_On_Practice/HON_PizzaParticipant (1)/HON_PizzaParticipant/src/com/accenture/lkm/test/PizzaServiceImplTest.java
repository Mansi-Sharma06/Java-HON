package com.accenture.lkm.test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.accenture.lkm.bean.PizzaBean;
import com.accenture.lkm.bean.PizzaOrderBean;
import com.accenture.lkm.dao.PizzaDAOWrapper;
import com.accenture.lkm.exception.NoRecordFoundException;
import com.accenture.lkm.service.PizzaServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:WebContent/WEB-INF/root-config.xml"})
@Transactional
public class PizzaServiceImplTest {
	
  @Autowired
  private PizzaServiceImpl pizzaServiceImpl;
  
  /**
   * 	
   * To-Do Item 1.25: This method tests the getOrderDetailsWithRecordsFound functionality in PizzaServiceImpl
     by retrieving pizza orders within a specified bill range from the database.
     TODO: Implement the testGetOrderDetailsWithRecordsFound method:
        --Prepare test data by setting valid fromBill and toBill values that exists in database.
        --Invoke the getOrderDetails method of pizzaServiceImpl, passing the fromBill and toBill..
        --Verify that PizzaOrder exists within the bill range.
   */
  @Test
  public void testGetOrderDetailsWithRecordsFound() throws Exception {
	    double fromBill = 200.0;
		double toBill = 800.0;
	//	pizzaServiceImpl.getOrderDetails(fromBill, toBill);
		List<PizzaOrderBean> result = pizzaServiceImpl.getOrderDetails(fromBill, toBill);
		Assert.assertNotNull(result);
		Assert.assertTrue(result.size() > 0);
		for (PizzaOrderBean order : result) {
		    Assert.assertTrue(order.getBill() >= fromBill && order.getBill() <= toBill);
		}
  }
  /**
   * 	
   * To-Do Item 1.26: This method tests the getOrderDetailsWithNoRecordsFound functionality in PizzaServiceImpl
     by retrieving pizza orders within a specified bill range from the database when no records are found.
     TODO: Implement the testGetOrderDetailsWithNoRecordsFound method:
        --Set invalid fromBill and toBill values that do not correspond to any records in the database.
        --Verify that the NoRecordFoundException is thrown when invoking the getOrderDetails method of pizzaServiceImpl.
        --Verify that the message of the thrown exception matches the expected message: "No records were found for the entered Bill Range".
   */
 
  @Test
  public void testGetOrderDetailsWithNoRecordsFound() throws Exception {
	    double fromBill = 5000.0;
		double toBill = 8000.0;
		try
		{
			pizzaServiceImpl.getOrderDetails(fromBill, toBill);
			//fail("NoRecordFoundException should have been thrown.");
		}
		catch(NoRecordFoundException e)
		{
			Assert.assertEquals("No records were found for the entered Bill Range", e.getMessage());
		}
		
//		NoRecordFoundException exception = assertThrows(NoRecordFoundException.class, () -> {
//        pizzaServiceImpl.getOrderDetails(fromBill, toBill);});
//		Assert.assertEquals("No records were found for the entered Bill Range", exception.getMessage());
  }
  /**
   * 	
   * To-Do Item 1.27: This method tests the transactional behavior of adding a pizza order
     in PizzaServiceImpl by verifying that no changes are made to the database when adding a pizza order.
     TODO: Implement the testTransactionalBehavior method:
        --Prepare test data by setting valid fromBill and toBill values that exist in the database.
        --Retrieve the initial list of pizza orders using getOrderDetails method of pizzaServiceImpl.
        --Record the initial number of orders
        --Add a new pizza order using addPizzaOrderDetails method of pizzaServiceImpl.
        --Retrieve the final list of pizza orders using getOrderDetails method of pizzaServiceImpl.
        --Record the final number of orders.
        --Verify that the initial and final number of orders are equal, indicating that no changes were made to the database.
   */
  @Test
  public void testTransactionalBehavior() throws Exception {
	    double fromBill = 200.0;
		double toBill = 800.0;
		List<PizzaOrderBean> initialOrders = pizzaServiceImpl.getOrderDetails(fromBill, toBill);
		int initialOrderCount = initialOrders.size();
		PizzaOrderBean newPizzaOrder = new PizzaOrderBean();
		newPizzaOrder.setCustomerName("Shyam");
		newPizzaOrder.setContactNumber("6789067890");
		newPizzaOrder.setNumberOfPiecesOrdered(5);
		newPizzaOrder.setBill(550.0);
		newPizzaOrder.setPizzaId(1005);
		pizzaServiceImpl.addPizzaOrderDetails(newPizzaOrder);
		List<PizzaOrderBean> finalOrders = pizzaServiceImpl.getOrderDetails(fromBill, toBill);
		int finalOrderCount = finalOrders.size();
		Assert.assertEquals(initialOrderCount, finalOrderCount);
	 }}