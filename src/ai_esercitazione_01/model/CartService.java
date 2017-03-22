package ai_esercitazione_01.model;

import java.util.Collection;

public interface CartService {

	public void addItem(Item item);
	public void removeItem(Item item);
	public void modifyItem(Item item);
	
	public Collection<Item> getItems();
	public Integer getItemCount();
	public Double getTotal();
}
