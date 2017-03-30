<%@page import="ai_esercitazione_01.controllers.EmptyCartServlet" %>
<%@page import="ai_esercitazione_01.controllers.LogoutServlet" %>
<%@page import="ai_esercitazione_01.model.CartService" %>
<%@page import="ai_esercitazione_01.model.Item" %>
<%@page import="ai_esercitazione_01.model.Ticket" %>
<%@page import="ai_esercitazione_01.controllers.ConfirmPaymentServlet" %>
<%@page import="java.io.DataOutputStream" %>
<%@page import="java.net.HttpURLConnection" %>
<%@page import="java.net.URL" %>
<%@ page import="java.util.Collection" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%
    CartService cartService = (CartService) request.getSession().getAttribute(CartService.ATTRIBUTE_NAME);
    if (cartService == null) {
        //error -> should not be here
        request.getSession().invalidate();
        request.getSession(true);
        request.getRequestDispatcher("index.jsp").forward(request, response);
        return;
    }
    Collection<Item> items = cartService.getItems();
%>

<jsp:include page="../header.jsp" flush="true"/>

<h2 class="text-left">Order</h2>

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
        <td><%=ticket.getType()%>
        </td>
        <td><%=String.format("%1$.2f", price)%> EUR</td>
        <td><%=quantity%>
        </td>
        <td><%=String.format("%1$.2f", itemPrice)%> EUR</td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>

<div class="row">
    <div class="col-md-4">
        <p class="lead">Total: <%=String.format("%1$.2f", total)%> EUR</p>
    </div>
    <div class="col-md-4">
    </div>
    <div class="col-md-4">
        <form action="<%=ConfirmPayementServlet.URL%>" method="post">
            <input class="btn btn-default" type="submit" value="Confirm Payment">
        </form>
    </div>
</div>

<jsp:include page="../footer.jsp" flush="true"/>