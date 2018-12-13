<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link href="<c:url value='/css/style.css'/>" rel="stylesheet"
	type="text/css" />

<a href="${pageContext.request.contextPath}/admin/course/create">Add
	Course</a>
<c:if test="${fn:length(courses) gt 0}">
	<br />
	<br />
	<table class="borderAll">
		<tr>
			<th><s:message code="label.student.courseID" /></th>
			<th><s:message code="label.student.CourseName" /></th>
			<th><s:message code="label.student.edit" /></th>
			<th><s:message code="label.student.delete" /></th>
		</tr>
		<c:forEach var="Course" items="${courses}" varStatus="status">
			<tr class="${status.index%2==0?'even':'odd'}">
				<td class="nowrap">${status.index}</td>
				<td class="nowrap">${Course.courseID}</td>
				<td class="nowrap">${Course.courseName}</td>
				<td align="center"><a
					href="${pageContext.request.contextPath}/admin/course/edit/${Course.courseIndex}.html">
						<s:message code="label.student.edit" />
				</a></td>
				<td><a
					href="${pageContext.request.contextPath}/admin/course/delete/${Courses.courseIndex}.html">
						<s:message code="label.student.delete" />
				</a></td>
				<td><%-- <a
					href="${pageContext.request.contextPath}/admin/student/courses/${student.studentID}.html">
						view course
				</a> --%>
				<button onclick = "window.location = '${pageContext.request.contextPath}/admin/course/students/${Course.courseIndex}.html'">view course</button>
				</td>
				</tr></c:forEach>
	</table>
</c:if>