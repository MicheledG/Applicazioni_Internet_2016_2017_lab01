<%@page import="java.util.List"%>
<%@page import="ai_esercitazione_01.controllers.AddToCartServlet"%>
<%@page import="ai_esercitazione_01.model.Ticket"%>
<%@page import="ai_esercitazione_01.model.TicketService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<% 
	TicketService ticketService = (TicketService) getServletContext().getAttribute(TicketService.ATTRIBUTE_NAME); 	
	if (ticketService == null) {
		//internal server error -> ticketService should be always present
	}
	List<Ticket> tickets = ticketService.getTickets();
%>

<jsp:include page="header.jsp" flush="true" />

	<div class="jumbotron">
	  <h1>Hello, traveler!</h1>
	  <!-- <h1>Hello, user_name!</h1> -->
	  <p>Welcome to our web site, where you can find all the tickets you need, both for single and multiple rides
	  	 as well as subscriptions at different levels.</p>
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
  			<%for (Ticket ticket:tickets) { %>
	    	<tr>
	    		<td><%= ticket.getType() %></td>
	    		<td><%= ticket.getDescription() %></td>
	    		<td><%= ticket.getValidity() %> minuti</td>
	    		<td><%= Double.toString(ticket.getPrice()) + "0" %> EUR</td>
	    		<td>
	    			<form action="<%= AddToCartServlet.URL %>" method="post">
   						<input type="hidden" name="<%= AddToCartServlet.POST_PARAMETER_NAME_TICKET_ID %>" value="<%= ticket.getID() %>"> 
						<button type="submit" class="btn btn-default">Add</button>
	    			</form>
	    		</td>
	    	</tr>
	    	<%} %>
  		</tbody>
	</table>

<jsp:include page="footer.jsp" flush="true" />