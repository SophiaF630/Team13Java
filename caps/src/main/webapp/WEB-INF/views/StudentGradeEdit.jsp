<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="<c:url value='/styles/style.css'/>" rel="stylesheet"
	type="text/css" />
</head>
<body>

<form:form method="POST" modelAttribute="studentcoursedetails"
	action="${pageContext.request.contextPath}/lecturer/edit/${studentcourse.id.getStudent_StudentID()}/${studentcourse.id.getCourseIndex()}.html">
		<center>
			<table cellpadding=4 cellspacing=2 border=0>
				<tr>
					<th width="45%">Description</th>
					<th width="55%">Detail</th>
				</tr>
				<tr>
				   <td><s:message code="courseIndex" /> </td>
				   <td><form:input path="id.courseIndex" readonly="true"/></td>
				 </tr>
				<tr>
				   <td><s:message code="StudentID" /></td>
				   <td><form:input path="id.courseIndex" readonly="true"/></td>
				 </tr>
				<tr>
				   <td><s:message code="CAGrade" /> </td>
				   <td><form:input path="CAGrade" /></td>
				 </tr>
				<tr>
				   <td><s:message code="ExamGrade" /></td>
				   <td><form:input path="examGrade"/></td>
				 </tr>
				<tr>
				   <td><s:message code="Status" /></td>
				   <td><form:input path="status"/></td>
				 </tr>
				 <tr>
				 <tr>
				   <td><s:message code="Enrolled time" /></td>
				   <td><form:input path="enrollTime"/></td>
				 </tr>
				 <tr>
				 <td><input type="submit" value="Submit"> </td>
				 <td><input type="reset" value="Reset"></td>
				 </tr>
		</table>
		</center>
	
	</form:form>
</body>
</html>