<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<center>
		<h2>Orders within Price Range Report</h2>		
		<form:form modelAttribute="billRangeObj" action="FetchRecordsWithinBillRange.html" >
		
		<!-- 
		To-Do Item 1.20: Complete the OrderReport.jsp page using Spring MVC Form Tag library:
		TODO: --Create text box to input "fromPrice" and "toPrice" field.
			  --Create a submit button with label "Fetch Details".	
			  --When user clicks on the submit button, order details should be displayed on the same page.
			  --Check if the data is available before displaying it.  
			  --If no data is available appropriate error message should be displayed. 		 
		 -->
		
			<table border="2">
					<tr>
					<td>From Price: </td>
					<td><form:input path="fromPrice"/></td>
					<td>To Price: </td>
					<td><form:input path="toPrice"/></td>
				</tr>		
			</table>
			<br>
			<!-- Add submit button here -->
			<input type ="submit" value ="Fetch Details">
			<br>
			<br>
			<br>
			<c:if test="${not empty pizzaList}">
<table border="2">
<tr>
<th>Order Id</th><th>Customer Name</th><th>Pizza Id</th><th>Bill</th><th>Quantity</th>
</tr>
<c:forEach var="pizzaOrder" items="${pizzaList}">
<tr>
<td>${pizzaOrder.orderId}</td>
<td>${pizzaOrder.customerName}</td>
<td>${pizzaOrder.pizzaId}</td>
<td>${pizzaOrder.bill}</td>
<td>${pizzaOrder.numberOfPiecesOrdered}</td>
</tr>
</c:forEach>
</table>
</c:if>
<%-- <c:if test="${empty pizzaList }">
<h2>No Orders Found</h2>
</c:if> --%>
<br>
			
			<a href="${pageContext.request.contextPath}/Home.jsp">
			Home</a>
		</form:form>

	</center>
</body>
</html>