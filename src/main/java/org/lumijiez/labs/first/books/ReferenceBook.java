package org.lumijiez.labs.first.books;

import java.time.LocalDate;

public class ReferenceBook implements Book {
    private String title;
    private String content;

    public ReferenceBook(String title, String content) {
        this.title = title;
        this.content = content;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public boolean canBeBorrowed() {
        return false;
    }

    @Override
    public String getContent() {
        return this.content;
    }

    @Override
    public LocalDate getDueDate() {
        return null;
    }
}

