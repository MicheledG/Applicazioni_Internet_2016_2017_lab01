package ai_esercitazione_01.controllers;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ai_esercitazione_01.model.CartService;
import ai_esercitazione_01.model.Item;

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
		if(cartService == null){
			//error -> should not be here
	    	String url = request.getScheme() + "://" +
			request.getServerName() + ":" +
			request.getServerPort() +
			request.getContextPath() + "/" +
			LogoutServlet.URL;
			java.net.URL obj = new java.net.URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			//add reuqest header
			con.setRequestMethod("POST");
			
			// Send post request
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.flush();
			wr.close();
			
			return;
		}
		
		
		Collection<Item> items = cartService.getItems();
		
		// retrieve quantity of each ticket contained into the post parameters
		for(Item item : items){
			String quantity = request.getParameter(UpdateCartServlet.POST_PARAMETER_ITEM_QUANTITY+item.getID());
			if(quantity!=null){
				try{
					int intQuantity = Integer.parseInt(quantity); 
					if(intQuantity < UpdateCartServlet.MIN_QUANTITY || intQuantity > UpdateCartServlet.MAX_QUANTITY){
						//wrong quantity value
					}
					else if(intQuantity == 0){
						cartService.removeItem(item.getID());
					} 
					else{
						item.setQuantity(intQuantity);
					}
				}catch (NumberFormatException e) {
					//ignore the quantity for this item
					continue;
				}
			}
			else {
				//missing item quantity within the request, remove the item
				cartService.removeItem(item.getID());
			}
		}
		
		request.getServletContext().getRequestDispatcher("/cart.jsp").forward(request, response);
	}

}
