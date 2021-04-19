<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Insert title here</title>
		<link rel="stylesheet" href="/webjars/bootstrap/4.5.0/css/bootstrap.min.css" />
		<link rel="stylesheet" href="css/style.css"/>
		<script src="/webjars/jquery/3.5.1/jquery.min.js"></script>
		<script src="/webjars/bootstrap/4.5.0/js/bootstrap.min.js"></script>
	</head>
	<body>
		<div class="container">
			<form:form action="/ninjas/new" method="post" modelAttribute="ninjaObj">
				<form:select path="campus">
					<c:forEach items="${allDojos}" var="dojo">
						<form:option value="${dojo.id}">
							<c:out value="${dojo.name}"/>
						</form:option>
					</c:forEach>
				</form:select>
				<form:label path="firstName">First Name</form:label>
				<form:input path="firstName"/>
				<form:errors path="firstName"/>
				<form:label path="lastName">Last Name</form:label>
				<form:input path="lastName"/>
				<form:errors path="lastName"/>
				<form:label path="age">Age</form:label>
				<form:input type="number" path="age"/>
				<form:errors path="age"/>'
				
				<button type="submit" class="btn btn-dark">Create Ninja</button>
			</form:form>
		</div>
	</body>
</html>
