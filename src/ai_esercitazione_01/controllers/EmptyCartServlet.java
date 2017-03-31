package ai_esercitazione_01.controllers;

import ai_esercitazione_01.model.CartService;
import ai_esercitazione_01.model.CartServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/" + EmptyCartServlet.URL)
public class EmptyCartServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public static final String URL = "emptyCart";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        HttpSession session = request.getSession();
        // retrieve information from SESSION
        synchronized (session) {

            CartService cartService = (CartService) session.getAttribute(CartService.ATTRIBUTE_NAME);

            if (cartService == null) {
                //error -> should not be here
                session.invalidate();
                request.getSession(true);
                request.getRequestDispatcher("index.jsp").forward(request, response);
                return;
            }

            CartService cartServiceEmpty = new CartServiceImpl();
            session.setAttribute(CartService.ATTRIBUTE_NAME, cartServiceEmpty);

        }

        request.getServletContext().getRequestDispatcher("/cart.jsp").forward(request, response);
    }

}
