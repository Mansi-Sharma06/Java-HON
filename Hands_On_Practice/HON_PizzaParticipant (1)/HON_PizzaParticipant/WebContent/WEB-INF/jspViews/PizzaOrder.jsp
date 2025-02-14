<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration Page</title>
<style>
.error {
	color: #ff0000;
	font-style: italic;
}
</style>
</head>
<body>
	<center>
			
		<!-- 
		To-Do Item 1.15: Complete the PizzaOrder.jsp page using Spring MVC Form Tag library:
		TODO: --Create text box to input customer name field.
			  --Create text box to input contact number field.		      
		      --Create a drop down list to populate pizza names dynamically.
		      --Create text box to input number of pizza to order as quantity.	
		      --Display form validation errors if any.	      
		      --Create a submit button with label Order.   		    	
		-->
			
	<h2>Add Pizza Details</h2>
		<form:form modelAttribute="pizzaOrderObj" method="POST"
			action="${pageContext.request.contextPath}/SavePizzaOrder.html">

			<br>

			<table border="2">
<tr>
<td>Customer Name</td>
<td><form:input path="customerName"/></td>

</tr>
<tr>
<td>Contact Number</td>
<td><form:input path="contactNumber"/></td>

</tr>
<tr>
<td>Pizza Name</td>
<td>
<form:select path="pizzaId">
<form:option value="" label="--Select--"/>
<form:options items="${pizzaMap}"/>
</form:select>
</td>
</tr>
<tr>
<td>Quantity</td>
<td><form:input path="numberOfPiecesOrdered"/></td>

</tr>
</table>
<br>
<br>
<input type="submit" value="ORDER">
<br><a href="${pageContext.request.contextPath}/Home.jsp">
			Home</a> <br>
			<br>
			<br>
<spring:hasBindErrors name="pizzaOrderBean">
<h2>All Errors</h2>
</spring:hasBindErrors>


<form:errors path="numberOfPiecesOrdered" cssClass="error"/>
<form:errors path="customerName" cssClass="error"/>
<form:errors path="contactNumber" cssClass="error"/>
<form:errors path="pizzaId" cssClass="error"/>

</form:form>
	</center>
</body>
</html>