<%@page import="ai_esercitazione_01.controllers.LoginServlet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<jsp:include page="header.jsp" flush="true" />

<h2 class="text-center">Login</h2>

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
	<div class="text-center">
		<%
			if (request.getAttribute("loginError") != null) {
		%>
		<%
			String error = request.getAttribute("loginError").toString();
		%>

		<p><%=error%>
		</p>
		<%
			}
		%>
	</div>
	<div class="text-center">

		<%
			if (request.getAttribute("yetLogged") != null) {
		%>
		<%
			String error2 = request.getAttribute("yetLogged").toString();
		%>

		<p><%=error2%>
		</p>
		<%
			}
		%>
	</div>

</form>
<p></p>
<jsp:include page="footer.jsp" flush="true" />