<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="dec"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<link
	href="<c:url value='/js/DataTables/Bootstrap-3.3.7/css/bootstrap.min.css'/>"
	rel="stylesheet" type="text/css" />
<link href="<c:url value='/js/DataTables/Bootstrap-3.3.7/css/bootstrap-theme.min.css'/>"
	rel="stylesheet" type="text/css" />
<link href="<c:url value='/js/DataTables/datatables.min.css'/>"
	rel="stylesheet" type="text/css" />
</head>
<body>
	<%@include file="Menu.jsp"%>
	<div>
		<dec:body />
	</div>
	<!-- ======== Footer ======== -->
	<div id="footer" align="center">
		<hr>
		<small> &copy; ISS NUS SA47 TEAM13 2018 </small>
	</div>
</body>
</html>
