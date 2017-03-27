package ai_esercitazione_01.model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class TicketDAOStaticImpl implements TicketDAO {

    private static ConcurrentHashMap<String, Ticket> tickets = new ConcurrentHashMap<>();

    public TicketDAOStaticImpl() {
        Ticket ticket1 = new Ticket("Urban", 1.5);
        Ticket ticket2 = new Ticket("Suburban", 1.5);
        Ticket ticket3 = new Ticket("Daily", 5.0);
        Ticket ticket4 = new Ticket("Weekly", 15.00);
        Ticket ticket5 = new Ticket("Montly", 32.00);

        ticket1.setDescription("Single ride");
        ticket1.setValidity(90);

        ticket2.setDescription("Single ride");
        ticket2.setValidity(120);

        ticket3.setDescription("Daily");
        ticket3.setValidity(1440);

        ticket4.setDescription("Weekly");
        ticket4.setValidity(10080);

        ticket5.setDescription("Montly");
        ticket5.setValidity(302400);

        tickets.put(ticket1.getID(), ticket1);
        tickets.put(ticket2.getID(), ticket2);
        tickets.put(ticket3.getID(), ticket3);
        tickets.put(ticket4.getID(), ticket4);
        tickets.put(ticket5.getID(), ticket5);
    }

    @Override
    public void addTicket(Ticket ticket) {
        tickets.put(ticket.getID(), ticket);
    }

    @Override
    public Ticket getTicket(String id) {
        return tickets.get(id);
    }

    @Override
    public List<Ticket> getTickets() {
        return new ArrayList<>(tickets.values());
    }

    @Override
    public void updateTicket(Ticket ticket) {
        tickets.get(ticket.getID()).setType(ticket.getType());
        tickets.get(ticket.getID()).setDescription(ticket.getDescription());
        tickets.get(ticket.getID()).setPrice(ticket.getPrice());
        tickets.get(ticket.getID()).setValidity(ticket.getValidity());
    }

    @Override
    public void deleteTicket(String id) {
        tickets.remove(id);
    }
}
