package org.lumijiez.labs.first.users;

import org.lumijiez.labs.first.books.Book;

import java.util.List;

public interface User {
    boolean borrowBook(Book book);
    void returnBook(Book book);
    String getName();
    List<Book> getBorrowedBooks();
    int getBorrowingLimit();
}

