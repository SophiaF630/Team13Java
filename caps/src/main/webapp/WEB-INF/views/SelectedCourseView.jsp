<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



<div class="container">
	<div class="row" style="color: black;">
		<div class="col-xs-12">
			<h1>Course Enrollment Status</h1>
		</div>
	</div>
	<div class="row">
		<div class="col-xs-12">
			<c:if test="${fn:length(studentcourse) gt 0}">
			
				<table class="table table-striped table-bordered" style="width: 100%" id="selectedCourseView">
					<thead>
						<tr>
							<th><s:message code="label.course.courseIndex" /></th>
							<th><s:message code="label.course.courseID" /></th>
							<th><s:message code="label.course.courseName" /></th>
							<th><s:message code="label.course.faculty" /></th>
							<th align="center"><s:message code="label.course.credits" /></th>
							<th><s:message code="label.course.studentcourse.status" /></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="studentcourse" items="${studentcourse}" varStatus="status">
							<tr class="${status.index%2==0?'even':'odd'}">
								<td class="nowrap">${studentcourse.course.courseIndex}</td>
								<td class="nowrap">${studentcourse.course.courseID}</td>
								<td class="nowrap">${studentcourse.course.courseName}</td>
								<td class="nowrap">${studentcourse.course.faculty}</td>
								<td class="nowrap" align="center">${studentcourse.course.credits}</td>
								<td class="nowrap">${studentcourse.status}</td>
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
		$('#selectedCourseView').DataTable();
	});

	
</script>