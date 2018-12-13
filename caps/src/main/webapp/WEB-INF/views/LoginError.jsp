<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="ISO-8859-1">
<title>Error</title>

<form:form action="${pageContext.request.contextPath}/login/error.html"/>
Invalid credentials!
</head>
<body>
<%-- <form:form action="${pageContext.request.contextPath}/login/admin.html"
		method="POST" ModelAttribute="user">
		</form:form>
		<h1>hello well done</h1> --%>	
		
		
</body>
</html>
