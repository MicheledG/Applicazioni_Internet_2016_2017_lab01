package ai_esercitazione_01.controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/" + LogoutServlet.URL)
public class LogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public static final String URL = "logout";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
        request.getSession(true);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

}
