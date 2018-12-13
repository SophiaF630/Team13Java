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
				<h1>Class Details</h1>
			</div>
		</div>
		<table class="cell-border" style="width: 100%" id="courseDetails">
			<thead>
				<tr>
					<th><s:message code="courseIndex" /></th>
					<th><s:message code="StudentID" /></th>
					<th><s:message code="CAGrade" /></th>
					<th><s:message code="ExamGrade" /></th>
					<th><s:message code="status" /></th>
				</tr>
			<thead>
			<tbody>
						<c:forEach var="studentcourse" items="${studentcourse}"
							varStatus="status">
							<tr class="${status.index%2==0?'even':'odd'}">
								<td class="nowrap">${studentcourse.id.getCourseIndex()}</td>
								<td class="nowrap">${studentcourse.id.getStudent_StudentID()}</td>
								<td class="nowrap">${studentcourse.CAGrade}</td>
								<td class="nowrap">${studentcourse.examGrade}</td>
								<td class="nowrap">${studentcourse.status}</td>
								<td align="center"><a
									href="${pageContext.request.contextPath}/lecturer/course/edit/${studentcourse.id.getStudent_StudentID()}/${studentcourse.id.getCourseIndex()}.html">
									<s:message code="Edit" /></a></td>
							</tr>
						</c:forEach>
					</tbody>
		</table>

	</div>
</body>
</html>