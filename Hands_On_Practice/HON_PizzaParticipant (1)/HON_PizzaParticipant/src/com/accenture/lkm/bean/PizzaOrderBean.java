package com.accenture.lkm.bean;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;


public class PizzaOrderBean {
	
	/**	 
	 To-Do Item 1.14: Use Spring MVC standard validation
	 TODO:
	 	-- Ensure pizzaId is a mandatory field.
	 	-- Validate customer name should have length between 3 and 30.
	 	-- Validate contact number length should be 10.
	 	-- Ensure number of pieces ordered is a mandatory field and minimum quantity is 1.	 
	 */
		
	private Integer orderId;	
    @NotNull//(message=" ")
	private Integer pizzaId;
    @NotEmpty
    @Size(min=3,max=30)//,message=" ")
	private String customerName;
    @NotEmpty
    @Size(min=10,max=10)//,message=" ")
	private String contactNumber;	
	private Double bill;
	@NotNull// (message=" ")
	@Range(min=1)
	private Integer numberOfPiecesOrdered;
	
	public PizzaOrderBean() {
		super();
	}
	
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getPizzaId() {
		return pizzaId;
	}
	public void setPizzaId(Integer pizzaId) {
		this.pizzaId = pizzaId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public Double getBill() {
		return bill;
	}
	public void setBill(Double bill) {
		this.bill = bill;
	}
	public Integer getNumberOfPiecesOrdered() {
		return numberOfPiecesOrdered;
	}
	public void setNumberOfPiecesOrdered(Integer numberOfPiecesOrdered) {
		this.numberOfPiecesOrdered = numberOfPiecesOrdered;
	}
	@Override
	public String toString() {
		return "PizzaOrderBean [orderId=" + orderId + ", pizzaId=" + pizzaId
				+ ", customerName=" + customerName + ", contactNumber="
				+ contactNumber + ", bill=" + bill + ", numberOfPiecesOrdered="
				+ numberOfPiecesOrdered + "]";
	}
	
	
}
