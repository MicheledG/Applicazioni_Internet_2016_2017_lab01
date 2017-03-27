package ai_esercitazione_01.model;

public class LoginServiceImpl implements LoginService {

    UserDAOStaticImpl userDAOStatic = new UserDAOStaticImpl();

    @Override
    public User login(String username, String password) {

        User user = userDAOStatic.getUser(username);

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
