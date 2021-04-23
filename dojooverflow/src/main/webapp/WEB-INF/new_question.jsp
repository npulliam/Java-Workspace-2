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
			<form:form action="/questions/new" method="post" modelAttribute="questionObj">
				<div class="form-group">
					<form:label path="question">Question</form:label>
					<form:input path="question" type="textarea"/>
					<form:errors path="question"/>
				</div>
				<div class="form-group">
					<form:label path="tags">Tags (maximum 3, comma separated)</form:label>
					<form:input path="tags"/>
					<form:errors path="tags"/>
				</div>
				<button type="submit" class="btn btn-dark">Add Question</button>
			</form:form>
		</div>
	</body>
</html>
