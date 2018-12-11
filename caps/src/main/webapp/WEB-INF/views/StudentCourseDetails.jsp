<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link href="<c:url value='/css/style.css'/>" rel="stylesheet"
	type="text/css" />


<c:if test="${fn:length(courses) gt 0}">
	<br />
	<br />
	<table class="borderAll">
		<tr>
			<th><s:message code="label.course.courseIndex" /></th>
			<th><s:message code="label.course.courseID" /></th>
			<th><s:message code="label.course.courseName" /></th>
			<th><s:message code="label.course.faculty" /></th>
			<th><s:message code="label.course.credits" /></th>
			<th><s:message code="label.course.select" /></th>
		</tr>
		<c:forEach var="course" items="${courses}" varStatus="status">
			<tr class="${status.index%2==0?'even':'odd'}">
				<td class="nowrap">${course.courseIndex}</td>
				<td class="nowrap">${course.courseID}</td>
				<td class="nowrap">${course.courseName}</td>
				<td class="nowrap">${course.faculty}</td>
				<td class="nowrap">${course.credits}</td>
				<td align="center"><a
					href="${pageContext.request.contextPath}/coursedetails/edit/${student.studentID}.html">
						<s:message code="label.course.select" />
				</a></td>
				

			</tr>
		</c:forEach>
	</table>
</c:if>