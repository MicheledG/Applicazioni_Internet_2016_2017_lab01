<%@page import="ai_esercitazione_01.model.User"%>
<%@page import="ai_esercitazione_01.controllers.LoginServlet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<jsp:include page="header.jsp" flush="true" />


<h2 class="text-center">Login</h2>

<% User user = null;
 
	synchronized(session) {
	user = (User) session.getAttribute(LoginServlet.SESSION_ATTRIBUTE_USER);
}
if(user == null) {	
%>
	<div class="row">
		<form class="col-md-6 col-md-offset-3" method="post"
			action="<%=LoginServlet.URL%>">
			<div class="form-group">
				<label for="username">Username</label> <input type="text"
					class="form-control" name="<%=LoginServlet.POST_PARAMETER_USERNAME%>"
					id="username" placeholder="Username">
			</div>
			<div class="form-group">
				<label for="password">Password</label> <input type="password"
					class="form-control" name="<%=LoginServlet.POST_PARAMETER_PASSWORD%>"
					id="password" placeholder="Password">
			</div>
			<button type="submit" class="btn btn-default">Submit</button>
		</form>
	</div>	
	
		
	<%
	if (request.getAttribute(LoginServlet.SESSION_ATTRIBUTE_LOGIN_ERROR) != null) {
	%>
		<div class="row">
			<div class="col-md-4"></div>
			<div class="col-md-4">
				<div class="alert alert-danger text-center">
  					<strong>Error:</strong> wrong username or password.
				</div>
			</div>
			<div class="col-md-4"></div>	
		</div>
				
	<%
	}
}
else {	
%>
	<div class="row">
		<div class="col-md-4"></div>
		<div class="col-md-4">
			<div class="alert alert-info text-center">
  				<strong>Info:</strong> user <%=user.getUsername() %> already logged.
			</div>
		</div>
		<div class="col-md-4"></div>	
	</div>
	
<%
}
%>



<jsp:include page="footer.jsp" flush="true" />