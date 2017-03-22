package ai_esercitazione_01.model;

public interface LoginService {
	public User login(String username, String password);
	public void logout(String username);
}
