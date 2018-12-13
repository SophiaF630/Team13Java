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
<script
	src="<c:url value='/js/DataTables/jQuery-3.3.1/jquery-3.3.1.min.js'/>"
	type="text/javascript"></script>
<script
	src="<c:url value='/js/DataTables/Bootstrap-3.3.7/js/bootstrap.min.js'/>"
	type="text/javascript"></script>
</head>

<body>
	<div class="wrapper">
        <nav id="sidebar">
            <div class="sidebar-header">
                <h3>ISS-Caps</h3>
            </div>

            <ul class="list-unstyled components">
                <p>Menu</p>
                <li>
                    <a href="/caps/student/coursedetails">Select Course</a>
                </li>
                <li>
                    <a href="/caps/student/selectedcourse">Enrolled Courses</a>
                </li>
                <li>
                    <a href="#">Otheres</a>
                </li>
            </ul>
        </nav>

        <!-- Page Content  -->
        <div id="content">
            <nav class="navbar navbar-expand-lg navbar-light bg-light">
                <div class="container-fluid">

                    <div class="collapse navbar-collapse text-right" id="navbarSupportedContent">
                        <ul class="nav navbar-nav ml-auto pull-right">
                            <li class="nav-item active">
                                <a>Username</a>
                            </li>
                            <li class="nav-item active">
                                <a class="nav-link" href="#">Logout</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
			<div>
				<dec:body />
			</div>
		
			<!-- ======== Footer ======== -->
			<div id="footer" align="center">
				<hr>
				<small> &copy; ISS NUS SA47 TEAM13 2018 </small>
			</div>
		</div>
	</div>
</body>
</html>
