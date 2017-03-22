package ai_esercitazione_01.model;

public class Login implements LoginService {

	@Override
	public User login(String username, String password) {
		UserDAO userDAO = UserDAO.getInstance();
		User user = userDAO.readUser(username);
		if (user != null && user.getPassword().equals(password)) {
			return user;
		} else {
			return null;
		}
	}

	@Override
	public void logout(String username) {
		return;
	}

}
