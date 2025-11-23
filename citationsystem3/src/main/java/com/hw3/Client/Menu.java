/*
 * Name: Althea Chen
 * AndrewID: yuquanch
 */

package com.hw3.Client;

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
        System.out.println("Citation System Menu");
        System.out.println("0 = Quit");
        System.out.println("1 = Search by Citation Number");
        System.out.println("2 = Search by Last Name");
        System.out.println("3 = Search by Type of Offense");
        System.out.println("4 = Search by User ID");
        System.out.println("5 = Create New Citation");
        System.out.println("6 = Delete Citation by Number");

        System.out.print("What would you like to do? ");
        String choice = scanner.nextLine();
        int userchoice = Integer.parseInt(choice);
        if (userchoice > 0 && userchoice <= 10){
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
