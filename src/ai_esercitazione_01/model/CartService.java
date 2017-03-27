package ai_esercitazione_01.model;

import java.util.Collection;

public interface CartService {
	
	public String ATTRIBUTE_NAME = "cartService";
	
	public boolean containsItem(String ticketId);
	
	public void addItem(Item item);
	public void removeItem(String ticketId);
	public void modifyItem(String ticketId, int quantity);
	
	public Collection<Item> getItems();
	public Integer getItemCount();
	public Double getTotal();
}
