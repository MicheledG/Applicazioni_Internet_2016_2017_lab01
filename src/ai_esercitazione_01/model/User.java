package ai_esercitazione_01.model;

public class User {
	private String ID;
	private String username;
	private String password;
	
	private static int counter = 1;
	
	public User() {
	}
	
	public User(String username, String password) {
		this.ID = String.valueOf(counter++);
		this.username = username;
		this.password = password;
	}
	
	public String getID() {
		return ID;
	}
	public void setID(String ID) {
		this.ID = ID;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
