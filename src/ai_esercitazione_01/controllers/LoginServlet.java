package ai_esercitazione_01.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ai_esercitazione_01.model.LoginService;
import ai_esercitazione_01.model.LoginServiceImpl;
import ai_esercitazione_01.model.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession().getAttribute("utente")!=null) {
			request.setAttribute("yetLogged", "You are logged-in yet!");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			
		}
		else {
			if (request.getParameter("utente")!=null && request.getParameter("passwd")!=null) {
			
				String user = request.getParameter("utente").toString();
				String password = request.getParameter("passwd").toString();
			
				LoginService ls = new LoginServiceImpl();			
				User loggedUser = ls.login(user, password);
				if (loggedUser == null) {
					request.setAttribute("loginError", "Login Failed: check user or password.");
					request.getRequestDispatcher("login.jsp").forward(request, response);
					//username or password not stored in DB
				}
				else {
					request.getSession().setAttribute("utente", loggedUser);
					response.sendRedirect("index.jsp");
					//all ok, the Servlet set the Session Attribute "utente" containing the User just logged
				}
			}
			else {
				response.sendRedirect("login.jsp");
				//System.out.print("Non ho preso i dati"); //just for debug, if username or password is null
				//i could also set an attribute "inputMissing" with a String "Insert username/password too"
			
			}
		}
    }
}
