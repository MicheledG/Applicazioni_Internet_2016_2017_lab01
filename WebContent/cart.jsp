<%@page import="ai_esercitazione_01.controllers.LoginServlet"%>
<%@page import="ai_esercitazione_01.controllers.UpdateQuantitiesServlet"%>
<%@page import="ai_esercitazione_01.model.*"%>
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

	CartService cartService = (CartService) request.getSession().getAttribute(CartService.ATTRIBUTE_NAME);
%>

<jsp:include page="header.jsp" flush="true" />

<h2 class="text-left">My Cart</h2>

<div class="jumbotron">
	<table class="table table-striped">
		<thead>
			<tr>
				<th>Type</th>
				<th>Price</th>
				<th>Quantity</th>
				<th>Subtotal</th>
			</tr>
		</thead>
		<tbody>
			<%
				double total = 0;

				for (Item item : cartService.getItems()) {
					Double price = item.getTicket().getPrice();
					int quantity = item.getQuantity();
					total += (price * quantity);
			%>
			<tr>
				<td><%=item.getTicket().getType()%></td>
				<td><%=price%></td>
				<td>
					<form action="<%=UpdateQuantitiesServlet.URL%>" method="post">
						<input type="hidden" value="<%=item.getID()%>"/>
						<button type="submit">-</button>
						<input name="<%=item.getID()%>" type="text" value="<%=quantity%>">
						<button type="submit">+</button>
					</form>
				</td>
				<td><%=price * quantity%> EUR</td>
			</tr>
			<%
				}
			%>
			<tr align="center">
				<td colspan="2">
					<form method="post">
						<button>update cart</button>
					</form>
				</td>
				<td colspan="2">
					<form action="checkout.jsp">
						<button>checkout</button>
					</form>
				</td>
			</tr>
			<tr align="center">
				<td colspan="2">TOTAL</td>
				<td colspan="2"><%=total%> EUR</td>
			</tr>
		</tbody>
	</table>
</div>

<jsp:include page="footer.jsp" flush="true" />