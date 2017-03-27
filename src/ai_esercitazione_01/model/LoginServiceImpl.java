package ai_esercitazione_01.model;

public class LoginServiceImpl implements LoginService {

    UserDAO userDAO = new UserDAOStaticImpl();

    @Override
    public User login(String username, String password) {

        User user = userDAO.getUser(username);

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
