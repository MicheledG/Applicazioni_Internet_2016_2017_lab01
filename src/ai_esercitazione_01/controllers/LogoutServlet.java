package ai_esercitazione_01.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/"+LogoutServlet.URL)
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	public static final String URL = "logout";
	
	//TODO: Transform into a Post -> modify navbar to use a form instead of link!
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.getSession().invalidate();
		request.getSession(true);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}
