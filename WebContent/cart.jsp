<%@page import="ai_esercitazione_01.controllers.EmptyCartServlet" %>
<%@page import="ai_esercitazione_01.controllers.UpdateCartServlet" %>
<%@page import="ai_esercitazione_01.model.CartService" %>
<%@page import="ai_esercitazione_01.model.Item" %>
<%@page import="ai_esercitazione_01.model.Ticket" %>
<%@ page import="java.util.Collection" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%

    CartService cartService;
    synchronized (session) {

        cartService = (CartService) session.getAttribute(CartService.ATTRIBUTE_NAME);
        if (cartService == null) {
            //internal server error -> cartService should be always present
            request.getSession().invalidate();
            request.getSession(true);
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;

        }

        Collection<Item> items = cartService.getItems();

%>

<jsp:include page="header.jsp" flush="true"/>

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
            <td><%=ticket.getType()%>
            </td>
            <td><%=String.format("%1$.2f", price)%> EUR</td>
            <td>
                <select name="<%=UpdateCartServlet.POST_PARAMETER_ITEM_QUANTITY+item.getID()%>">
                    <%
                        for (i = UpdateCartServlet.MIN_QUANTITY; i <= UpdateCartServlet.MAX_QUANTITY; i++) {
                            if (i == quantity) {
                    %>
                    <option value="<%=i %>" selected><%=i %>
                    </option>
                    <%
                    } else {
                    %>
                    <option value="<%=i %>"><%=i %>
                    </option>
                    <%
                        }
                    %>
                    <%
                        }
                    %>
                </select>
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
            <input class="btn btn-default" type="submit" value="Update Cart">
            <%
                if (total != 0) {
                    //there is at least one item to buy
            %>
            <a class="btn btn-default" href="private/checkout.jsp">Checkout</a>
            <%
                }
            %>
        </div>
    </div>
</form>

<%
    } //closing synchronized
%>


<form action="<%=EmptyCartServlet.URL%>" method="post">
    <input class="btn btn-default" type="submit" value="Empty Cart">
</form>

<jsp:include page="footer.jsp" flush="true"/>