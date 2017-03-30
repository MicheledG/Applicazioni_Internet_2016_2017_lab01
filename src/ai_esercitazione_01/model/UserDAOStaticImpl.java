package ai_esercitazione_01.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserDAOStaticImpl implements UserDAO {

    // list is working as a database
    private HashMap<String, User> users;

    public UserDAOStaticImpl() {
        this.users = new HashMap<>();

        User u1 = new User("user1", "password1");
        User u2 = new User("user2", "password2");
        User u3 = new User("user3", "password3");

        this.users.put(u1.getUsername(), u1);
        this.users.put(u2.getUsername(), u2);
        this.users.put(u3.getUsername(), u3);
    }

    @Override
    public List<User> getAllUser() {
        return new ArrayList<User>(users.values());
    }

    @Override
    public User getUser(String username) {
        User u = users.get(username);

//        if (u == null) {
//            //System.out.println("NOT FOUND " + username);
//            return null;
//        }
//
//        //System.out.println("FOUND " + u.getUsername());
        
        return u;
    }

    @Override
    public void updateUser(User user) {
        User u = users.get(user.getUsername());

        if (u == null) {
            System.out.println("NOT FOUND " + user.getUsername());
            return;
        }

        users.remove(u.getUsername());

        System.out.println("FOUND " + u.getUsername());

        u.setUsername(user.getUsername());
        u.setPassword(user.getPassword());

        users.put(u.getUsername(), u);
        return;
    }

    @Override
    public void deleteUser(User user) {
        users.remove(user.getUsername());
        //System.out.println("DELETED " + user.getUsername());
    }

}