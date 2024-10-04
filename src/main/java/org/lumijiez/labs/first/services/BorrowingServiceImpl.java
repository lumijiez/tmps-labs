package org.lumijiez.labs.first.services;

import org.lumijiez.labs.first.books.Book;
import org.lumijiez.labs.first.users.User;

public class BorrowingServiceImpl implements BorrowingService {
    private final Notification notificationService;

    public BorrowingServiceImpl(Notification notificationService) {
        this.notificationService = notificationService;
    }

    @Override
    public void borrowBook(User user, Book book) {
        if (user.borrowBook(book)) {
            notificationService.notifyUser(user.getName() + " borrowed " + book.getTitle() + ".");
        } else {
            System.out.println("Could not borrow the book: " + book.getTitle());
        }
    }

    @Override
    public void returnBook(User user, Book book) {
        user.returnBook(book);
        notificationService.notifyUser(user.getName() + " returned " + book.getTitle() + ".");
    }
}
