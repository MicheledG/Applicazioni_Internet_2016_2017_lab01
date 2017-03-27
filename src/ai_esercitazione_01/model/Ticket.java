package ai_esercitazione_01.model;

public class Ticket {

    private String ID;
    private String type;
    private String description;
    private int validity; //in minute
    private Double price; //in EUR

    private static int counter = 1;

    public Ticket() {
        this.ID = String.valueOf(counter++);
    }

    public Ticket(String type, Double price) {
        this.ID = String.valueOf(counter++);
        this.type = type;
        this.price = price;
    }

    public String getID() {
        return ID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getValidity() {
        return validity;
    }

    public void setValidity(int validity) {
        this.validity = validity;
    }

}
