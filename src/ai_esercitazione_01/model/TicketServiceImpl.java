package ai_esercitazione_01.model;

import java.util.List;

public class TicketServiceImpl implements TicketService {

    TicketDAOStaticImpl ticketDAOStatic = new TicketDAOStaticImpl();

    @Override
    public Ticket getTicket(String ticketID) {
        return ticketDAOStatic.getTicket(ticketID);
    }

    @Override
    public List<Ticket> getTickets() {
        return ticketDAOStatic.getTickets();
    }

}
