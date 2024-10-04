package org.lumijiez.labs.first.services;

import org.lumijiez.labs.first.books.Book;
import org.lumijiez.labs.first.users.User;

import java.time.LocalDate;

public class EmailNotificationService implements Notification {
    @Override
    public void notifyUser(String message) {
        System.out.println("Sending email: " + message);
    }

    @Override
    public void remindDueDate(Book book, User user) {
        LocalDate dueDate = book.getDueDate();
        if (dueDate != null) {
            System.out.println("Reminder: " + user.getName() + ", your borrowed book " + book.getTitle() + " is due on " + dueDate);
        }
    }
}
