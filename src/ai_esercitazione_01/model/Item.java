package ai_esercitazione_01.model;

public class Item {
    private String ID;
    private Ticket ticket;
    private Integer quantity;

    public Item() {
    }

    public Item(Ticket ticket, Integer quantity) {
        this.ID = ticket.getID();
        this.ticket = ticket;
        this.quantity = quantity;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}
