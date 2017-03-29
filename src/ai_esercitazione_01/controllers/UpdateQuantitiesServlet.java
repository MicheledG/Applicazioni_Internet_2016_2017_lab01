package ai_esercitazione_01.controllers;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ai_esercitazione_01.model.CartService;
import ai_esercitazione_01.model.Item;

@WebServlet("/" + UpdateQuantitiesServlet.URL)
public class UpdateQuantitiesServlet extends HttpServlet {
	
	public static final String URL = "UpdateQuantitiesServlet";
	
	public static final String POST_PARAMETER_ITEM_QUANTITY = "quantity";
	
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// retrieve information from SESSION
		CartService cartService = (CartService) request.getSession().getAttribute(CartService.ATTRIBUTE_NAME);
		Collection<Item> items = cartService.getItems();
		
		// retrieve quantity of each ticket contained into the post parameters
		for(Item item : items){
			String quantity = request.getParameter(UpdateQuantitiesServlet.POST_PARAMETER_ITEM_QUANTITY+item.getID());
			if(quantity!=null){
				try{
					int intQuantity = Integer.parseInt(quantity); 
					item.setQuantity(intQuantity);
				}catch (NumberFormatException e) {
					// TODO: handle exception
				}
			}
			else {
				//TODO
				//missing item into the ticket
			}
		}
		
		request.getServletContext().getRequestDispatcher("/cart2.jsp").forward(request, response);
	}

}
