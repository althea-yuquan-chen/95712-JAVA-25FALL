/*
 * Name: Althea Chen
 * AndrewID: yuuanch
 */

package HW1;

import java.util.Arrays;
import java.util.Scanner;

// Class: hw1Main
// Purpose: The main program

public class hw1Main {
    public static Scanner scanner = new Scanner(System.in);
    public CitationList citationList;

    public static void main(String[] args){
        CitationList citationList = new CitationList();
        citationList.readCitationFile("citations.csv");
        int userchoice;

        do{
            userchoice = Menu.displayMenu();

            switch (userchoice){
                // Display all the Citation data using CitationList.toString()
                case 1: 
                    System.out.println(citationList.toString());
                    break;

                // Display all Citations by chosen typeOfOffense
                case 2:
                    System.out.print("Enter the type of offense: ");
                    String typeOfOffense = scanner.nextLine();
                    citationList.displayCitationType(typeOfOffense);
                    break;

                // Search for a Citation by number
                case 3:
                    System.out.print("Enter the number: ");
                    String number_str = scanner.nextLine();
                    int number = Integer.parseInt(number_str);
                    citationList.displayCitation(number);
                    break;

                // Search for a Citation by Person last name
                case 4:
                    System.out.print("Enter the person's last name: ");
                    String lastname = scanner.nextLine();
                    citationList.displayCitation(lastname);
                    break;

                // Add a new Citation
                case 5:
                    citationList.newCitation();
                    break;

                // Quit, or error
                case 0:
                    break;
            }
        } while (userchoice != 0);
        
    }

}
