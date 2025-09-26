/*
 * Name: Althea Chen
 * AndrewID: yuuanch
 */

 package HW1;

import java.util.Arrays;
import java.util.Scanner;

// Class: Menu
// Purpose: Models the user menu choices

public class Menu {
    private String[] menuChoice;
    private static Scanner scanner = new Scanner(System.in);

    public Menu(String[] menuChoice) {
        this.menuChoice = menuChoice;
    }

    public Menu() {
    }

    /*
     * displayMenu(): displays the menu, prompts for the user's choice
     * Parameters: None
     * Return: int of user choice
     */
    public static int displayMenu(){
        System.out.println();
        System.out.println("0. Quit");
        System.out.println("1. Display all the Citation data using CitationList.toString()");
        System.out.println("2. Display all Citations by chosen typeOfOffense");
        System.out.println("3. Search for a Citation by number");
        System.out.println("4. Search for a Citation by Person last name");
        System.out.println("5. Add a new Citation");

        System.out.print("What would you like to do? ");
        String choice = scanner.nextLine();
        int userchoice = Integer.parseInt(choice);
        if (userchoice > 0 && userchoice <= 5){
            return userchoice;
        } else {
            return 0;
        }

    }

    public String[] getMenuChoice() {
        return menuChoice;
    }

    public void setMenuChoice(String[] menuChoice) {
        this.menuChoice = menuChoice;
    }


    @Override
    public String toString() {
        return "Menu [menuChoice=" + Arrays.toString(menuChoice) + "]";
    }

}
