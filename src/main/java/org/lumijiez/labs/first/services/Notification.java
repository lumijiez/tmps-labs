package org.lumijiez.labs.first.services;

import org.lumijiez.labs.first.books.Book;
import org.lumijiez.labs.first.users.User;

public interface Notification {
    void notifyUser(String message);
    void remindDueDate(Book book, User user);
}


