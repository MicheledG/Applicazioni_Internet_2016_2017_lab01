<%@page import="ai_esercitazione_01.controllers.LogoutServlet"%>
<%@page import="ai_esercitazione_01.controllers.UpdateCartServlet"%>
<%@page import="java.util.Collection"%>
<%@page import="ai_esercitazione_01.controllers.LoginServlet"%>
<%@page import="ai_esercitazione_01.model.*"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	CartService cartService = (CartService) request.getSession().getAttribute(CartService.ATTRIBUTE_NAME);
	if (cartService == null) {
		//internal server error -> cartService should be always present
		response.sendRedirect(LogoutServlet.URL);
	}
	Collection<Item> items = cartService.getItems();
%>

<jsp:include page="header.jsp" flush="true" />

<h2 class="text-left">My Cart</h2>


<form action="<%=UpdateCartServlet.URL%>" method="post">
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
	
			for (Item item : items) {
				int quantity = item.getQuantity();
				Ticket ticket = item.getTicket();
				Double price = ticket.getPrice();
				Double itemPrice = price * quantity;
				total += itemPrice;
				int i;
			%>
			<tr>
				<td><%=ticket.getType()%></td>
				<td><%=String.format("%1$.2f",price) %></td>
				<td>
					<select name="<%=UpdateCartServlet.POST_PARAMETER_ITEM_QUANTITY+item.getID()%>">
						<%
						for(i = UpdateCartServlet.MIN_QUANTITY; i <= UpdateCartServlet.MAX_QUANTITY; i++) {
							if(i == quantity){
							%>								
								<option value="<%=i %>" selected><%=i %></option>
							<%
							} else {
							%>
								<option value="<%=i %>"><%=i %></option>
							<%
							} 
							%>
						<%
						} 
						%>
					</select>
				</td>
				<td><%=String.format("%1$.2f",itemPrice)%> EUR</td>
			</tr>
			<%
			}
			%>
		</tbody>
	</table>
	
	<div class="row">
		<div class="col-md-4">
			<p class="lead">Total: <%=String.format("%1$.2f",total)%> EUR</p>
		</div>
		<div class="col-md-4">
		</div>
		<div class="col-md-4">
			<input class="btn btn-default" type="submit" value="Update Cart">
			<a class="btn btn-default" href="private/checkout.jsp">Checkout</a>
		</div>
	</div>
	
	
	
</form>

<jsp:include page="footer.jsp" flush="true" />