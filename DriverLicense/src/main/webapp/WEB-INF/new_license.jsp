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
			<form:form action="/licenses/create" method="post" modelAttribute="licenseObj" class="card m-5 p-5">
				<div class="form-group">
					<form:label path="person">Person</form:label>
					<form:select path="person" class="form-control">
						<c:forEach items="${allPersons}" var="person">
							<form:option value="${person.id}">
								<c:out value="${person.firstName}"/> <c:out value="${person.lastName}"/>
							</form:option>
						</c:forEach>
					</form:select>
					<form:errors path="person"/>
				</div>
				<div class="form-group">
					<form:label path="state">State</form:label>
					<form:select path="state" items="${states}" class="form-control"/>
					<form:errors path="state"/>
				</div>
				<div class="form-group">
					<form:label path="expirationDate">Expiration Date</form:label>
					<form:input type="date" path="expirationDate" class="form-control"/>
					<form:errors path="expirationDate"/>
				</div>
				<button type="submit" class="btn btn-dark">Create</button>
			</form:form>
		</div>
	</body>
</html>
