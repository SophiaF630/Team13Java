<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>



<div class="container">
	<div class="row" style="color:black;">
		<div class="col-xs-12">
			<h1>Course Details</h1>
			<c:if test="${status == 1}">
			<button onclick = "window.location.href = '${pageContext.request.contextPath}/admin/student/history/${sid}.html'">history course</button></c:if>
			<c:if test="${status == 2}">
			<button onclick = "window.location.href = '${pageContext.request.contextPath}/admin/student/courses/${sid}.html'">current course</button></c:if>
		</div>
	</div>
	<div class="row">
		<div class="col-xs-12">
			<c:if test="${fn:length(courses) gt 0}">
				<table class="cell-border" style="width:100%" id="courseDetails">
					<thead>
					<tr>
						<th><s:message code="label.course.courseIndex" /></th>
						<th><s:message code="label.course.courseID" /></th>
						<th><s:message code="label.course.courseName" /></th>
						<th><s:message code="label.course.faculty" /></th>
						<th><s:message code="label.course.credits" /></th>
						<th><s:message code="label.course.enrol" /></th>
						<th><s:message code="label.course.operation" /></th>
					</tr>
					</thead>
					<tbody>
					<c:forEach var="course" items="${courses}" varStatus="Status">
						<tr class="${Status.index%2==0?'even':'odd'}">
							<td class="nowrap">${course.courseIndex}</td>
							<td class="nowrap">${course.courseID}</td>
							<td class="nowrap">${course.courseName}</td>
							<td class="nowrap">${course.faculty}</td>
							<td class="nowrap">${course.classSize}</td>
							<td>
							<c:if test="${status == 1}">
			<a href ="${pageContext.request.contextPath}/admin/student/removecourse/${course.courseIndex}&${sID} .html">remove</a></c:if></td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</c:if>
		</div>
	</div>
</div>


<script src="<c:url value='/js/DataTables/jQuery-3.3.1/jquery-3.3.1.min.js'/>" 
	type="text/javascript" ></script>
<script src="<c:url value='/js/DataTables/datatables.min.js'/>" 
	type="text/javascript" ></script>
	
	<script>
	$(document).ready( function () {
	    $('#courseDetails').DataTable();
	} );
	</script>