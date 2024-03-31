import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class BookNotFoundException extends Exception {
    public BookNotFoundException(String message) {
        super(message);
    }
}

public class BookInventorySystem {
    private static HashMap<String, Book> inventory = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            displayMenu();
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character
            handleChoice(choice);
        } while (choice != 6);
    }

    private static void displayMenu() {
        System.out.println("\nBook Inventory System");
        System.out.println("1. Add Book");
        System.out.println("2. Remove Book");
        System.out.println("3. Update Book");
        System.out.println("4. Search Book by Title");
        System.out.println("5. Search Book by Author");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void handleChoice(int choice) {
        try {
            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    removeBook();
                    break;
                case 3:
                    updateBook();
                    break;
                case 4:
                    searchBookByTitle();
                    break;
                case 5:
                    searchBookByAuthor();
                    break;
                case 6:
                    System.out.println("Exiting Book Inventory System...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } catch (BookNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void addBook() {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter book author: ");
        String author = scanner.nextLine();
        System.out.print("Enter book price: ");
        double price = scanner.nextDouble();
        System.out.print("Enter book quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        Book book = new Book(title, author, price, quantity);
        inventory.put(title, book);
        System.out.println("Book added successfully.");
    }

    private static void removeBook() throws BookNotFoundException {
        System.out.print("Enter book title to remove: ");
        String title = scanner.nextLine();
        Book book = inventory.get(title);
        if (book == null) {
            throw new BookNotFoundException("Book with title '" + title + "' not found.");
        } else {
            inventory.remove(title);
            System.out.println("Book removed successfully.");
        }
    }

    private static void updateBook() throws BookNotFoundException {
        System.out.print("Enter book title to update: ");
        String title = scanner.nextLine();
        Book book = inventory.get(title);
        if (book == null) {
            throw new BookNotFoundException("Book with title '" + title + "' not found.");
        } else {
            System.out.print("Enter new book title: ");
            String newTitle = scanner.nextLine();
            System.out.print("Enter new book author: ");
            String newAuthor = scanner.nextLine();
            System.out.print("Enter new book price: ");
            double newPrice = scanner.nextDouble();
            System.out.print("Enter new book quantity: ");
            int newQuantity = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            book.setTitle(newTitle);
            book.setAuthor(newAuthor);
            book.setPrice(newPrice);
            book.setQuantity(newQuantity);
            inventory.remove(title);
            inventory.put(newTitle, book);
            System.out.println("Book updated successfully.");
        }
    }

    private static void searchBookByTitle() {
        System.out.print("Enter book title to search: ");
        String title = scanner.nextLine();
        ArrayList<Book> searchResults = new ArrayList<>();
        for (Book book : inventory.values()) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                searchResults.add(book);
            }
        }
        if (searchResults.isEmpty()) {
            System.out.println("No books found with the title '" + title + "'.");
        } else {
            System.out.println("Search results for title '" + title + "':");
            for (Book book : searchResults) {
                System.out.println(book.getTitle() + " by " + book.getAuthor());
            }
        }
    }

    private static void searchBookByAuthor() {
        System.out.print("Enter book author to search: ");
        String author = scanner.nextLine();
        ArrayList<Book> searchResults = new ArrayList<>();
        for (Book book : inventory.values()) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                searchResults.add(book);
            }
        }
        if (searchResults.isEmpty()) {
            System.out.println("No books found by the author '" + author + "'.");
        } else {
            System.out.println("Search results for author '" + author + "':");
            for (Book book : searchResults) {
                System.out.println(book.getTitle() + " by " + book.getAuthor());
            }
        }
    }
}