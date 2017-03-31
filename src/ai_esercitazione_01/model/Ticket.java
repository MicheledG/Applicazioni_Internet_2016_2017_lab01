package ai_esercitazione_01.model;

public class Ticket {

    public static final String VALIDITY_TIME_UNIT_MIN = "minutes";
    public static final String VALIDITY_TIME_UNIT_DAY = "days";

    private String ID;
    private String type;
    private String description;
    private int validity;
    private String validityTimeUnit;
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

    public String getValidityTimeUnit() {
        return validityTimeUnit;
    }

    public void setValidityTimeUnit(String validityTimeUnit) {
        this.validityTimeUnit = validityTimeUnit;
    }

}
