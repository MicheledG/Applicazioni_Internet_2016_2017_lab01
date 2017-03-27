package ai_esercitazione_01.model;

import java.util.List;

public interface UserDAO {
    public List<User> getAllUser();

    public User getUser(String ID);

    public void updateUser(User student);

    public void deleteUser(User student);
}