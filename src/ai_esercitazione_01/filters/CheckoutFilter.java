package ai_esercitazione_01.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ai_esercitazione_01.model.CartService;

/**
 * Servlet Filter implementation class CheckoutFilter
 */
@WebFilter("/CheckoutFilter")
public class CheckoutFilter implements Filter {

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		if (request instanceof HttpServletRequest) {
            HttpSession session = ((HttpServletRequest) request).getSession();
            CartService cartService = (CartService) session.getAttribute(CartService.ATTRIBUTE_NAME);
            if(cartService == null){
            	session.invalidate();
                HttpServletRequest httpRequest = (HttpServletRequest) request; 
            	httpRequest.getSession(true);
                httpRequest.getRequestDispatcher("../index.jsp").forward(request, response);
            }
            //check if there is at least one item
            if (cartService.getItemCount() > 0) {
            	chain.doFilter(request, response);
                return;
            } else {
            	//no items into the cart -> no checkout
            	HttpServletResponse res = (HttpServletResponse) response;
            	res.sendRedirect("../index.jsp");
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
		// TODO Auto-generated method stub
	}

}
