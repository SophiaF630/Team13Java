<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="<c:url value='/css/style.css'/>" rel="stylesheet"
	type="text/css" />
</head>
<body>
	<h1>This is Lecturer-Information Edit Page</h1>
	
	
	<form:form
		action="${pageContext.request.contextPath }/admin/lecturer/edit/${user.userID}.html"
		method="POST" modelAttribute="user">
		
		<center>
			<table cellpadding=4 cellspacing=2 border=0>
			<tr>
				<th width="45%">Description</th>
				<th width="55%">Detail</th>
			</tr>
			<tr>
				   <td><s:message code="FirstMidName" /> *</td>
				   <td><form:input path="firstMidName"/>
				   <form:errors path="firstMidName" cssStyle="color: red;" /></td>
			</tr>
						<tr>
				   <td><s:message code="LastName" /> *</td>
				   <td><form:input path="lastName"/>
				   <form:errors path="lastName" cssStyle="color: red;" /></td>
			</tr>
						<tr>
				   <td><s:message code="Phone" /> *</td>
				   <td><form:input path="phone"/>
				   <form:errors path="phone" cssStyle="color: red;" /></td>
			</tr>
						<tr>
				   <td><s:message code="Email" /> *</td>
				   <td><form:input path="email"/>
				   <form:errors path="email" cssStyle="color: red;" /></td>
			</tr>
			
				<tr>
					<td><input type="submit" value="Submit"></td>
					<td><input type="reset" value="Reset"></td>
				</tr>
			</table>
		</center>
	</form:form>
	
</body>
</html>