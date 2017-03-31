package ai_esercitazione_01.model;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CartServiceImpl implements CartService {

    Map<String, Item> itemMap = new ConcurrentHashMap<>();

    @Override
    public void addItem(Item item) {
        itemMap.put(item.getID(), item);
        return;
    }

    @Override
    public void removeItem(String ticketId) {
        itemMap.remove(ticketId);
        return;
    }

    @Override
    public void modifyItem(String ticketId, int quantity) {
        itemMap.get(ticketId).setQuantity(quantity);
    }

    @Override
    public Collection<Item> getItems() {
        return itemMap.values();
    }

    @Override
    public Integer getItemCount() {
        return itemMap.size();
    }

    @Override
    public Double getTotal() {
        return itemMap.values().stream()
                .map(i -> i.getTicket().getPrice() * i.getQuantity())
                .reduce(0.0, (a, b) -> a + b);
    }

    @Override
    public boolean containsItem(String ticketId) {
        return itemMap.containsKey(ticketId);
    }

    @Override
    public Item getItem(String ticketId) {
        return itemMap.get(ticketId);
    }

}
