<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		<h2>Add Pizza Order Details Success</h2>
		
		<!-- 		
			To-Do Item 1.16:
			TODO - Display a message showing order placed successfully
					NOTE: You can refer to To-Do Item 1.11 -->
		<h3>${message}</h3>
		<br><a href="${pageContext.request.contextPath}/Home.jsp">
			Home</a> 
	</center>
</body>
</html>