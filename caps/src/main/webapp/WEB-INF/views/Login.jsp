<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<header onload='document.loginForm.username.focus();'>
    <h1>Spring Security 5 - Login Form</h1>
 
    <c:if test="${not empty errorMessage}"><div style="color:red; font-weight: bold; margin: 30px 0px;">${errorMessge}</div></c:if>
 
    
	<form action="${pageContext.request.contextPath}/admin/home.html"
		method="POST"  ModelAttribute="user">
	
        <table>
            <tr>
                <td>UserName:</td>
                <td><input type='text' name='username' value=''></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><input type='password' name='password' /></td>
            </tr>
            <tr>
                <td colspan='2'><input name="submit" type="submit" value="submit" /></td>
            </tr>
        </table>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    </form>
</header>
</html>


<%-- 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<meta charset="ISO-8859-1">
<link href="<c:url value='/css/style.css'/>" rel="stylesheet"
	type="text/css" />
<title>Congratulations</title>
<head>
<h1>YOU MADE IT!</h1>

	<form:form action="${pageContext.request.contextPath}/admin/home.html"
		method="POST"  ModelAttribute="user">
		
Username: <input type="text" name="UserID">
		<br />
Password: <input type="text" name="Password">
		<br />
		<button
			class="w3-button w3-black w3-padding-large w3-large w3-margin-top" type="submit">Login</button> &nbsp &nbsp
<button
			class="w3-button w3-black w3-padding-large w3-large w3-margin-top">Cancel</button>
		<!-- <input type="submit" value ="Submit"/> <br/><br/>
    <input type="reset" value ="Cancel"/> <br/> -->

</form:form>
	
</head>
<body>
	
</body>
</html>  --%>