package ai_esercitazione_01.listeners;

import ai_esercitazione_01.model.*;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class AppListener implements ServletContextListener, HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent arg0) {
        CartService cartService = new CartServiceImpl();
        arg0.getSession().setAttribute(CartService.ATTRIBUTE_NAME, cartService);
        PaymentService paymentService = new PaymentServiceImpl();
        arg0.getSession().setAttribute(PaymentService.ATTRIBUTE_NAME, paymentService);
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
        //inject login service into the app context
        LoginService loginService = new LoginServiceImpl();
        arg0.getServletContext().setAttribute(LoginService.ATTRIBUTE_NAME, loginService);
    }

}
