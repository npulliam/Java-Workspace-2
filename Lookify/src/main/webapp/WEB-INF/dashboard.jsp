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
		<script src="/webjars/jquery/3.5.1/jquery.min.js"></script>
		<script src="/webjars/bootstrap/4.5.0/js/bootstrap.min.js"></script>
		
	</head>
	<body>
		<div class="container">
			<div class="row d-flex justify-content-between m-3 px-4">
				<a href="/songs/new">Add New</a>
				<a href="/songs/topTen">Top Songs</a>
				<form action="/search" method="POST">
					<input type="search" name="searchArtist"/>
					<button type="submit" class="btn btn-dark">Search Artist</button>
				</form>
				<table class="table table-dark table-striped m-4 p-2">
					<thead>
						<tr>
							<th>Title</th>
							<th>Rating</th>
							<th>Actions</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${allSongs}" var="song">
							<tr>
								<td><a href="/songs/${song.id}"><c:out value="${song.name}"/></a></td>
								<td><c:out value="${song.rating}"/></td>
								<td><a href="/songs/${song.id}/delete">delete</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		
	</body>
</html>
