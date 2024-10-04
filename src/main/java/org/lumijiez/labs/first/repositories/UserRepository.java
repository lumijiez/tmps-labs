package org.lumijiez.labs.first.repositories;

import org.lumijiez.labs.first.users.User;

public interface UserRepository {
    void addUser(User user);
    User getUserByName(String userName);
    void listUsers();
}
