<%@page import="ai_esercitazione_01.controllers.AddToCartServlet"%>
<%@page import="ai_esercitazione_01.controllers.LoginServlet"%>
<%@page import="ai_esercitazione_01.model.Ticket"%>
<%@page import="ai_esercitazione_01.model.TicketService"%>
<%@page import="ai_esercitazione_01.model.User"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	TicketService ticketService = (TicketService) getServletContext()
			.getAttribute(TicketService.ATTRIBUTE_NAME);
	if (ticketService == null) {
		//internal server error -> ticketService should be always present
	}
	List<Ticket> tickets = ticketService.getTickets();

	//check if there is a user logged-in
	String username = "traveler";
	User user = (User) request.getSession().getAttribute(LoginServlet.SESSION_ATTRIBUTE_USER);
	if (user != null) {
		username = user.getUsername();
	}
%>

<jsp:include page="header.jsp" flush="true" />

<div class="jumbotron">
	<h1>
		Hello,
		<%=username%>!
	</h1>
	<!-- <h1>Hello, user_name!</h1> -->
	<p>Welcome to our web site, where you can find all the tickets you
		need, both for single and multiple rides as well as subscriptions at
		different levels.</p>
</div>

<h2 class="text-left">Tickets</h2>

<table class="table table-striped">
	<thead>
		<tr>
			<th>Type</th>
			<th>Description</th>
			<th>Validity</th>
			<th>Price</th>
			<th>Add to cart</th>
		</tr>
	</thead>
	<tbody>
		<%
			for (Ticket ticket : tickets) {
		%>
		<tr>
			<td><%=ticket.getType()%></td>
			<td><%=ticket.getDescription()%></td>
			<td><%=ticket.getValidity()%> <%=ticket.getValidityTimeUnit()%>
			</td>
			<td><%=String.format("%1$.2f", ticket.getPrice())%> EUR</td>
			<td>
				<form action="<%=AddToCartServlet.URL%>" method="post">
					<input type="hidden" name="<%=AddToCartServlet.POST_PARAMETER_NAME_TICKET_ID%>" value="<%=ticket.getID()%>">
					<input class="btn btn-default" type="submit" value="Add">
				</form>
			</td>
		</tr>
		<%
			}
		%>
	</tbody>
</table>

<jsp:include page="footer.jsp" flush="true" />