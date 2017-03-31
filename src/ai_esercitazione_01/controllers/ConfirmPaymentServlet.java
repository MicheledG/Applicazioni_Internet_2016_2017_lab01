package ai_esercitazione_01.controllers;

import ai_esercitazione_01.model.CartService;
import ai_esercitazione_01.model.CartServiceImpl;
import ai_esercitazione_01.model.PaymentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet implementation class ConfirmPayementServlet
 */
@WebServlet("/" + ConfirmPaymentServlet.URL)
public class ConfirmPaymentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public static final String URL = "confirm";


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        synchronized (session) {

            PaymentService ps = (PaymentService) session.getAttribute(PaymentService.ATTRIBUTE_NAME);
            if (ps.doPayment()) {

                CartService cs = (CartService) session.getAttribute(CartService.ATTRIBUTE_NAME);
                if (cs == null) {
                    //error -> should not be here
                    request.getSession().invalidate();
                    request.getSession(true);
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                    return;
                }

                CartService cartServiceEmpty = new CartServiceImpl();
                session.setAttribute(CartService.ATTRIBUTE_NAME, cartServiceEmpty);
            }
//	    		else {
//	    			//doPayment is always successful
//	    			response.sendRedirect("private/checkout.jsp");
//	    		}
        }

        response.sendRedirect("confirm.jsp");

    }

}
