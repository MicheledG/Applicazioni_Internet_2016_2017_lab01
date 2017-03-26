<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<jsp:include page="header.jsp" flush="true" />
	
	<h2 class="text-center">Login</h2>
	
	<form class="col-md-6 col-md-offset-3" method="post" action="login">
		<div class="form-group">
		    <label for="username">Username</label>
		    <input type="text" class="form-control" name="utente" id="username" placeholder="Username">
	  	</div>
	  	<div class="form-group">
	    	<label for="password">Password</label>
	    	<input type="password" class="form-control" name="passwd" id="password" placeholder="Password">
	  	</div>
	  	<button type="submit" class="btn btn-default">Submit</button>
	  	
	</form>
	<div >
	  	<% if (request.getAttribute("loginError")!= null) {%>
	  		<% String error = request.getAttribute("loginError").toString(); %>
	  	
	  		<%=error %>
	  	<%} %>
	  	</div>

<jsp:include page="footer.jsp" flush="true" />