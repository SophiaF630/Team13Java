<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>



<div class="container">
	<div class="row" style="color:black;">
		<div class="col-xs-12">
			<h1>Course Details</h1>
			<button onclick = "window.location.href = '${pageContext.request.contextPath}/admin/student/history/${sID}.html'">history course</button>
		</div>
	</div>
	<div class="row">
		<div class="col-xs-12">
			<c:if test="${fn:length(student) gt 0}">
				<table class="cell-border" style="width:100%" id="courseDetails">
					<thead>
					<tr>
						<th><s:message code="label.student.studentID" /></th>
					</tr>
					</thead>
					<tbody>
					<c:forEach var="course" items="${student}" varStatus="status">
						<tr class="${status.index%2==0?'even':'odd'}">
							<td class="nowrap">${course.studentID}</td>
							<%-- <c:if test="${stucourse.status} != 'finished'">
							<a href ="${pageContext.request.contextPath}/admin/student/removecourse/${course.studentID}&${sID} .html">remove</a>
							</c:if> --%>
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