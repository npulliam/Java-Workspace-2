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
			<h2><c:out value="${product.name}"/></h2>
			<div class="row">
				<div class="col">
					<p>Categories:</p>
					<ul>
						<c:forEach items="${productCats}" var="category">
							<li><c:out value="${category.name}"/></li>
						</c:forEach>
					</ul>
				</div>
				<div class="col">
					<form:form action="/products/${product.id}/addcategory" method="post" modelAttribute="categoryObj">
						<form:label path="id">Add Category</form:label>
						<form:select path="id">
							<c:forEach items="${allCategories}" var="category">
								<form:option value="${category.id}">
									<c:out value="${category.name}"/>
								</form:option>
							</c:forEach>
						</form:select>
						
						<button type="submit" class="btn btn-dark">Add</button>
					</form:form>
				</div>
			</div>
		</div>
	</body>
</html>
