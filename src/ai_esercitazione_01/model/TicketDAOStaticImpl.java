package ai_esercitazione_01.model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class TicketDAOStaticImpl implements TicketDAO {

    private static ConcurrentHashMap<String, Ticket> tickets = new ConcurrentHashMap<>();

    public TicketDAOStaticImpl() {
        Ticket ticket1 = new Ticket("Urban Ticket", 1.5);
        Ticket ticket2 = new Ticket("Suburban Ticket", 1.5);
        Ticket ticket3 = new Ticket("Daily Ticket", 5.0);
        Ticket ticket4 = new Ticket("Weekly Ticket", 15.00);
        Ticket ticket5 = new Ticket("Monthly Ticket", 32.00);

        ticket1.setDescription("It allows a single ride on a bus within the city area");
        ticket1.setValidity(90);
        ticket1.setValidityTimeUnit(Ticket.VALIDITY_TIME_UNIT_MIN);

        ticket2.setDescription("It allows a single ride on a bus within the first city-ring area");
        ticket2.setValidity(120);
        ticket2.setValidityTimeUnit(Ticket.VALIDITY_TIME_UNIT_MIN);
        
        ticket3.setDescription("It allows multiple rides in a day on a bus within the city area");
        ticket3.setValidity(1);
        ticket3.setValidityTimeUnit(Ticket.VALIDITY_TIME_UNIT_DAY);
        
        ticket4.setDescription("It allows multiple rides in a week on a bus within the city area");
        ticket4.setValidity(7);
        ticket4.setValidityTimeUnit(Ticket.VALIDITY_TIME_UNIT_DAY);

        ticket5.setDescription("It allows multiple rides in a month on a bus within the city area");
        ticket5.setValidity(28);
        ticket5.setValidityTimeUnit(Ticket.VALIDITY_TIME_UNIT_DAY);

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
