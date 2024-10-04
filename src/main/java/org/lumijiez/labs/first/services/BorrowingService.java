package org.lumijiez.labs.first.services;

import org.lumijiez.labs.first.books.Book;
import org.lumijiez.labs.first.users.User;

public interface BorrowingService {
    void borrowBook(User user, Book book);
    void returnBook(User user, Book book);
}

