package ai_esercitazione_01.model;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TicketDAO {
	
	private static TicketDAO instance = null;
	
	private static Map<String,Ticket> ticketMap = new ConcurrentHashMap<>();
	
	static {
		//insert data into the ticket DB
		Ticket ticket1 = new Ticket();
		Ticket ticket2 = new Ticket();
		Ticket ticket3 = new Ticket();
		Ticket ticket4 = new Ticket();
		
		ticket1.setType("Urbano");
		ticket1.setDescription("Valido su tutti i mezzi di superficie della città");
		ticket1.setValidity(90);
		ticket1.setPrice(1.5);
		ticketMap.put(ticket1.getID(), ticket1);
		
		ticket2.setType("Suburbano");
		ticket2.setDescription("Valido su tutti i mezzi di superficie della cintura");
		ticket2.setValidity(90);
		ticket2.setPrice(1.5);
		ticketMap.put(ticket2.getID(), ticket2);
		
		ticket3.setType("Urbano + Suburbano");
		ticket3.setDescription("Valido su tutti i mezzi di superficie della città e della cintura");
		ticket3.setValidity(120);
		ticket3.setPrice(1.7);
		ticketMap.put(ticket3.getID(), ticket3);
		
		ticket4.setType("Intera rete");
		ticket4.setDescription("Valido su tutti i mezzi della città e della cintura");
		ticket4.setValidity(120);
		ticket4.setPrice(2.0);
		ticketMap.put(ticket4.getID(), ticket4);
	}
	
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
	
	//retrieve information about a single entry
	public Ticket readTicket(String ticketID){
		return ticketMap.get(ticketID);
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
