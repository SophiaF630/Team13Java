<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<div class="row" style="color: black;">
			<div class="col-xs-12">
				<h1>Lecturer Course Details</h1>
			</div>
		</div>
		<table class="cell-border" style="width: 100%" id="courseDetails">
			<thead>
				<tr>
				<th><s:message code="No" /></th>
					<th><s:message code="CourseID" /></th>
					<th><s:message code="Class" /></th>
				</tr>
			<thead>
			<tbody>
						<c:forEach var="Lecturercourse" items="${Lecturercourse}"
							varStatus="index">
							<tr class="${status.index%2==0?'even':'odd'}">
							<td>${index.index+1}</td>
								<td class="nowrap">${Lecturercourse.id.getCourseIndex()}</td>
								<td ><a
									href="${pageContext.request.contextPath}/lecturer/course/${Lecturercourse.id.getCourseIndex()}.html">
									<s:message code="View" /></a></td>
							</tr>
						</c:forEach>
					</tbody>
		</table>

	</div>
</body>
</html>