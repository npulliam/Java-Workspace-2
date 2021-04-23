<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true" %>

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
			<form:form method="POST" action="/events/${eventToEdit.id}/edit" modelAttribute="eventToEdit" class="form card m-4 p-5">
		    	<input type="hidden" name="_method" value="put">
		    	<div class="form-group">
		        	<form:label path="name">Name:</form:label>
		            <form:input type="text" path="name" class="form-control"/>
		       		<form:errors path="name"/>
		       	</div>
		       	<div class="form-group">
		       		<form:label path="eventDate">Date:</form:label>
		            <form:input type="date" path="eventDate" class="form-control"/>
		       		<form:errors path="eventDate"/>
		       	</div>
		       	<div class="form-group row">
			       <div class="col">
			            <form:label path="location">Location:</form:label>
			            <form:input type="text" path="location" class="form-control"/>
			       		<form:errors path="location"/>
			       </div>
			       <div class="col">
						<form:label path="state">State</form:label>
						<form:select path="state" class="form-control" value="${eventToEdit.state}">
							<c:forEach items="${states}" var="stateName">
								<form:option value="${stateName}"><c:out value="${stateName}"/></form:option>
							</c:forEach>
						</form:select>
						<form:errors path="state"/>
			       </div>
			     </div>  
		        <button type="submit" class="btn btn-primary w-25">Update</button>
		    </form:form>
		</div>
	</body>
</html>
