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
		
			<h1>Register</h1>
			<p><form:errors path="userObj.*"/></p>
		    <form:form method="POST" action="/registration" modelAttribute="userObj" class="form card m-4 p-5">
		    	<div class="form-group">
		        	<form:label path="email">Email:</form:label>
		            <form:input type="email" path="email" class="form-control"/>
		       		
		       	</div>
		            
		       	<div class="form-group">
		            <form:label path="firstName">First Name:</form:label>
		            <form:input type="text" path="firstName" class="form-control"/>
		       		
		       	</div>
		       	<div class="form-group">
		            <form:label path="lastName">Last Name:</form:label>
		            <form:input type="text" path="lastName" class="form-control"/>
		       		
		       	</div>
		       	<div class="form-group row">
			       <div class="col">
			            <form:label path="location">Location:</form:label>
			            <form:input type="text" path="location" class="form-control"/>
			       		
			       </div>
			       <div class="col">
						<form:label path="state">State</form:label>
						<form:select path="state" class="form-control">
							<c:forEach items="${states}" var="stateName">
								<form:option value="${stateName}"><c:out value="${stateName}"/></form:option>
							</c:forEach>
						</form:select>
						
			       </div>
				</div>
			  	<div class="form-group">
		            <form:label path="password">Password:</form:label>
		            <form:input type="password" path="password" class="form-control"/>
		       		
		     	</div>
		    	<div class="form-group">
		            <form:label path="passwordConfirmation">Confirm Password:</form:label>
		            <form:input type="password" path="passwordConfirmation" class="form-control"/>
		       		
		       	</div>
				
		        <button type="submit" class="btn btn-primary w-25">Register</button>
		    </form:form>
		    <h1>Login</h1>
		    <p><c:out value="${error}" /></p>
	        <form method="post" action="/login" class="form card p-5 m-4">
		        <div class="form-group">		        
		            <label for="email">Email</label>
		            <input type="text" id="email" name="email" class="form-control"/>
		        </div>
		        
		    	<div class="form-group">
		            <label for="password">Password</label>
		            <input type="password" id="password" name="password" class="form-control"/>
		    	</div>
		        
		        <button type="submit" class="btn btn-primary">Login</button>
		    </form>
		</div>
	
	</body>
</html>
