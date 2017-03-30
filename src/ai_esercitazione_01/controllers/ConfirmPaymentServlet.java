package ai_esercitazione_01.controllers;

import java.io.IOException;
import java.util.Collection;

import ai_esercitazione_01.model.CartService;
import ai_esercitazione_01.model.Item;
import ai_esercitazione_01.model.PaymentService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ConfirmPayementServlet
 */
@WebServlet("/"+ConfirmPaymentServlet.URL)
public class ConfirmPaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String URL = "confirm";

       
    	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    		PaymentService ps = (PaymentService) request.getSession().getAttribute(PaymentService.ATTRIBUTE_NAME);
    		if (ps.doPayment()) {
    			//if payment was good
    			CartService cs = (CartService) request.getSession().getAttribute(CartService.ATTRIBUTE_NAME);
    			if (cs == null) {
    	            //error -> should not be here
    	            request.getSession().invalidate();
    	            request.getSession(true);
    	            request.getRequestDispatcher("index.jsp").forward(request, response);
    	            return;
    	        }

    	        Collection<Item> items = cs.getItems();
    	        //make the cart empty
    	        for (Item item : items) {
    	                cs.removeItem(item.getID());
    	        }
    	        response.sendRedirect("private/confirm.jsp");    			
    		}
    		else {
    			//NON SO COSA FARE SE NON VA A BUON FINE, INVENTATE QUALCOSA :)
    			response.sendRedirect("private/checkout.jsp");
    		}
	}

}
