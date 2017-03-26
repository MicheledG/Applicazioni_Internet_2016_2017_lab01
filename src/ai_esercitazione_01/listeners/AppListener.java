package ai_esercitazione_01.listeners;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import ai_esercitazione_01.model.CartService;
import ai_esercitazione_01.model.CartServiceImpl;
import ai_esercitazione_01.model.TicketService;
import ai_esercitazione_01.model.TicketServiceImpl;

@WebListener
public class AppListener implements ServletContextListener, HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
//		UsersTable usersTable = new UsersTable();
//		LoginService loginService = new LoginServiceImpl(usersTable);
//		arg0.getSession().setAttribute("loginService", loginService);
		CartService cartService = new CartServiceImpl();
		arg0.getSession().setAttribute(CartService.ATTRIBUTE_NAME, cartService);
	}
	
	
	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		//inject ticket service into the app context
		TicketService ticketService = new TicketServiceImpl();
		arg0.getServletContext().setAttribute(TicketService.ATTRIBUTE_NAME, ticketService);
	}

}
