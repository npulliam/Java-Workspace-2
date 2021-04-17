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
			<div class="container card m-5 p-5">
				<div class="row">
				<div class="col">
						<p>Title</p>
						<p>Artist</p>
						<p>Rating (1-10)</p>
					</div>
					<div class="col">
						<p><c:out value="${song.name}"/></p>
						<p><c:out value="${song.artist}"/></p>
						<p><c:out value="${song.rating}"/></p>
					</div>
				</div>
				<a href="/songs/${song.id}/delete">delete</a>
			</div>
		</div>
	</body>
</html>
