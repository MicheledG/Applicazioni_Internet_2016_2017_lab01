package ai_esercitazione_01.controllers;

import ai_esercitazione_01.model.CartService;
import ai_esercitazione_01.model.Item;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@WebServlet("/" + EmptyCartServlet.URL)
public class EmptyCartServlet extends HttpServlet {
    
	private static final long serialVersionUID = 1L;
	
	public static final String URL = "emptyCart";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    	// retrieve information from SESSION
        CartService cartService = (CartService) request.getSession().getAttribute(CartService.ATTRIBUTE_NAME);

        if (cartService == null) {
            //error -> should not be here
            request.getSession().invalidate();
            request.getSession(true);
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }

        Collection<Item> items = cartService.getItems();

        // retrieve quantity of each ticket contained into the post parameters
        for (Item item : items) {
                cartService.removeItem(item.getID());
        }

        request.getServletContext().getRequestDispatcher("/cart.jsp").forward(request, response);
    }

}
