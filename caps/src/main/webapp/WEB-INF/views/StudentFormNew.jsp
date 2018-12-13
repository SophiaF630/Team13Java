<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link href="<c:url value='/css/style.css'/>" rel="stylesheet"
	type="text/css" />

<%-- <link rel="STYLESHEET" type="text/css"
	href="${pageContext.request.contextPath}/js/jquery-ui.theme.css" />
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
<script>
	$(document).ready(function() {
		$("#datepicker1").datepicker({
			dateFormat : "dd/mm/yy"
		});
	});
</script> --%>


<body>

	<div class="container">
		<div class="row" style="color: black;">
			<div class="col-xs-12">
				<h1>Create New Student</h1>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-12">
				<center>
					<table cellpadding=4 cellspacing=2 border=0>
						<tr>
							<td width="100%"><form:form method="POST"
									modelAttribute="student"
									action="${pageContext.request.contextPath}/admin/student/create.html">
									<table cellpadding=4 cellspacing=2 border=0>
										<tr>
											<th width="45%">Description</th>
											<th width="55%">Detail</th>
										</tr>
										<tr>
											<td><s:message code="label.student.studentID" /></td>
											<td>${newStudent.studentID}</td>
										</tr>
										<tr>
											<td><s:message code="label.student.enrollmentDate" /></td>
											<td><input type="date" name="student.enrollmentDate" />
												<errors path="student.enrollmentDate" cssStyle="color: red;" /></td>
										</tr>
										<%-- 	<tr>
								<td><s:message code="label.student.enrollmentDate" /></td>
								<td><form:input size="16" path="student.enrollmentDate" id="datepicker1"/> <form:errors
										path="student.enrollmentDate" cssStyle="color: red;" /></td>
							</tr> --%>
									</table>
								</form:form> <form:form method="POST" modelAttribute="user"
									action="${pageContext.request.contextPath}/admin/student/create.html">
									<table cellpadding=4 cellspacing=2 border=0>
										<tr>
											<td><s:message code="label.student.firstMidName" /></td>
											<td><input name="user.firstMidName" /> <form:errors
													path="user.firstMidName" cssStyle="color: red;" /></td>
										</tr>
										<tr>
											<td><s:message code="label.student.lastName" /></td>
											<td><input name="user.lastName" /> <form:errors
													path="user.firstMidName" cssStyle="color: red;" /></td>
										</tr>
										<tr>
											<td><s:message code="label.user.phone" /></td>
											<td><input name="user.phone" /> <form:errors
													path="user.phone" cssStyle="color: red;" /></td>
										</tr>
										<tr>
											<td><s:message code="label.user.email" /></td>
											<td><input name="user.email" /> <form:errors
													path="user.email" cssStyle="color: red;" /></td>
										</tr>
										<tr>
											<td><s:message code="label.user.address" /></td>
											<td><input name="user.address" /> <form:errors
													path="user.address" cssStyle="color: red;" /></td>
										</tr>
										<tr>
											<td><s:message code="label.user.password" /></td>
											<td><input name="user.password" /> <form:errors
													path="user.password" cssStyle="color: red;" /></td>
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

