package com.accenture.lkm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.accenture.lkm.bean.BillRangeBean;
import com.accenture.lkm.bean.PizzaOrderBean;
import com.accenture.lkm.exception.NoRecordFoundException;
import com.accenture.lkm.service.PizzaService;

@Controller
public class ReportController {

	@Autowired
	private PizzaService pizzaService;
	
	
	@RequestMapping(value="LoadBillRangeReport")
	public ModelAndView loadDateRangeReportPage(){
		ModelAndView andView = new ModelAndView("OrderReport");
		andView.addObject("billRangeObj", new BillRangeBean());
		return andView;
	}
	
	/**
	 To-Do Item 1.17: This method should save the details of PizzaOrderBean.
 		TODO:
 		--Map the URL to /FetchRecordsWithinBillRange.
 		--Retrieve BillRangeBean submitted by the view.
 		--Invoke getOrderDetails method of the PizzaService. 
 		--Create and populate a ModelAndView object with "OrderReport" as logical view name and add List<PizzaOrderBean> to it.
	 */
	
	@RequestMapping(value="/FetchRecordsWithinBillRange.html", method = RequestMethod.POST)
	
	public ModelAndView getOrderDetails(@ModelAttribute("billRangeObj")BillRangeBean billRangeBean) throws Exception{
		List<PizzaOrderBean> pizzaList=pizzaService.getOrderDetails(billRangeBean.getFromPrice(), billRangeBean.getToPrice());
		ModelAndView mv=new ModelAndView();
		mv.setViewName("OrderReport");
		mv.addObject("pizzaList", pizzaList);
		return mv;
		
	}
		
	
	/**
	To-Do Item 1.18: This method should handle NoRecordFoundException.
	TODO:
	  --Annotate the method with appropriate annotation to indicate it handles NoRecordFoundException.
	  --It should create a 'ModelAndView' object to render the view and populate data.
	  --It should set the view name to "NoRecordFoundException".This view will be responsible for displaying the error message to the user.
	  --It should add the exception message to the ModelAndView object with a key "message". This message will be accessible in the view(NoRecordFoundException.jsp) for display.
	  --Finally,it should return the populated ModelAndView object. 
	*/
	@ExceptionHandler(NoRecordFoundException.class)
	public ModelAndView handleNoRecordFoundException(Exception exception1){
		ModelAndView  modelAndView1 = new ModelAndView();
		modelAndView1.setViewName("NoRecordFoundException");
		modelAndView1.addObject("message", exception1.getMessage());
		modelAndView1.addObject("exception", exception1);
		return modelAndView1;
	}
	
	
	/**	
	 To-Do Item 1.19:  This method should handle common exceptions.
	  TODO:
	  --Annotate the method with appropriate annotation to indicate it handles Exception.
	  --It should create a 'ModelAndView' object to render the view and populate data.
	  --It should set the view name to "GeneralizedExceptionHandlerPage".This view will be responsible for displaying the error message to the user.
	  --It should add the exception message to the ModelAndView object with a key "message". This message will be accessible in the view(GeneralizedExceptionHandlerPage.jsp) for display.
	  --Finally,it should return the populated ModelAndView object. 	 
	 */		
	
	
	
	
		
		@ExceptionHandler(value=Exception.class)
		public ModelAndView handleAllExceptions(Exception exception){
		ModelAndView  modelAndView = new ModelAndView();
		modelAndView.setViewName("GeneralizedExceptionHandlerPage");
		modelAndView.addObject("message", exception.getMessage());
		modelAndView.addObject("exception", exception);
		return modelAndView;
			
		}
	
}
