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
			<div class="row d-flex justify-content-around m-5">
				<h1>Welcome, <c:out value="${loggedUser.firstName}" /></h1>
				<a href="/logout">Logout</a>
			</div>
			<p class="my-3">Here are some events in your state:</p>
			<table class="table table-primary table-striped">
				<thead>
					<tr>
						<th>Name</th>
						<th>Date</th>
						<th>Location</th>
						<th>Host</th>
						<th>Action/Status</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${allEvents}" var="event">
						<c:if test="${event.state == loggedUser.state}">
							<tr>
								<td><a href="/events/${event.id}"><c:out value="${event.name}"/></a></td>
								<td><c:out value="${event.eventDate}"/></td>
								<td><c:out value="${event.location}"/></td>
								<td><c:out value="${event.host.firstName}"/></td>
								<c:choose>
									<c:when test="${loggedUser.eventsHosted.contains(event)}">
										<td><a href="/events/${event.id}/edit">Edit</a> <a href="/events/${event.id}/delete">Delete</a></td>
									</c:when>
									<c:otherwise>
										<c:choose>
											<c:when test="${loggedUser.joinedEvents.contains(event)}">
												<td>Joined  <a href="/events/${event.id}/canceljoin">Cancel</a></td>
											</c:when>
											<c:otherwise>
												<td><a href="/events/${event.id}/join">Join</a></td>										
											</c:otherwise>
										</c:choose>
									</c:otherwise>
								</c:choose>
								
							</tr>
						</c:if>
					</c:forEach>
				</tbody>
			</table>
			<p class="my-3">Here are some events in your other states:</p>
			<table class="table table-primary table-striped">
				<thead>
					<tr>
						<th>Name</th>
						<th>Date</th>
						<th>Location</th>
						<th>State</th>
						<th>Host</th>
						<th>Action/Status</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${allEvents}" var="event">
						<c:if test="${event.state != loggedUser.state}">
							<tr>
								<td><a href="/events/${event.id}"><c:out value="${event.name}"/></a></td>
								<td><c:out value="${event.eventDate}"/></td>
								<td><c:out value="${event.location}"/></td>
								<td><c:out value="${event.state}"/></td>
								<td><c:out value="${event.host.firstName}"/></td>
								<c:choose>
									<c:when test="${loggedUser.eventsHosted.contains(event)}">
										<td><a href="/events/${event.id}/edit">Edit</a> <a href="/events/${event.id}/delete">Delete</a></td>
									</c:when>
									<c:otherwise>
									<c:choose>
										<c:when test="${loggedUser.joinedEvents.contains(event)}">
											<td>Joined  <a href="/events/${event.id}/canceljoin">Cancel</a></td>
										</c:when>
										<c:otherwise>
											<td><a href="/events/${event.id}/join">Join</a></td>										
										</c:otherwise>
									</c:choose>
									</c:otherwise>
								</c:choose>
								
							</tr>
						</c:if>
					</c:forEach>
				</tbody>
			</table>
			<h3>Add an Event</h3>
			<form:form method="POST" action="/events/create" modelAttribute="eventObj" class="form card m-4 p-5">
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
						<form:select path="state" class="form-control">
							<c:forEach items="${states}" var="stateName">
								<form:option value="${stateName}"><c:out value="${stateName}"/></form:option>
							</c:forEach>
						</form:select>
						<form:errors path="state"/>
			       </div>
			     </div>  
		        <button type="submit" class="btn btn-primary w-25">Register</button>
		    </form:form>
		</div>
	</body>
</html>
