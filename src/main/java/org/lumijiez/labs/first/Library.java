package org.lumijiez.labs.first;

import org.lumijiez.labs.first.books.Book;
import org.lumijiez.labs.first.books.GeneralBook;
import org.lumijiez.labs.first.books.ReferenceBook;
import org.lumijiez.labs.first.repositories.InMemoryLibraryRepository;
import org.lumijiez.labs.first.repositories.InMemoryUserRepository;
import org.lumijiez.labs.first.repositories.LibraryRepository;
import org.lumijiez.labs.first.repositories.UserRepository;
import org.lumijiez.labs.first.services.BorrowingService;
import org.lumijiez.labs.first.services.BorrowingServiceImpl;
import org.lumijiez.labs.first.services.EmailNotificationService;
import org.lumijiez.labs.first.services.Notification;
import org.lumijiez.labs.first.users.ProfessorUser;
import org.lumijiez.labs.first.users.StudentUser;
import org.lumijiez.labs.first.users.User;

import java.util.Scanner;

public class Library {

    static String[][] booksData = {
            {"Clean Code", "A handbook of agile software craftsmanship."},
            {"Java Concurrency in Practice", "A comprehensive guide to writing concurrent applications in Java."},
            {"Effective Java (2nd Edition)", "The definitive guide to programming in Java."},
            {"Head First Java", "An engaging introduction to the Java programming language."},
            {"Java: The Complete Reference", "A complete guide to the Java programming language and its features."},
            {"Design Patterns", "Elements of reusable object-oriented software."},
            {"Refactoring", "Improving the design of existing code."},
            {"Introduction to Java Programming", "Comprehensive introduction to Java programming."},
            {"Java Network Programming", "A guide to network programming in Java."},
            {"Java Performance: The Definitive Guide", "Getting the most out of Java."}
    };

    static String[][] referenceBooksData = {
            {"Java Language Specification", "The official reference for the Java language."},
            {"Java API Documentation", "Comprehensive documentation for the Java APIs."},
            {"The Art of Java", "A detailed reference guide to Java programming."},
            {"Java in a Nutshell", "A quick reference guide for Java developers."},
            {"Java: A Beginner's Guide", "An introductory guide for beginners."}
    };

    private static final LibraryRepository libraryRepository = new InMemoryLibraryRepository();
    private static final UserRepository userRepository = new InMemoryUserRepository();
    private static final Notification notificationService = new EmailNotificationService();
    private static final BorrowingService borrowingService = new BorrowingServiceImpl(notificationService);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        for (String[] bookData : booksData) {
            GeneralBook book = new GeneralBook(bookData[0], bookData[1]);
            libraryRepository.addBook(book);
        }

        for (String[] bookData : referenceBooksData) {
            ReferenceBook book = new ReferenceBook(bookData[0], bookData[1]);
            libraryRepository.addBook(book);
        }

        System.out.println("Welcome to the Library System!");
        boolean running = true;

        while (running) {
            System.out.println("\nPlease choose an option:");
            System.out.println("1. Add a Student User");
            System.out.println("2. Add a Teacher User");
            System.out.println("3. Borrow a Book");
            System.out.println("4. Return a Book");
            System.out.println("5. List Available Books");
            System.out.println("6. List Users");
            System.out.println("7. Exit");

            int option = Integer.parseInt(scanner.nextLine());

            switch (option) {
                case 1:
                    addUser(scanner, "student");
                    break;
                case 2:
                    addUser(scanner, "teacher");
                    break;
                case 3:
                    borrowBook(scanner);
                    break;
                case 4:
                    returnBook(scanner);
                    break;
                case 5:
                    libraryRepository.listAvailableBooks();
                    break;
                case 6:
                    userRepository.listUsers();
                    break;
                case 7:
                    running = false;
                    System.out.println("Exiting the system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }

    private static void addUser(Scanner scanner, String userType) {
        System.out.print("Enter user name: ");
        String userName = scanner.nextLine();
        User user;

        if (userType.equals("student")) {
            user = new StudentUser(userName);
        } else {
            user = new ProfessorUser(userName);
        }

        userRepository.addUser(user);
        System.out.println(userType.substring(0, 1).toUpperCase() + userType.substring(1) + " User '" + userName + "' added.");
    }

    private static void borrowBook(Scanner scanner) {
        System.out.print("Enter user name: ");
        String userName = scanner.nextLine();

        System.out.print("Enter book title to borrow: ");
        String bookTitle = scanner.nextLine();

        User user = userRepository.getUserByName(userName);
        Book book = libraryRepository.getBookByTitle(bookTitle);

        if (user != null && book != null) {
            borrowingService.borrowBook(user, book);
        } else {
            System.out.println("User or book not found.");
        }
    }

    private static void returnBook(Scanner scanner) {
        System.out.print("Enter user name: ");
        String userName = scanner.nextLine();

        System.out.print("Enter book title to return: ");
        String bookTitle = scanner.nextLine();

        User user = userRepository.getUserByName(userName);
        Book book = libraryRepository.getBookByTitle(bookTitle);

        if (user != null && book != null) {
            borrowingService.returnBook(user, book);
        } else {
            System.out.println("User or book not found.");
        }
    }
}
