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
			<form:form action="/persons/create" method="post" modelAttribute="personObj" class="card m-5 p-5">
				<div class="form-group">
					<form:label path="firstName">First Name</form:label>
					<form:input path="firstName" class="form-control"/>
					<form:errors path="firstName"/>
				</div>
				<div class="form-group">
					<form:label path="lastName">Last Name</form:label>
					<form:input path="lastName" class="form-control"/>
					<form:errors path="lastName"/>
				</div>
				<button type="submit" class="btn btn-dark">Create</button>
			</form:form>
		</div>
	</body>
</html>
