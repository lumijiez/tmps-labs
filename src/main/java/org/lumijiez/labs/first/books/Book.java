package org.lumijiez.labs.first.books;

import java.time.LocalDate;

public interface Book {
    String getTitle();
    boolean canBeBorrowed();
    String getContent();
    LocalDate getDueDate();
}

