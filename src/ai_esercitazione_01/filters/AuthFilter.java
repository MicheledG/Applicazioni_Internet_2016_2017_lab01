package ai_esercitazione_01.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import ai_esercitazione_01.controllers.LoginServlet;

/**
 * Servlet Filter implementation class AuthFilter
 */
@WebFilter("/AuthFilter")
public class AuthFilter implements Filter {
	
	public static final String SESSION_ATTRIBUTE_LANDING_URL = "landingUrl";

    /**
     * @see Filter#destroy()
     */
    public void destroy() {
    }

    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        if (request instanceof HttpServletRequest) {
            HttpSession session = ((HttpServletRequest) request).getSession();
            if (session.getAttribute(LoginServlet.SESSION_ATTRIBUTE_USER)==null) {
   	             HttpServletResponse res = (HttpServletResponse) response;
   	             session.setAttribute(SESSION_ATTRIBUTE_LANDING_URL, ((HttpServletRequest) request).getRequestURI());
   	             res.sendRedirect("../login.jsp");
            } else {
                chain.doFilter(request, response);
                return;
            }
        }

        // pass the request along the filter chain
        chain.doFilter(request, response);
    }

    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
    }

}