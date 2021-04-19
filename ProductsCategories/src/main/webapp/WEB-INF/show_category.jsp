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
			<h2><c:out value="${category.name}"/></h2>
			<div class="row">
				<div class="col">
					<p>Products:</p>
					<ul>
						<c:forEach items="${categoryProds}" var="product">
							<li><c:out value="${product.name}"/></li>
						</c:forEach>
					</ul>
				</div>
				<div class="col">
					<form:form action="/categories/${category.id}/addproduct" method="post" modelAttribute="productObj">
						<form:label path="id">Add Product</form:label>
						<form:select path="id">
							<c:forEach items="${allProducts}" var="product">
								<c:if test="${!categoryProds.contains(product)}">
									<form:option value="${product.id}">
										<c:out value="${product.name}"/>
									</form:option>
								</c:if>
							</c:forEach>
						</form:select>
						
						<button type="submit" class="btn btn-dark">Add</button>
					</form:form>
				</div>
			</div>
		</div>
	</body>
</html>
