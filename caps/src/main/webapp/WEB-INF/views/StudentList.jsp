<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<a href="${pageContext.request.contextPath}/admin/student/create">Add Student</a>

<div class="container">
	<div class="row" style="color: black;">
		<div class="col-xs-12">
			<h1>Student List</h1>
		</div>
	</div>
	<div class="row">
		<div class="col-xs-12">
			<c:if test="${fn:length(students) gt 0}">

				<table class="table table-striped table-bordered" style="width: 100%" id="studentList">
					<thead>
						<tr>
							<th><s:message code="label.student.studentID" /></th>
							<th><s:message code="label.student.lastName" /></th>
							<th><s:message code="label.student.firstMidName" /></th>						
							<th><s:message code="label.student.enrollmentDate" /></th>
							<th><s:message code="label.student.edit" /></th>
							<th><s:message code="label.student.delete" /></th>
							<th><s:message code="label.student.view" /></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="student" items="${students}" varStatus="status">
							<tr class="${status.index%2==0?'even':'odd'}">
								<td class="nowrap">${student.studentID}</td>								
								<td class="nowrap">${Users[status.index].lastName}</td>
								<td class="nowrap">${Users[status.index].firstMidName}</td>
								<td class="nowrap"><fmt:formatDate value="${student.enrollmentDate}" pattern="dd/MM/yyyy"/></td>
								<td align="center"><a
									href="${pageContext.request.contextPath}/admin/student/edit/${student.studentID}">
										<s:message code="label.student.edit" />
								</a></td>
								<td align="center"><a
									href="${pageContext.request.contextPath}/admin/student/delete/${student.studentID}">
										<s:message code="label.student.delete" />
								</a></td>
								<td>
									<%-- <a
					href="${pageContext.request.contextPath}/admin/student/courses/${student.studentID}.html">
						view course
				</a> --%>
									<button
										onclick="window.location = '${pageContext.request.contextPath}/admin/student/courses/${student.studentID}.html'">View
										course</button>
								</td>
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
		$('#studentList').DataTable();
	});

	
</script>