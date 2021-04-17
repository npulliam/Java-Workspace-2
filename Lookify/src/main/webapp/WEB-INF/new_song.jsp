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
			<div class="row d-flex justify-content-end m-3 px-4">
				<a href="/dashboard">dashboard</a>
			</div>
			<form:form action="/songs/create" method="post" modelAttribute="songObj">
				<div class="form-group">
					<form:label path="name">Title</form:label>
					<form:input path="name" class="form-control"/>
					<form:errors path="name"/>
				</div>
				<div class="form-group">
					<form:label path="artist">Artist</form:label>
					<form:input path="artist" class="form-control"/>
					<form:errors path="artist"/>
				</div>
				<div class="form-group">
					<form:label path="rating">Rating</form:label>
					<form:input type="number" min="1" max ="10" path="rating" class="form-control" value="1"/>
					<form:errors path="rating"/>
				</div>
				<button type="submit" class="btn btn-primary">Add Song</button>
			</form:form>
		</div>
	</body>
</html>
