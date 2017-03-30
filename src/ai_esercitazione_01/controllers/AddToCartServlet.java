package ai_esercitazione_01.controllers;

import ai_esercitazione_01.model.CartService;
import ai_esercitazione_01.model.Item;
import ai_esercitazione_01.model.Ticket;
import ai_esercitazione_01.model.TicketService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;


/**
 * Servlet implementation class AddToCartServlet
 */
@WebServlet("/" + AddToCartServlet.URL)
public class AddToCartServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final int DEFAULT_NEW_QUANTITY = 1;

	public static final String URL = "addTicket";
	public static final String POST_PARAMETER_NAME_TICKET_ID = "ticketID";

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// retrieve information from CONTEXT
		TicketService ticketService = (TicketService) request.getServletContext().getAttribute(TicketService.ATTRIBUTE_NAME);
		// retrieve information from SESSION
		CartService cartService = (CartService) request.getSession().getAttribute(CartService.ATTRIBUTE_NAME);
		
		if(ticketService == null || cartService == null){
			//error -> should not be here
			request.getSession().invalidate();
	        request.getSession(true);
	        request.getRequestDispatcher("index.jsp").forward(request, response);			
			return;
		}
		
		
		// retrieve information from REQUEST
		String ticketID = request.getParameter(AddToCartServlet.POST_PARAMETER_NAME_TICKET_ID);

		// ckeck if ticketID is a valid ID
		if (ticketID != null) {			
			// check if it is a new ticket to add to the cart	
			if (!cartService.containsItem(ticketID)) {
				// retrieve info about the ticket
				Ticket newTicket = ticketService.getTicket(ticketID);
				if (newTicket != null) {
					// insert a new item with this ticket into the cart
					Item newItem = new Item(newTicket, AddToCartServlet.DEFAULT_NEW_QUANTITY);
					cartService.addItem(newItem);
				}
			}
			else {
				//the ticket is already into the cart -> increment its quantity
				cartService.getItem(ticketID).increaseQuantity(1);
			}
		}

		request.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
	}

}
