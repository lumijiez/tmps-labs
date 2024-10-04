package org.lumijiez.labs.first.users;

import org.lumijiez.labs.first.books.Book;
import org.lumijiez.labs.first.books.GeneralBook;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StudentUser implements User {
    private final String name;
    private final List<Book> borrowedBooks = new ArrayList<>();
    private static final int BORROW_LIMIT = 3;

    public StudentUser(String name) {
        this.name = name;
    }

    @Override
    public boolean borrowBook(Book book) {
        if (borrowedBooks.size() >= BORROW_LIMIT) {
            System.out.println(name + " has reached the borrowing limit.");
            return false;
        }
        if (book.canBeBorrowed()) {
            LocalDate dueDate = LocalDate.now().plusWeeks(2);
            ((GeneralBook) book).borrow(dueDate);
            borrowedBooks.add(book);
            System.out.println(name + " borrowed " + book.getTitle() + ". Due date: " + dueDate);
            return true;
        } else {
            System.out.println(book.getTitle() + " cannot be borrowed.");
        }
        return false;
    }

    @Override
    public void returnBook(Book book) {
        borrowedBooks.remove(book);
        ((GeneralBook) book).returnBook();
        System.out.println(name + " returned " + book.getTitle());
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    @Override
    public int getBorrowingLimit() {
        return BORROW_LIMIT;
    }
}
