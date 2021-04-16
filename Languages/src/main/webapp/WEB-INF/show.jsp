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
				<a href="/languages" class="btn btn-primary m-3">dashboard</a>
			</div>
			<div class="card m-5 p-5">
				<p>
					<c:out value="${showLang.name}"/>
				</p>
				<p>
					<c:out value="${showLang.creator}"/>				
				</p>
				<p>
					<c:out value="${showLang.currentVersion}"/>				
				</p>
				<a href="/languages/${showLang.id}/edit">Edit</a>
				<a href="/languages/${showLang.id}/delete" class="btn btn-danger m-3">delete</a>
			</div>
		</div>
	</body>
</html>
