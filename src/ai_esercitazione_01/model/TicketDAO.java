package ai_esercitazione_01.model;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TicketDAO {
	
	private static TicketDAO instance = null;
	
	private static Map<String,Ticket> ticketMap = new ConcurrentHashMap<>();
	
	protected TicketDAO() {
	}
	
	public static TicketDAO getInstance() {
		if (instance == null) {
			instance = new TicketDAO();
		}
		return instance;
	}
	
	public void createTicket(Ticket ticket) {
		ticketMap.put(ticket.getID(), ticket);
		return;
	}
	
	public Collection<Ticket> readTickets() {
		return ticketMap.values();
	}
	
	public void updateTicket(Ticket ticket) {
		ticketMap.replace(ticket.getID(), ticket);
		return;
	}
	
	public void deleteTicket(String id) {
		ticketMap.remove(id);
	}
}
