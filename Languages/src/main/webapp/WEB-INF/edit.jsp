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
			<div class="row d-flex justify-content-end">
				<a href="../${langObj.id}/delete" class="btn btn-danger m-3">delete</a>
				<a href="/languages" class="btn btn-primary m-3">dashboard</a>
			</div>
			<h3 class="mt-5 text-center">Edit <c:out value="${langObj.name}"/> Language</h3>
			<form:form action="../${langObj.id}/update" method="put" modelAttribute="langObj" class="form mx-5 p-5">
				<div class="form-group">
					<form:label path="name">Name</form:label>
					<form:input path="name" class="form-control" value="${langObj.name}"></form:input>
					<form:errors path ="name"/>
				</div>
				<div class="form-group">
					<form:label path="creator">Creator</form:label>
					<form:input path="creator" class="form-control" value="${langObj.creator}"></form:input>
					<form:errors path ="creator"/>
				</div>
				<div class="form-group">
					<form:label path="currentVersion">Current Version</form:label>
					<form:input path="currentVersion" class="form-control" value="${langObj.currentVersion}"></form:input>
					<form:errors path ="currentVersion"/>
				</div>
				<button type="submit" class="btn btn-primary">Submit</button>
			</form:form>
		</div>
	</body>
</html>
