<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



<div class="container">
	<div class="row" style="color: black;">
		<div class="col-xs-12">
			<h1>Course Details</h1>
			<c:if test="${status == 1}">
				<button
					onclick="window.location.href = '${pageContext.request.contextPath}/admin/student/history/${sid}.html'">Enrollment
					History</button>
			</c:if>
			<c:if test="${status == 2}">
				<button
					onclick="window.location.href = '${pageContext.request.contextPath}/admin/student/courses/${sid}.html'">Current
					Enrollment</button>
			</c:if>
		</div>
	</div>
	<div class="row">
		<div class="col-xs-12">
			<c:if test="${fn:length(studentcourse) gt 0}">
				<table class="table table-striped table-bordered"
					style="width: 100%" id="courseDetails">
					<thead>
						<tr>
							<th><s:message code="label.course.courseIndex" /></th>
							<th><s:message code="label.course.courseID" /></th>
							<th><s:message code="label.course.courseName" /></th>
							<th><s:message code="label.course.faculty" /></th>
							<th><s:message code="label.course.classSize" /></th>
							<th><s:message code="label.course.credits" /></th>
							<th><s:message code="label.course.startDate" /></th>
							<th><s:message code="label.course.endDate" /></th>
							<th><s:message code="label.studentcourse.status" /></th>
							<c:if test="${status == 1}">
								<th><s:message code="label.course.operation" /></th>
							</c:if>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="studentcourse" items="${studentcourse}" varStatus="Status">
							<tr class="${Status.index%2==0?'even':'odd'}">
								<td class="nowrap">${studentcourse.course.courseIndex}</td>
								<td class="nowrap">${studentcourse.course.courseID}</td>
								<td class="nowrap">${studentcourse.course.courseName}</td>
								<td class="nowrap">${studentcourse.course.faculty}</td>
								<td class="nowrap">${studentcourse.course.classSize}</td>
								<td class="nowrap">${studentcourse.course.credits}</td>
								<td class="nowrap"><fmt:formatDate value="${studentcourse.course.startDate}" pattern="dd/MM/yyyy"/></td>
								<td class="nowrap"><fmt:formatDate value="${studentcourse.course.endDate}" pattern="dd/MM/yyyy"/></td>
								<td class="nowrap">${studentcourse.status}</td>
								<c:if test="${status == 1}">
									<td>
									<%-- <button
										onclick="window.location = '${pageContext.request.contextPath}/admin/student/rejectcourse/${studentcourse.course.courseIndex}&${studentcourse.student.studentID}.html'">Reject</button>
										<button
										onclick="window.location = '${pageContext.request.contextPath}/admin/student/approvecourse/${studentcourse.course.courseIndex}&${studentcourse.student.studentID}.html'">Approve</button> --%>
									<a
										href="${pageContext.request.contextPath}/admin/student/rejectcourse/${studentcourse.course.courseIndex}&${studentcourse.student.studentID}.html">Reject</a>
										<a
										href="${pageContext.request.contextPath}/admin/student/approvecourse/${studentcourse.course.courseIndex}&${studentcourse.student.studentID}.html">Approve</a>
									</td>
									
								</c:if>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:if>
		</div>
	</div>
</div>


<script
	src="<c:url value='/js/DataTables/jQuery-3.3.1/jquery-3.3.1.min.js'/>"
	type="text/javascript"></script>
<script src="<c:url value='/js/DataTables/datatables.min.js'/>"
	type="text/javascript"></script>

<script>
	$(document).ready(function() {
		$('#courseDetails').DataTable();
	});

	
</script>