<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<link href="<c:url value='/styles/style.css'/>" rel="stylesheet"
	type="text/css" />
<head>

</head>
<body>
	
		<div class="container">
			<div class="row" style="color: black;">
				<div class="col-xs-12">
					<h1>Edit Student Information</h1>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-12">
				<center>
					<table cellpadding=4 cellspacing=2 border=0>
						<tr>
							<td width="100%"><form:form method="POST"
									modelAttribute="student"
									action="${pageContext.request.contextPath}/admin/student/edit/${student.studentID}.html">
									<table cellpadding=4 cellspacing=2 border=0>
										<tr>
											<th width="50%">Description</th>
											<th width="60%">Detail</th>
										</tr>
										<tr>
											<td><s:message code="label.student.studentID" />
												*Readonly</td>
											<td><form:input path="studentID" readonly="true" /></td>
										</tr>
										<tr>
											<td><s:message code="label.student.enrollmentDate" /></td>
											<td><form:input path="enrollmentDate" /></td>
										</tr>
									</table>
								</form:form></td>
						</tr>
						<tr>
							<td width="100%"><form:form method="POST"
									modelAttribute="user"
									action="${pageContext.request.contextPath}/admin/student/edit/${student.studentID}.html">
									<table cellpadding=4 cellspacing=2 border=0>
										<tr>
											<td width="50%"><s:message code="label.student.lastName" /></td>
											<td width="60%"><form:input path="lastName" /></td>
										</tr>
										<tr>
											<td><s:message code="label.student.firstMidName" /></td>
											<td><form:input path="firstMidName" /></td>
										</tr>
										<tr>
											<td><s:message code="label.user.phone" /></td>
											<td><form:input path="phone" /></td>
										</tr>
										<tr>
											<td><s:message code="label.user.email" /></td>
											<td><form:input path="email" /></td>
										</tr>
										<tr>
											<td><s:message code="label.user.address" /></td>
											<td><form:input path="address" /></td>
										</tr>
										<tr>
											<td><input type="submit" value="Submit"></td>
											<td><input type="reset" value="Reset"></td>
										</tr>
									</table>
								</form:form></td>
						</tr>
					</table>
					</center>
				</div>
			</div>
		</div>
	
</body>
</html>