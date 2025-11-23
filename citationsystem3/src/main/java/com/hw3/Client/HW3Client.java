package com.hw3.Client;

import java.util.HashMap;
import java.util.Scanner;

import com.hw3.Client.UserPackage.User;
import com.hw3.Client.UserPackage.UserList;
import com.hw3.CommonPackage.Citation;
import com.hw3.CommonPackage.Configuration;
import com.hw3.CommonPackage.FieldType;
import com.hw3.CommonPackage.Person;
import com.hw3.CommonPackage.Request;
import com.hw3.CommonPackage.RequestType;

/**
 * Class: HW3Client
 * Purpose: The main client program. It handles user login, menu navigation,
 * creating Request objects, and communicating with the server via the Client proxy.
 */
public class HW3Client {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Current working dir: " + System.getProperty("user.dir"));
        // 1. Read Configuration
        HashMap<String, String> config = Configuration.readConfigurationFile();
        String host = config.getOrDefault("Host", "localhost");
        int port = Integer.parseInt(config.getOrDefault("Port", "3000"));

        // 2. Read User Data and Login
        // Assuming UserList is in your client package or UserPackage
        UserList userList = new UserList();
        // You need to ensure UserList reads the users.csv file. 
        // If your HW2 UserList required a filename in constructor or a read method, call it here.
        userList.readUserFile("users.csv"); 
        
        // User chooses identity
        User currentUser = userList.choose();
        if (currentUser == null) {
            System.out.println("Login failed. Exiting.");
            return;
        }
        System.out.println("Logged in as: " + currentUser.getUserName());
        
        // 3. Connect to Server
        Client client = new Client(host, port);
        Menu menu = new Menu(); // Assuming Menu class from HW2

        boolean running = true;
        while (running) {
            // 4. Display Menu and get choice
            int choice = menu.displayMenu(); // This calls your modified 7-option menu
            
            Request request = new Request();
            boolean validRequest = true;

            // 5. Construct Request based on choice
            switch (choice) {
                case 0: // QUIT
                    request.setRequestType(RequestType.QUIT);
                    running = false;
                    break;

                case 1: // Search by Citation Number
                    request.setRequestType(RequestType.SEARCH);
                    request.setFieldType(FieldType.NUMBER);
                    System.out.print("Enter Citation Number: ");
                    int num = Integer.parseInt(scanner.nextLine());
                    // Create a dummy citation with just the number for the search query
                    Citation dummyNum = new Citation();
                    dummyNum.setNumber(num);
                    request.setCitation(dummyNum);
                    break;

                case 2: // Search by Last Name
                    request.setRequestType(RequestType.SEARCH);
                    request.setFieldType(FieldType.NAME);
                    System.out.print("Enter Last Name: ");
                    String lname = scanner.nextLine();
                    Person pName = new Person();
                    pName.setLastName(lname);
                    Citation dummyName = new Citation();
                    dummyName.setPerson(pName);
                    request.setCitation(dummyName);
                    break;

                case 3: // Search by Type of Offense
                    request.setRequestType(RequestType.SEARCH);
                    request.setFieldType(FieldType.TYPEOFOFFENSE);
                    System.out.print("Enter Type of Offense: ");
                    String offense = scanner.nextLine();
                    Citation dummyOffense = new Citation();
                    dummyOffense.setTypeOfOffense(offense);
                    request.setCitation(dummyOffense);
                    break;

                case 4: // Search by User ID
                    // Uses the ID of the *current logged in user*, not prompted input
                    request.setRequestType(RequestType.SEARCH);
                    request.setFieldType(FieldType.USERID);
                    Citation dummyUser = new Citation();
                    dummyUser.setUserID(currentUser.getId());
                    request.setCitation(dummyUser);
                    System.out.println("Searching for citations associated with your User ID: " + currentUser.getId());
                    break;

                case 5: // Create New Citation
                    request.setRequestType(RequestType.INSERT);
                    Citation newCitation = createNewCitationInput();
                    request.setCitation(newCitation);
                    break;

                case 6: // Delete Citation by Number
                    request.setRequestType(RequestType.DELETE);
                    request.setFieldType(FieldType.NUMBER);
                    System.out.print("Enter Citation Number to delete: ");
                    int delNum = Integer.parseInt(scanner.nextLine());
                    Citation dummyDel = new Citation();
                    dummyDel.setNumber(delNum);
                    request.setCitation(dummyDel);
                    break;

                default:
                    System.out.println("Invalid choice.");
                    validRequest = false;
            }

            // 6. Send Request and Receive Response
            if (validRequest) {
                client.send(request);
                
                // If we are quitting, don't wait for response, just break
                if (!running) break;

                Citation result = client.receive();
                
                // Display result
                if (result != null) {
                    // Check if it's an "empty" object
                    if (result.getNumber() == 0 && result.getTypeOfOffense() == null) {
                        if (choice == 5 || choice == 6) {
                            System.out.println("Operation successful.");
                        } else {
                            System.out.println("No citation found.");
                        }
                    } else {
                        System.out.println("\n--- Server Response ---");
                        System.out.println(result.toString());
                        System.out.println("-----------------------\n");
                    }
                } else {
                    System.out.println("Error: No response from server.");
                }
            }
        }

        // 7. Close connection
        client.close();
        System.out.println("Client closed.");
    }

    /**
     * Helper method to get input for a new Citation
     */
    private static Citation createNewCitationInput() {
        System.out.println("Creating new Citation...");
        try {
            System.out.print("Enter Number: ");
            int number = Integer.parseInt(scanner.nextLine());
            
            System.out.print("Enter Offense Type: ");
            String type = scanner.nextLine();
            
            System.out.print("Enter Description: ");
            String desc = scanner.nextLine();
            
            System.out.print("Enter Date: ");
            String date = scanner.nextLine();
            
            System.out.print("Enter Offender First Name: ");
            String fname = scanner.nextLine();
            
            System.out.print("Enter Offender Last Name: ");
            String lname = scanner.nextLine();
            
            System.out.print("Enter Address: ");
            String address = scanner.nextLine();
            
            System.out.print("Enter Phone: ");
            String phone = scanner.nextLine();
            
            System.out.print("Enter User ID associated: ");
            int uid = Integer.parseInt(scanner.nextLine());

            Person p = new Person(fname, lname, address, phone);
            return new Citation(number, type, desc, date, p, uid);
            
        } catch (Exception e) {
            System.out.println("Invalid input format.");
            return new Citation(); // Return empty on error
        }
    }
}
