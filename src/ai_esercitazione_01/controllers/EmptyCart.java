package ai_esercitazione_01.controllers;

import ai_esercitazione_01.model.CartService;
import ai_esercitazione_01.model.Item;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

@WebServlet("/" + EmptyCart.URL)
public class EmptyCart extends HttpServlet {
    public static final String URL = "emptyCart";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute(CartService.ATTRIBUTE_NAME);

        Collection<Item> items = null;
        request.getSession().setAttribute(CartService.ATTRIBUTE_NAME, items);

        request.getServletContext().getRequestDispatcher("/cart.jsp").forward(request, response);
    }
}
