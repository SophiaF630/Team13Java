<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="container">
	<div class="row" style="color: black;">
		<div class="col-xs-12">
			<h1>Course Details</h1>
		</div>
	</div>
	<div class="row">
		<div class="col-xs-12">
			<c:if test="${fn:length(courses) gt 0}">
			<form method="POST" action="/caps/student/coursedetails">
				<input type="hidden" value="student-id-01" name="studentId">
				<table class="table table-striped table-bordered" style="width: 100%" id="courseDetails">
					<thead>
						<tr>
							<th><s:message code="label.course.courseIndex" /></th>
							<th><s:message code="label.course.courseID" /></th>
							<th><s:message code="label.course.courseName" /></th>
							<th><s:message code="label.course.faculty" /></th>
							<th><s:message code="label.course.credits" /></th>
							<th><s:message code="label.course.select" /></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="avaCourses" items="${avaCourses}" varStatus="status">
							<tr class="${status.index%2==0?'even':'odd'}">
								<td class="nowrap">${avaCourses.courseIndex}</td>
								<td class="nowrap">${avaCourses.courseID}</td>
								<td class="nowrap">${avaCourses.courseName}</td>
								<td class="nowrap">${avaCourses.faculty}</td>
								<td class="nowrap">${avaCourses.credits}</td>
								<td>
									<%-- <c:choose>										
										<c:when test="true"> --%>
											<input name="courseIndexes" type="checkbox"
												value="${avaCourses.courseIndex}">
									<%-- 	</c:when> 
										<c:otherwise>
										<input name="courseIndexes" disabled type="checkbox"
												value="${course.courseIndex}">
										</c:otherwise>										
									</c:choose>
 --%>
								</td>
							</tr>
						</c:forEach>
						<c:forEach var="unavaCourses" items="${unavaCourses}" varStatus="status">
							<tr class="${status.index%2==0?'even':'odd'}">
								<td class="nowrap">${unavaCourses.courseIndex}</td>
								<td class="nowrap">${unavaCourses.courseID}</td>
								<td class="nowrap">${unavaCourses.courseName}</td>
								<td class="nowrap">${unavaCourses.faculty}</td>
								<td class="nowrap">${unavaCourses.credits}</td>
								<td>
									<%-- <c:choose>										
										<c:when test="true"> --%>
											<input name="courseIndexes" disabled type="checkbox"
												value="${unavaCourses.courseIndex}">
								<%-- 		</c:when> 
										<c:otherwise>
										<input name="courseIndexes" disabled type="checkbox"
												value="${unavaCourses.courseIndex}">
										</c:otherwise>										
									</c:choose> --%>

								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<input class="btn button-primary pull-right" type="submit" value="Add to Course Planner">
				</form>
			</c:if>
		</div>
	</div>
</div>

<script src="<c:url value='/js/DataTables/datatables.min.js'/>"
	type="text/javascript"></script>

<script>
	$(document).ready(function() {
		$('#courseDetails').DataTable();
	});
</script>