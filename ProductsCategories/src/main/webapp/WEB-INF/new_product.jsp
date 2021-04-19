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
			<h2>New Product</h2>
			<form:form action="/products/new" method="post" modelAttribute="productObj">
				<div class="form-group">
					<form:label path="name">Name</form:label>
					<form:input path="name"/>
					<form:errors path="name"/>
				</div>
				<div class="form-group">
					<form:label path="description">Description</form:label>
					<form:input type="textarea" path="description"/>
					<form:errors path="description"/>
				</div>
				<div class="form-group">
					<form:label path="price">Price $</form:label>
					<form:input type="number" min="0.01" step="0.01" path="price"/>
					<form:errors path="price"/>
				</div>
				<button type="submit" class="btn btn-dark">Add Product</button>
			</form:form>
		</div>
	</body>
</html>
