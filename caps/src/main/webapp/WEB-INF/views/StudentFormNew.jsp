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

<form:form method="POST" modelAttribute="student,user"
	action="${pageContext.request.contextPath}/admin/student/create.html">
		<center>
			<table cellpadding=4 cellspacing=2 border=0>
				<tr>
					<th width="45%">Description</th>
					<th width="55%">Detail</th>
				</tr>
				<tr>
				   <td><s:message code="label.student.studentID" />studentID</td>
				   <td><input name="user.userID"/>
				   <form:errors path="user.userID" cssStyle="color: red;" /></td>
				 </tr>
				<tr>
				   <td><s:message code="label.student.studentFirstName" />FirstName</td>
				   <td><input name="user.firstMidName"/>
				   <form:errors path="user.firstMidName" cssStyle="color: red;" /></td>
				 </tr>
				 <tr>
				   <td><s:message code="label.student.studentLastName" />LastName</td>
				   <td><input name="user.lastName"/>
				   <form:errors path="user.firstMidName" cssStyle="color: red;" /></td>
				 </tr>
				 <tr>
				   <td><s:message code="label.student.studentpassword" />password</td>
				   <td><input name="user.password"/>
				   <form:errors path="user.password" cssStyle="color: red;" /></td>
				 </tr>
				 <tr>
				   <td><s:message code="label.student.studentphone" />phone</td>
				   <td><input name="user.phone"/>
				   <form:errors path="user.phone" cssStyle="color: red;" /></td>
				 </tr>
				 <tr>
				   <td><s:message code="label.student.studentemail" />email</td>
				   <td><input name="user.email"/>
				   <form:errors path="user.email" cssStyle="color: red;" /></td>
				 </tr>
				<tr>
				   <td><s:message code="label.student.enrollmentDate" />enrollmentDate</td>
				   <td><input type = "date" name="student.enrollmentDate"/>
				   <form:errors path="student.enrollmentDate" cssStyle="color: red;" /></td>
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