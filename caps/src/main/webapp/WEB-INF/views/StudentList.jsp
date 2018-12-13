<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link href="<c:url value='/css/style.css'/>" rel="stylesheet"
	type="text/css" />

<a href="${pageContext.request.contextPath}/admin/student/create">Add
	Student</a>
<c:if test="${fn:length(students) gt 0}">
	<br />
	<br />
	<table class="borderAll">
		<tr>
			<th><s:message code="label.student.userName" /></th>
			<th><s:message code="label.student.studnetID" /></th>
			<th><s:message code="label.student.enrollmentDate" /></th>
			<th><s:message code="label.student.edit" /></th>
			<th><s:message code="label.student.delete" /></th>
		</tr>
		<c:forEach var="student" items="${students}" varStatus="status">
			<tr class="${status.index%2==0?'even':'odd'}">
				<td class="nowrap">${status.index}</td>
				<td class= "nowrap">${Users[status.index].firstMidName} </td>
				<td class="nowrap">${student.studentID}</td>
				<td class="nowrap"><input type = "date" value = "${student.enrollmentDate}" readonly = "readonly"></td>
				<td align="center"><a
					href="${pageContext.request.contextPath}/admin/student/edit/${student.studentID}.html">
						<s:message code="label.student.edit" />
				</a></td>
				<td><a
					href="${pageContext.request.contextPath}/admin/student/delete/${student.studentID}.html">
						<s:message code="label.student.delete" />
				</a></td>
				<td><%-- <a
					href="${pageContext.request.contextPath}/admin/student/courses/${student.studentID}.html">
						view course
				</a> --%>
				<button onclick = "window.location = '${pageContext.request.contextPath}/admin/student/courses/${student.studentID}.html'">view course</button>
				</td>

			</tr>
		</c:forEach>
	</table>
</c:if>