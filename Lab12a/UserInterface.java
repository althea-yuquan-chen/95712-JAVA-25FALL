import java.util.Scanner;

public class UserInterface {
    public static Scanner input = new Scanner(System.in);
    private Catalog catalog = new Catalog();
    private ListOfPatrons listOfPatrons = new ListOfPatrons();
    private View view;

    private int getMenuChoice() {
        int menuChoice;
        String[] choices = {"Quit", "Add Patron", "Check Out Book", "Patron Summary", "Book Summary"};
        for (int i = 0; i < choices.length; i++) {
            System.out.println(i + "). " + choices[i]);
        }
        System.out.print("Please enter your choice: ");
        menuChoice = Integer.parseInt(input.nextLine());
        if (menuChoice < 1 || menuChoice > choices.length) {
            menuChoice = 0;
        }
        return menuChoice;
    }

    public void start() {
        listOfPatrons.addPatron(PatronFactory.createPatron("Patron", "James", 0));
        listOfPatrons.addPatron(PatronFactory.createPatron("Patron", "Chen", 1));
        listOfPatrons.addPatron(PatronFactory.createPatron("Patron", "Park", 2));
        listOfPatrons.addPatron(PatronFactory.createPatron("Child", "Jones", 8));
        listOfPatrons.addPatron(PatronFactory.createPatron("Senior", "Barrett", 97));

        int choice = getMenuChoice();
        while (choice != 0) {
            switch (choice) {
                case 1:
                    System.out.print("Enter Patron name: ");
                    String patronName = input.nextLine();
                    System.out.print("Enter Patron ID: ");
                    int id = Integer.parseInt(input.nextLine());
                    System.out.println("Enter Patron Type: ");
                    String type = input.nextLine();
                    listOfPatrons.addPatron(PatronFactory.createPatron(type, patronName, id));
                    break;

                case 2:
                    // TBD
                    System.out.print("Enter the user ID: ");
                    String userIDstr = input.nextLine();
                    int userID = Integer.parseInt(userIDstr);
                    Patron patron = listOfPatrons.searchPatronByID(userID);
                    if (patron == null) {
                        System.out.println("Patron <" + userID + "#> not found.");
                    }

                    System.out.print("Enter the book ID: ");
                    String bookIDstr = input.nextLine();
                    int bookID = Integer.parseInt(bookIDstr);
                    Book book = catalog.searchByID(bookID);
                    if (book == null) {
                        System.out.println("Book <" + bookID + "#> not found.");
                    }

                    patron.checkOutBook(book);
                    System.out.println("Patron <" + userID + "#> checked out book <" + bookID + "#>.");
                    
                    break;

                case 3:
                    System.out.println(listOfPatrons.toString());
                    break;

                case 4:
                    System.out.println(catalog.toString());
                    break;
            }
            choice = getMenuChoice();
        }
    }
}
