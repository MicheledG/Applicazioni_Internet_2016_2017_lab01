package ai_esercitazione_01.controllers;

import ai_esercitazione_01.filters.AuthFilter;
import ai_esercitazione_01.model.LoginService;
import ai_esercitazione_01.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;

/**
 * Servlet implementation class LoginServlet
 */

//TODO: avoid writing names of post parameters and of attributes to set into session and into the context
//directly into the code, write them once and then recall
//e.g. SESSION_ATTRIBUTE_USER used as static final
//Use these static strings also into the .jsp to avoid writing wrong names

@WebServlet("/" + LoginServlet.URL)
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public static final String URL = "login";

    public static final String SESSION_ATTRIBUTE_USER = "user";

    public static final String SESSION_ATTRIBUTE_LOGIN_ERROR = "loginError";
    
    public static final String POST_PARAMETER_USERNAME = "username";
    public static final String POST_PARAMETER_PASSWORD = "password";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	if (request.getSession().getAttribute(LoginServlet.SESSION_ATTRIBUTE_USER)!=null) {
			request.getRequestDispatcher("login.jsp").forward(request, response);	
		}
    	
		else {
			
			String username = request.getParameter(LoginServlet.POST_PARAMETER_USERNAME);
			String password = request.getParameter(LoginServlet.POST_PARAMETER_PASSWORD); 
			
			if (username!=null && password!=null) {
			
				LoginService loginService = (LoginService) request.getServletContext().getAttribute(LoginService.ATTRIBUTE_NAME);			
				if(loginService == null){
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
				
				User loggedUser = loginService.login(username, password);
				
				if (loggedUser == null) {
					//username or password not stored in DB					
					request.setAttribute(LoginServlet.SESSION_ATTRIBUTE_LOGIN_ERROR, "true");
					request.getRequestDispatcher("login.jsp").forward(request, response);
				}
				else {
					//all ok, the Servlet set the Session Attribute "user" containing the User just logged
					request.getSession().setAttribute(LoginServlet.SESSION_ATTRIBUTE_USER, loggedUser);
					String landingUrl = (String) request.getSession().getAttribute(AuthFilter.SESSION_ATTRIBUTE_LANDING_URL);
					if (landingUrl != null) {
						response.sendRedirect(landingUrl);
						return;
					} else {
						response.sendRedirect("index.jsp");
						return;
					}
				}
			}
			else {
				response.sendRedirect("login.jsp");
				return;
			}
		}
    }
}
