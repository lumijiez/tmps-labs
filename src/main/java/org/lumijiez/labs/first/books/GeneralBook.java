package org.lumijiez.labs.first.books;

import java.time.LocalDate;

public class GeneralBook implements Book {
    private String title;
    private String content;
    private boolean borrowed;
    private LocalDate dueDate;

    public GeneralBook(String title, String content) {
        this.title = title;
        this.content = content;
        this.borrowed = false;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public boolean canBeBorrowed() {
        return !borrowed;
    }

    @Override
    public String getContent() {
        return this.content;
    }

    @Override
    public LocalDate getDueDate() {
        return dueDate;
    }

    public void borrow(LocalDate dueDate) {
        this.borrowed = true;
        this.dueDate = dueDate;
    }

    public void returnBook() {
        this.borrowed = false;
        this.dueDate = null;
    }
}
