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

@WebServlet("/" + UpdateCartServlet.URL)
public class UpdateCartServlet extends HttpServlet {

    public static final String URL = "updateCart";

    public static final String POST_PARAMETER_ITEM_QUANTITY = "quantity";

    public static final int MIN_QUANTITY = 0;
    public static final int MAX_QUANTITY = 100;

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
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
            String quantity = request.getParameter(UpdateCartServlet.POST_PARAMETER_ITEM_QUANTITY + item.getID());
            if (quantity != null) {
                try {
                    int intQuantity = Integer.parseInt(quantity);
                    if (intQuantity < UpdateCartServlet.MIN_QUANTITY || intQuantity > UpdateCartServlet.MAX_QUANTITY) {
                        //wrong quantity value
                    } else if (intQuantity == 0) {
                        cartService.removeItem(item.getID());
                    } else {
                        item.setQuantity(intQuantity);
                    }
                } catch (NumberFormatException e) {
                    //ignore the quantity for this item
                    continue;
                }
            } else {
                //missing item quantity within the request, remove the item
                cartService.removeItem(item.getID());
            }
        }

        request.getServletContext().getRequestDispatcher("/cart.jsp").forward(request, response);
    }

}
