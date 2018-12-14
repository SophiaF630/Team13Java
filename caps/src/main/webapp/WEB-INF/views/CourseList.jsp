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
			<th><s:message code="label.student.faculty" /></th>
			<th><s:message code="label.student.credits" /></th>
			<th><s:message code="label.student.showTime" /></th>
			<th><s:message code="label.student.edit" /></th>
			<th><s:message code="label.student.delete" /></th>
		</tr>
		<c:forEach var="Course" items="${courses}" varStatus="status">
			<tr class="${status.index%2==0?'even':'odd'}">
				<td class="nowrap">${status.index}</td>
				<td class="nowrap">${Course.courseID}</td>
				<td class="nowrap">${Course.courseName}</td>
				<td class="nowrap">${Course.faculty}</td>
				<td class="nowrap">${Course.credits}</td>
				<td><button
						onclick="window.location = '${pageContext.request.contextPath}/admin/course/listshow/${Course.courseIndex}.html'">show</button></td>
				<td align="center"><a
					href="${pageContext.request.contextPath}/admin/course/edit/${Course.courseIndex}.html">
						<s:message code="label.student.edit" />
				</a></td>
				<td><a
					href="${pageContext.request.contextPath}/admin/course/delete/${Courses.courseIndex}.html">
						<s:message code="label.student.delete" />
				</a></td>
				<td>
					<button
						onclick="window.location = '${pageContext.request.contextPath}/admin/course/students/${Course.courseIndex}.html'">view
						course</button>
				</td>
			</tr>
		</c:forEach>
	</table>
</c:if>

<c:if test="${empty studentcourse}">
	<table class="table table-striped table-bordered" style="width: 100%"
		id="Timetable">
		<thead>
			<tr>
				<th align="center" valign="middle" width="120"></th>
				<th align="center" valign="middle" width="120">Monday</th>
				<th align="center" valign="middle" width="120">Tuesday</th>
				<th align="center" valign="middle" width="120">Wednesday</th>
				<th align="center" valign="middle" width="120">Thursday</th>
				<th align="center" valign="middle" width="120">Friday</th>
				<th align="center" valign="middle" width="120">Saturday</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach begin="9" end="18" step="1" var="time">
				<tr>
					<td align="center" valign="middle" width="120"><c:out
							value="${time}:00 - ${time+1}:00" /></td>
					<c:forEach begin="1" end="6" step="1" var="day">
						<c:set var="j"
							value="${String.valueOf(day)}${String.valueOf(time-9)}" />
						<c:if test="${collisions[j]}">
							<td align="center" class="has-collision" valign="middle"
								width="100">
						</c:if>
						<c:if test="${not collisions[j]}">
							<td align="center" valign="middle" width="100">
						</c:if>
						<c:forEach items="${studentcourse}" var="studentcourse">
							<c:set var="lectureschedule"
								value="${studentcourse.lectureSchedule}" />
							<c:set var="splitlectureschedule"
								value="${fn:split(lectureschedule, ',')}" />
							<c:forEach var="i" items="${splitlectureschedule}">
								<c:set var="k" value="${i}" />
								<c:if test="${k.equals(j)}">
									<c:out
										value="${studentcourse.courseID} ${studentcourse.courseName}" />
								</c:if>
							</c:forEach>
						</c:forEach>
						</td>
					</c:forEach>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</c:if>
