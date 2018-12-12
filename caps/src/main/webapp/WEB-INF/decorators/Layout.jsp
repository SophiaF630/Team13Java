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
<link href="<c:url value='/css/style.css'/>"
	rel="stylesheet" type="text/css" />
</head>
<body>
	<div id="wrapper">
		<%@include file="Menu.jsp"%>
		<div id="page-content-wrapper">
			<div class="row">
                <div class="col-xs-2 text-left">
                	<a href="#menu-toggle" class="btn btn-default" id="menu-toggle">Toggle Menu</a>
              	</div>
              	<div class="col-xs-10 text-right">
              		<p>Username</p>
              	</div>
            </div>
			<div class="container-fluid">
				<dec:body />
			</div>
		</div>
		<!-- ======== Footer ======== -->
		<div id="footer" align="center">
			<hr>
			<small> &copy; ISS NUS SA47 TEAM13 2018 </small>
		</div>
	</div>
</body>
</html>
