package ai_esercitazione_01.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/" + UpdateQuantitiesServlet.URL)
public class UpdateQuantitiesServlet extends HttpServlet {
	
	public static final String URL = "UpdateQuantitiesServlet";
	
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//int username = request.getParameter(LoginServlet.POST_PARAMETER_USERNAME);
		request.getServletContext().getRequestDispatcher("cart.jsp").forward(request, response);
	}

}
