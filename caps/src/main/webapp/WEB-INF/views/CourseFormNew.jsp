<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="<c:url value='/css/style.css'/>" rel="stylesheet"
	type="text/css" />
</head>
<body>
	<div>
		<table>
			<tr>
				<th>time</th>
				<th>Monday</th>
				<th>Tuesday</th>
				<th>Wednesday</th>
				<th>Thursday</th>
				<th>Friday</th>
				<th>Saturday</th>
				<th>Sunday</th>
			</tr>
			<tr>
				<td>7:30-8:30</td>
				<td><button id="10" onclick="add(id)">add</button></td>
				<td><button id="20" onclick="add(id)">add</button></td>
				<td><button id="30" onclick="add(id)">add</button></td>
				<td><button id="40" onclick="add(id)">add</button></td>
				<td><button id="50" onclick="add(id)">add</button></td>
				<td><button id="60" onclick="add(id)">add</button></td>
				<td><button id="00" onclick="add(id)">add</button></td>
			</tr>
			<tr>
				<td>8:30-9:30</td>
				<td><button id="11" onclick="add(id)">add</button></td>
				<td><button id="21" onclick="add(id)">add</button></td>
				<td><button id="31" onclick="add(id)">add</button></td>
				<td><button id="41" onclick="add(id)">add</button></td>
				<td><button id="51" onclick="add(id)">add</button></td>
				<td><button id="61" onclick="add(id)">add</button></td>
				<td><button id="01" onclick="add(id)">add</button></td>
			</tr>
			<tr>
				<td>9:30-10:30</td>
				<td><button id="12" onclick="add(id)">add</button></td>
				<td><button id="22" onclick="add(id)">add</button></td>
				<td><button id="32" onclick="add(id)">add</button></td>
				<td><button id="42" onclick="add(id)">add</button></td>
				<td><button id="52" onclick="add(id)">add</button></td>
				<td><button id="62" onclick="add(id)">add</button></td>
				<td><button id="02" onclick="add(id)">add</button></td>
			</tr>
			<tr>
				<td>10:30-11:30</td>
				<td><button id="13" onclick="add(id)">add</button></td>
				<td><button id="23" onclick="add(id)">add</button></td>
				<td><button id="33" onclick="add(id)">add</button></td>
				<td><button id="43" onclick="add(id)">add</button></td>
				<td><button id="53" onclick="add(id)">add</button></td>
				<td><button id="63" onclick="add(id)">add</button></td>
				<td><button id="03" onclick="add(id)">add</button></td>
			</tr>
			<tr>
				<td>11:30-12:30</td>
				<td><button id="14" onclick="add(id)">add</button></td>
				<td><button id="24" onclick="add(id)">add</button></td>
				<td><button id="34" onclick="add(id)">add</button></td>
				<td><button id="44" onclick="add(id)">add</button></td>
				<td><button id="54" onclick="add(id)">add</button></td>
				<td><button id="64" onclick="add(id)">add</button></td>
				<td><button id="04" onclick="add(id)">add</button></td>
			</tr>
			<tr>
				<td>12:30-13:30</td>
				<td><button id="15" onclick="add(id)">add</button></td>
				<td><button id="25" onclick="add(id)">add</button></td>
				<td><button id="35" onclick="add(id)">add</button></td>
				<td><button id="45" onclick="add(id)">add</button></td>
				<td><button id="55" onclick="add(id)">add</button></td>
				<td><button id="65" onclick="add(id)">add</button></td>
				<td><button id="05" onclick="add(id)">add</button></td>
			</tr>
			<tr>
				<td>13:30-14:30</td>
				<td><button id="16" onclick="add(id)">add</button></td>
				<td><button id="26" onclick="add(id)">add</button></td>
				<td><button id="36" onclick="add(id)">add</button></td>
				<td><button id="46" onclick="add(id)">add</button></td>
				<td><button id="56" onclick="add(id)">add</button></td>
				<td><button id="66" onclick="add(id)">add</button></td>
				<td><button id="06" onclick="add(id)">add</button></td>
			</tr>
			<tr>
				<td>14:30-15:30</td>
				<td><button id="17" onclick="add(id)">add</button></td>
				<td><button id="27" onclick="add(id)">add</button></td>
				<td><button id="37" onclick="add(id)">add</button></td>
				<td><button id="47" onclick="add(id)">add</button></td>
				<td><button id="57" onclick="add(id)">add</button></td>
				<td><button id="67" onclick="add(id)">add</button></td>
				<td><button id="07" onclick="add(id)">add</button></td>
			</tr>
			<tr>
				<td>15:30-16:30</td>
				<td><button id="18" onclick="add(id)">add</button></td>
				<td><button id="28" onclick="add(id)">add</button></td>
				<td><button id="38" onclick="add(id)">add</button></td>
				<td><button id="48" onclick="add(id)">add</button></td>
				<td><button id="58" onclick="add(id)">add</button></td>
				<td><button id="68" onclick="add(id)">add</button></td>
				<td><button id="08" onclick="add(id)">add</button></td>
			</tr>
			<tr>
				<td>16:30-17:30</td>
				<td><button id="19" onclick="add(id)">add</button></td>
				<td><button id="29" onclick="add(id)">add</button></td>
				<td><button id="39" onclick="add(id)">add</button></td>
				<td><button id="49" onclick="add(id)">add</button></td>
				<td><button id="59" onclick="add(id)">add</button></td>
				<td><button id="69" onclick="add(id)">add</button></td>
				<td><button id="09" onclick="add(id)">add</button></td>
			</tr>
		</table>
	</div>
	<form:form method="POST" modelAttribute="course"
		action="${pageContext.request.contextPath}/admin/course/create">
		<center>
			<table cellpadding=4 cellspacing=2 border=0>
				<tr>
					<th>Description</th>
					<th>Detail</th>
				</tr>
				<tr>
					<td><s:message code="label.student.courseID" /> *</td>
					<td><form:input path="courseID" /> <form:errors
							path="courseID" cssStyle="color: red;" /></td>
				</tr>
				<tr>
					<td><s:message code="label.student.courseDate" /></td>
					<td><form:input path="courseName" /> <form:errors
							path="courseName" cssStyle="color: red;" /></td>
				</tr>
				<tr>
					<td><s:message code="label.student.courseSize" /></td>
					<td><form:input path="classSize" /> <form:errors
							path="classSize" cssStyle="color: red;" /></td>
				</tr>
				<tr>
					<td><s:message code="label.student.credits" /></td>
					<td><form:input path="credits" /> <form:errors
							path="credits" cssStyle="color: red;" /></td>
				</tr>
				<tr>
					<td><s:message code="label.student.startDate" /></td>
					<td><form:input path="startDate" /> <form:errors
							path="startDate" cssStyle="color: red;" /></td>
				</tr>
				<tr>
					<td><s:message code="label.student.endDate" /></td>
					<td><form:input path="endDate" /> <form:errors
							path="endDate" cssStyle="color: red;" /></td>
				</tr>
				<tr>
					<td><s:message code="label.student.faculty" /></td>
					<td><form:input path="faculty" /> <form:errors
							path="faculty" cssStyle="color: red;" /></td>
				</tr>
				<tr>
					<td><s:message code="label.student.lectureSchedule" /></td>
					<td><form:input path="lectureSchedule" /> <form:errors
							path="lectureSchedule" cssStyle="color: red;"/>${a}</td>
				</tr>
				<tr>
					<td><input type="submit" value="Submit"></td>
					<td><input type="reset" value="Reset"></td>
				</tr>
			</table>
		</center>

	</form:form>
	<script type="text/javascript">
		string a = "";
		function add(id) {
			a = a + id + ",";
			document.getElementById(id).disabled = "disabled";
		}
	</script>
</body>
</html>