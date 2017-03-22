package ai_esercitazione_01.model;

import java.util.ArrayList;
import java.util.List;

public class TicketServiceImpl implements TicketService {

	@Override
	public List<Ticket> getTickets() {
		TicketDAO ticketDAO = TicketDAO.getInstance();
		List<Ticket> ticketList = new ArrayList<>(ticketDAO.readTickets());
		return ticketList;
	}

}
