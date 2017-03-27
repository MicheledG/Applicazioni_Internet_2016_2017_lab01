package ai_esercitazione_01.model;

import java.util.List;

public interface TicketService {

    public String ATTRIBUTE_NAME = "ticketService";

    public Ticket getTicket(String ticketID);

    public List<Ticket> getTickets();
}
