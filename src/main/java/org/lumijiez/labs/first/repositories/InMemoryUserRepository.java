package org.lumijiez.labs.first.repositories;

import org.lumijiez.labs.first.users.User;

import java.util.ArrayList;
import java.util.List;

public class InMemoryUserRepository implements UserRepository {
    private final List<User> users = new ArrayList<>();

    @Override
    public void addUser(User user) {
        users.add(user);
    }

    @Override
    public User getUserByName(String userName) {
        return users.stream()
                .filter(user -> user.getName().equalsIgnoreCase(userName))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void listUsers() {
        if (users.isEmpty()) {
            System.out.println("No users in the system.");
        } else {
            users.forEach(user -> System.out.println(user.getName()));
        }
    }
}

