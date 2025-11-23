package com.hw3.Client.UserPackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class UserList {
    private ArrayList<User> listofUsers = new ArrayList<>();

    public UserList() {
    }

    public UserList(ArrayList<User> listofUsers) {
        this.listofUsers = listofUsers;
    }

    public ArrayList<User> getListofUsers() {
        return listofUsers;
    }

    public void setListofUsers(ArrayList<User> listofUsers) {
        this.listofUsers = listofUsers;
    }

    public void readUserFile(String filename){
        File file = new File(filename);
        Scanner sc = null;

        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("Error: Could not find file at: " + file.getAbsolutePath());
            return; 
        }

        while (sc.hasNextLine()){
            String data_str = sc.nextLine();
            String[] data_split = data_str.split(",");
            String id_str = data_split[0];
            int id = Integer.parseInt(id_str);
            String userType = data_split[1];
            String name = data_split[2];
            String other = data_split[3];
            User user = UserFactory.CreateUser(id, userType, name, other);
            listofUsers.add(user);
        }
    }

    /**
     * Method: choose
     * Purpose: Displays the user list (User ID and User Name) and asks the user to pick one.
     * This simulates the idea that only the people in the users.csv file are valid users
     * The selected user's ID will be used for specific search requests later
     * * @return The User object selected by the operator.
     */
    public User choose() {
        System.out.println("Please login by choosing a user from the list:");
        
        if (listofUsers.isEmpty()) {
            System.out.println("No users found.");
            return null;
        }

        // Display the list of valid users
        for (User u : listofUsers) {
            // Assuming User has getId() and getPerson() methods from HW2
            System.out.println("ID: " + u.getId() + " - Name: " + u.getUserName());
        }

        System.out.print("Enter User ID to login: ");
        // Note: We use a new Scanner here, but be careful not to close System.in
        Scanner scanner = new Scanner(System.in);
        int choiceId = -1;
        
        // Simple input validation
        if (scanner.hasNextInt()) {
            choiceId = scanner.nextInt();
        }
        
        // Find and return the matching User object
        for (User u : listofUsers) {
            if (u.getId() == choiceId) {
                return u;
            }
        }
        
        // Fallback if invalid ID is entered
        System.out.println("Invalid User ID. Defaulting to the first user in the list.");
        return listofUsers.get(0);
    }
        
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("User List\n");
        for (User user: listofUsers){
            sb.append(user.toString()).append("\n");
        }

        return sb.toString();
    }

}
