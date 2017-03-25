package ai_esercitazione_01.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ai_esercitazione_01.model.CartService;
import ai_esercitazione_01.model.Item;
import ai_esercitazione_01.model.Ticket;
import ai_esercitazione_01.model.TicketService;

/**
 * Servlet implementation class AddToCartServlet
 */
@WebServlet("/"+AddToCartServlet.URL)
public class AddToCartServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final int DEFAULT_NEW_QUANTITY = 1;
	
	public static final String URL = "addTicket";
	public static final String POST_PARAMETER_NAME_TICKET_ID = "ticketID"; 
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//retrieve information from CONTEXT
		TicketService ticketService = (TicketService) request.getServletContext().getAttribute(TicketService.ATTRIBUTE_NAME);
		//retrieve information from SESSION
		CartService cartService = (CartService) request.getSession().getAttribute(CartService.ATTRIBUTE_NAME);
		//retrieve information from REQUEST
		String ticketID = request.getParameter(AddToCartServlet.POST_PARAMETER_NAME_TICKET_ID);
		
		//ckeck if ticketID is a valid ID
		if(ticketID != null){
			//retrieve ticket info
			Ticket newTicket = ticketService.getTicket(ticketID);
			if(newTicket != null){
				// create the new item
				Item newItem = new Item(newTicket, AddToCartServlet.DEFAULT_NEW_QUANTITY);
				// insert the new item into the cart
				cartService.addItem(newItem);
			}
		}
		
		request.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
	}

}
