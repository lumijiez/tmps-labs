# Laboratory 0 - Library Management System

## Author: Schipschi Daniil / FAF-223

----

## Objectives:

* Implement the first two letters of SOLID in a simple project.

## Implementation

The Library Management System allows users (students and professors) to borrow and return books while managing the underlying book and user storage efficiently. This implementation employs several SOLID principles.

### Key SOLID Principles

1. **Single Responsibility Principle (SRP)**: Each class has one specific responsibility, ensuring that changes in one area of the application don't affect others.

    - **Book Interface**: Responsible for defining book-related properties and behaviors.
    - **User Repository**: Focused solely on managing user data without coupling to book operations.
    - **Library Repository**: Focused solely on managing book data.
    - **Notification Service**: Handles user notifications independently.

   **Example: Book Interface**
   ```java
   public interface Book { 
       String getTitle();
       String getDescription();
   }
   ```

   Each class implementing this interface can have its unique implementation. For example:

   **Example: GeneralBook Implementation**
   ```java
   public class GeneralBook implements Book {
       private String title;
       private String description;

       public GeneralBook(String title, String description) {
           this.title = title;
           this.description = description;
       }

       @Override
       public String getTitle() {
           return title;
       }

       @Override
       public String getDescription() {
           return description;
       }
   }
   ```

2. **Open/Closed Principle (OCP)**: The system is open for extension but closed for modification. New types of users or books can be added without changing existing code.

    - **Book Types**: You can add new book types (e.g., `EBook`) by extending the `Book` interface without modifying existing classes.

   **Example: EBook Implementation**
   ```java
   public class EBook implements Book {
       private String title;
       private String description;
       private String fileFormat;

       public EBook(String title, String description, String fileFormat) {
           this.title = title;
           this.description = description;
           this.fileFormat = fileFormat;
       }

       @Override
       public String getTitle() {
           return title;
       }

       @Override
       public String getDescription() {
           return description;
       }

       public String getFileFormat() {
           return fileFormat;
       }
   }
   ```

    - **User Types**: New user types (e.g., `AdminUser`) can be implemented by creating new classes that implement the `User` interface.

   **Example: AdminUser Implementation**
   ```java
   public class AdminUser implements User {
   private String name;

       public AdminUser(String name) {
           this.name = name;
       }

       @Override
       public String getName() {
           return name;
       }

       // Admin-specific functionalities can be added here
   }
   ```

### Code Snippets

**User Repository Interface**
```java
public interface UserRepository {
    void addUser(User user);
    User getUserByName(String userName);
    void listUsers();
}
```

**InMemoryUserRepository Implementation**
```java
public class InMemoryUserRepository implements UserRepository {
private final List<User> users = new ArrayList<>();

    @Override
    public void addUser(User user) {
        users.add(user);
    }

    @Override
    public User getUserByName(String userName) {
        return users.stream()
                .filter(user -> user.getName().equalsIgnoreCase(userName))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void listUsers() {
        users.forEach(user -> System.out.println(user.getName()));
    }
}
```

**Notification Interface**
```java
public interface Notification {
    void notifyUser(String message);
}
```

**EmailNotificationService Implementation**
```java
public class EmailNotificationService implements Notification {
    @Override
    public void notifyUser(String message) {
        System.out.println("Email sent: " + message);
    }
}
```

**Borrowing Service Interface**
```java
public interface BorrowingService {
    void borrowBook(User user, Book book);
    void returnBook(User user, Book book);
}
```

**Borrowing Service Implementation**
```java 
public class BorrowingServiceImpl implements BorrowingService {
    private final Notification notificationService;

    public BorrowingServiceImpl(Notification notificationService) {
        this.notificationService = notificationService;
    }

    @Override
    public void borrowBook(User user, Book book) {
        notificationService.notifyUser(user.getName() + " borrowed " + book.getTitle() + ".");
    }

    @Override
    public void returnBook(User user, Book book) {
        notificationService.notifyUser(user.getName() + " returned " + book.getTitle() + ".");
    }
}
```

### Conclusion

This Library Management System effectively demonstrates the application of SOLID principles:

- **Single Responsibility Principle (S)** is maintained by clearly separating the responsibilities of managing users, books, and notifications, which allows for focused changes in each area.

- **Open/Closed Principle (O)** is illustrated through the extensible design of the `Book` and `User` classes. New types can be added easily without modifying existing functionality, thereby promoting a flexible and maintainable system.

This design leads to a more modular, maintainable codebase, making future enhancements straightforward.

---
