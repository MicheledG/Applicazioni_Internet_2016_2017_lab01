<%@page import="ai_esercitazione_01.controllers.LoginServlet" %>
<%@page import="ai_esercitazione_01.model.Ticket" %>
<%@page import="ai_esercitazione_01.model.TicketService" %>
<%@page import="ai_esercitazione_01.model.User" %>
<%@page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%
    TicketService ticketService = (TicketService) getServletContext().getAttribute(TicketService.ATTRIBUTE_NAME);
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

<jsp:include page="../header.jsp" flush="true"/>

<div class="jumbotron">
    <h1>Hello, <%=username %>!</h1>
</div>

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
        <tr>
            <td>urban</td>
            <td>1.50 €</td>
            <td>3</td>
            <td>4.50 €</td>
        </tr>
        <tr>
            <td>suburban</td>
            <td>2.00 €</td>
            <td>1</td>
            <td>2.00 €</td>
        </tr>
        <tr>
            <td>daily</td>
            <td>5.00 €</td>
            <td>2</td>
            <td>10.00 €</td>
        </tr>
        <tr>
            <td colspan="2"></td>
            <td>TOTAL</td>
            <td>16.50 €</td>
        </tr>
        <tr>
            <td colspan="4" align="center">
                <form method="get" action="checkout.jsp">
                    <button type="submit">Check Out</button>
                </form>
            </td>
        </tr>
        </tbody>
    </table>
</div>

<jsp:include page="../footer.jsp" flush="true"/>