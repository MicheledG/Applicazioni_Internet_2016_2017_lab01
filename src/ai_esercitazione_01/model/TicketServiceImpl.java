package ai_esercitazione_01.model;

import java.util.ArrayList;
import java.util.List;

public class TicketServiceImpl implements TicketService {

	@Override
	public Ticket getTicket(String ticketID) {
		TicketDAO ticketDAO = TicketDAO.getInstance();
		Ticket ticket = ticketDAO.readTicket(ticketID);
		return ticket;
	}
	
	@Override
	public List<Ticket> getTickets() {
		TicketDAO ticketDAO = TicketDAO.getInstance();
		List<Ticket> ticketList = new ArrayList<>(ticketDAO.readTickets());
		return ticketList;
	}

}
