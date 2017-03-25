<%@page import="ai_esercitazione_01.controllers.AddToCartServlet"%>
<%@page import="ai_esercitazione_01.model.Ticket"%>
<%@page import="java.util.List"%>
<%@page import="ai_esercitazione_01.model.TicketService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="en">
<head>
  <title>Esercitazione01</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
  	
  	<% 
  		TicketService ticketService = (TicketService) getServletContext().getAttribute("ticketService"); 	
  		if(ticketService == null){
  			//internal server error -> ticketService should be always present
  		}
  		List<Ticket> tickets = ticketService.getTickets();
  	%>
  	
	<div class="container-fluid">
		
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
		    		<td><%= ticket.getType()%></td>
		    		<td><%= ticket.getDescrption()%></td>
		    		<td><%= ticket.getValidity()%> minuti</td>
		    		<td><%= Double.toString(ticket.getPrice())%> EUR</td>
		    		<td>
		    			<form action="<%=AddToCartServlet.URL%>" method="post">
    						<input type="hidden" name="<%=AddToCartServlet.POST_PARAMETER_NAME_TICKET_ID%>" value="<%=ticket.getID()%>"> 
							<button type="submit" class="btn btn-default">Add</button>
		    			</form>
		    		</td>
		    	</tr>
		    	<%} %>
	    	</tbody>
		</table>
		
	</div>

</body>
</html>