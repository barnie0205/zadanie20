package pl.javastart.zadanie20;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {

    private final List<User> users;

    public UserRepository() {
        users = new ArrayList<>();
        users.add(new User("Jan", "Kowalski", 25));
        users.add(new User("Anna", "Nowak", 30));
        users.add(new User("Piotr", "Wiśniewski", 20));
    }

    public List<User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        users.add(user);
    }
}