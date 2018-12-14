<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<link
	href="<c:url value='/js/DataTables/Bootstrap-3.3.7/css/bootstrap.min.css'/>"
	rel="stylesheet" type="text/css" />
<header onload='document.loginForm.username.focus();'>
<div class="container">
	<h1 class="text-center">CAP - COURSE MANAGEMENT SYSTEM</h1>
	<h2 class="text-center">Logout successfully!</h2>
	<c:if test="${not empty errorMessage}">
		<div style="color: red; font-weight: bold; margin: 30px 0px;">${errorMessge}</div>
	</c:if>
	<form action="${pageContext.request.contextPath}/login/authenticate"
		method="POST">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
		<div class="form-group">
			<label for="username">Username</label> 
			<input type="text"
				class="form-control" id="username" name="username"
				aria-describedby="emailHelp" placeholder="User name">
		</div>

		<div class="form-group">
			<label for="password">Password</label> 
			<input type="password"
				class="form-control" name="password" aria-describedby="emailHelp"
				placeholder="Password">
		</div>
		<button type="submit" class="btn btn-primary">Submit</button>

	</form>

	</div>
</header>
</html>

