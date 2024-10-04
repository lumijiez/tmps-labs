package org.lumijiez.labs.first.repositories;

import org.lumijiez.labs.first.books.Book;

public interface LibraryRepository {
    void addBook(Book book);
    void removeBook(Book book);
    void listAvailableBooks();
    Book getBookByTitle(String title);
}

