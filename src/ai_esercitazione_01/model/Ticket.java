package ai_esercitazione_01.model;

public class Ticket {
	
	private String ID;
	private String type;
	private String descrption;
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
	public void setID(String ID) {
		this.ID = ID;
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

	public String getDescrption() {
		return descrption;
	}

	public void setDescrption(String descrption) {
		this.descrption = descrption;
	}

	public int getValidity() {
		return validity;
	}

	public void setValidity(int validity) {
		this.validity = validity;
	}
	
}
