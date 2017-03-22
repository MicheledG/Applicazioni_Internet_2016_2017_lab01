package ai_esercitazione_01.model;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserDAO {
	
	private static UserDAO instance = null;
	
	private static Map<String,User> userMap = new ConcurrentHashMap<>();
	
	protected UserDAO() {
	}
	
	public static UserDAO getInstance() {
		if (instance == null) {
			instance = new UserDAO();
		}
		return instance;
	}
	
	public void createUser(User user) {
		userMap.put(user.getUsername(), user);
		return;
	}
	
	public User readUser(String username) {
		return userMap.get(username);
	}
	
	public void updateUser(User user) {
		userMap.replace(user.getUsername(), user);
		return;
	}
	
	public void deleteUser(String username) {
		userMap.remove(username);
	}
}
