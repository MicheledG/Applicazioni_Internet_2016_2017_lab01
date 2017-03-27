package ai_esercitazione_01.model;

import java.util.List;

public interface TicketDAO {
    public void addTicket(Ticket ticket);

    public Ticket getTicket(String id);

    public List<Ticket> getTickets();

    public void updateTicket(Ticket ticket);

    public void deleteTicket(String id);
}