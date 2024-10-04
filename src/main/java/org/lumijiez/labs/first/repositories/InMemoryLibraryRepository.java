package org.lumijiez.labs.first.repositories;

import org.lumijiez.labs.first.books.Book;

import java.util.ArrayList;
import java.util.List;

public class InMemoryLibraryRepository implements LibraryRepository {
    private final List<Book> books = new ArrayList<>();

    @Override
    public void addBook(Book book) {
        books.add(book);
    }

    @Override
    public void removeBook(Book book) {
        books.remove(book);
    }

    @Override
    public void listAvailableBooks() {
        System.out.println("Available books:");
        for (Book book : books) {
            if (book.canBeBorrowed()) {
                System.out.println(book.getTitle());
            } else {
                System.out.println(book.getTitle() + " [NOT FOR BORROW]");
            }
        }
    }

    @Override
    public Book getBookByTitle(String title) {
        for (Book book : books) {
            if (book.getTitle().equals(title)) {
                return book;
            }
        }
        return null;
    }
}

