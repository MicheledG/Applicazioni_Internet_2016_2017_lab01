package ai_esercitazione_01.controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/" + LogoutServlet.URL)
public class LogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public static final String URL = "logout";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    	//TODO: SYNCHRONIZATION RIGHT HERE IS A PROBLEM
		HttpSession session = request.getSession();
		//OTHER THREADS CAN INVALIDATE THE SESSION IN THIS PHASE
    	synchronized (session) { //should be an atomic operation
       		session.invalidate();
    		request.getSession(true);
            request.getRequestDispatcher("index.jsp").forward(request, response);
    	}
    
    }

}
