<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



<div class="container">
	<div class="row" style="color: black;">
		<div class="col-xs-12">
			<h1>View Grade</h1>
		</div>
	</div>
	<div class="row">
		<div class="col-xs-12">
			<c:if test="${fn:length(studentcourse) gt 0}">
				<table class="table table-striped table-bordered"
					style="width: 100%" id="viewGrade">
					<thead>
						<tr>
							<th><s:message code="label.studentcourse.studentID" /></th>
							<th><s:message code="label.studentcourse.courseIndex" /></th>
							<th><s:message code="label.studentcourse.courseID" /></th>
							<th><s:message code="label.studentcourse.courseName" /></th>
							<th><s:message code="label.course.credits" /></th>
							<th><s:message code="label.studentcourse.Grade" /></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="studentcourse" items="${studentcourse}"
							varStatus="status">
							<tr class="${status.index%2==0?'even':'odd'}">
								<td class="nowrap">${studentcourse.student.studentID}</td>
								<td class="nowrap">${studentcourse.course.courseIndex}</td>
								<td class="nowrap">${studentcourse.course.courseID}</td>
								<td class="nowrap">${studentcourse.course.courseName}</td>
								<td class="nowrap">${studentcourse.course.credits}</td>
								<td class="nowrap"><c:choose>
										<c:when
											test="${(studentcourse.CAGrade*0.1 + studentcourse.examGrade*0.9) ge 85}">A+</c:when>
										<c:when
											test="${(studentcourse.CAGrade*0.1 + studentcourse.examGrade*0.9) ge 80 && (studentcourse.CAGrade*0.1 + studentcourse.examGrade*0.9) le 84}">A</c:when>
										<c:when
											test="${(studentcourse.CAGrade*0.1 + studentcourse.examGrade*0.9) ge 75 && (studentcourse.CAGrade*0.1 + studentcourse.examGrade*0.9) le 79}">A-</c:when>
										<c:when
											test="${(studentcourse.CAGrade*0.1 + studentcourse.examGrade*0.9) ge 70 && (studentcourse.CAGrade*0.1 + studentcourse.examGrade*0.9) le 74}">B+</c:when>
										<c:when
											test="${(studentcourse.CAGrade*0.1 + studentcourse.examGrade*0.9) ge 65 && (studentcourse.CAGrade*0.1 + studentcourse.examGrade*0.9) le 69}">B</c:when>
										<c:when
											test="${(studentcourse.CAGrade*0.1 + studentcourse.examGrade*0.9) ge 60 && (studentcourse.CAGrade*0.1 + studentcourse.examGrade*0.9) le 64}">B-</c:when>
										<c:when
											test="${(studentcourse.CAGrade*0.1 + studentcourse.examGrade*0.9) ge 55 && (studentcourse.CAGrade*0.1 + studentcourse.examGrade*0.9) le 59}">C+</c:when>
										<c:when
											test="${(studentcourse.CAGrade*0.1 + studentcourse.examGrade*0.9) ge 50 && (studentcourse.CAGrade*0.1 + studentcourse.examGrade*0.9) le 54}">C</c:when>
										<c:when
											test="${(studentcourse.CAGrade*0.1 + studentcourse.examGrade*0.9) ge 45 && (studentcourse.CAGrade*0.1 + studentcourse.examGrade*0.9) le 49}">D+</c:when>
										<c:when
											test="${(studentcourse.CAGrade*0.1 + studentcourse.examGrade*0.9) ge 40 && (studentcourse.CAGrade*0.1 + studentcourse.examGrade*0.9) le 44}">D</c:when>
										<c:when
											test="${(studentcourse.CAGrade*0.1 + studentcourse.examGrade*0.9) lt 40}">F</c:when>
									</c:choose></td>
							</tr>
						</c:forEach>
						<tr>
							<td colspan="6">Current term Grade Point Average(GPA): ${String.valueOf(gpa)}</td>
						</tr>
						<tr>
							<td colspan="6">Cumulative Grade Point Average(CGPA): ${String.valueOf(cgpa)}</td>
						</tr>
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
		$('#viewGrade').DataTable();
	});

	
</script>