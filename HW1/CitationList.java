/*
 * Name: Althea Chen
 * AndrewID: yuuanch
 */

package HW1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

// Class: CitationList
// Purpose: This class encapsulates the list of Citations

public class CitationList {
    private ArrayList<Citation> listOfCitaions = new ArrayList<>();
    private String title = "Chief";
    private String authority = "Barrett";
    private static Scanner scanner = new Scanner(System.in), fileScanner;

    public CitationList(String title, String authority) {
        this.title = title;
        this.authority = authority;
    }

    public CitationList() {
    }

    /*
     * readCitationFile(): read a Citation CSV file
     * Parameters: String of file name
     * Return: None
     */
    public void readCitationFile(String filename){
        File file = new File(filename);
        try {
            fileScanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            String[] contents = line.split(",");
            
            String number_str = contents[0];
            int number = Integer.parseInt(number_str);
            String typeOfOffense = contents[1];
            String description = contents[2];
            String date = contents[3];
            String fname = contents[4];
            String lname = contents[5];
            String address = contents[6];
            String phonenumber = contents[7];

            Person person = new Person(fname, lname, address, phonenumber);
            Citation citation = new Citation(number, typeOfOffense, description, date, person);
            listOfCitaions.add(citation);
        }
    }

    /*
     * writeCitationFile(): write a Citation CSV file
     * Parameters: String of file name
     * Return: None
     */
    public void writeCitationFile(String filename){
        try {
            FileWriter fileWriter = new FileWriter(filename);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            
            for (Citation citation : listOfCitaions) {
                // build the csv string
                String[] datalist = {String.valueOf(citation.getNumber()), citation.getTypeOfOffense(), citation.getDescription(), citation.getDate(), 
                    citation.getPerson().getFirstName(), citation.getPerson().getLastName(), citation.getPerson().getAddress(), citation.getPerson().getPhoneNumber()};
                String tocsv = String.join(",", datalist);

                //write the csv string to the file
                printWriter.println(tocsv);
                }            
        } catch (IOException e) {
            System.err.println("Error writing to file " + filename + ": " + e.getMessage());
        }
    }

    /*
     * displayCitationType(): display any Citations with the given type of offense
     * Parameters: String typeOfOffense
     * Return: none
     */
    public void displayCitationType(String typeOfOffense){
        boolean found = false;
        for (Citation citation: listOfCitaions){
            if (citation.getTypeOfOffense().equalsIgnoreCase(typeOfOffense)){
                System.out.println();
                System.out.println(citation.toString());
                found = true;
            }
        }

        if (!found){
            System.out.println("No citation exists for this type of offense.");
        }
    }

    /*
     * displayCitation(int number): display the Citations with the given number
     * Parameters: int number
     * Return: none
     */
    public void displayCitation(int number){
        boolean found = false;
        for (Citation citation: listOfCitaions){
            if (citation.getNumber() == number){
                System.out.println();
                System.out.println(citation.toString());
                found = true;
            }
        }

        if (!found){
            System.out.println("No citation exists for this number.");
        }
    }

    /*
     * displayCitation(String lastName): display the Citations with the given last name
     * Parameters: String lastName
     * Return: none
     */
    public void displayCitation(String lastName){
        boolean found = false;
        for (Citation citation: listOfCitaions){
            Person person = citation.getPerson();
            if (person.getLastName().equalsIgnoreCase(lastName)){
                System.out.println();
                System.out.println(citation.toString());
                found = true;
            }
        }

        if (!found){
            System.out.println("No citation exists for this person.");
        }
    }

    /*
     * newCitation(): prompt the user for the information for one new Citation, add it to listOfCitations
     * Parameter: None
     * Return: None
     */
    public void newCitation(){
        Citation citation = new Citation();
        System.out.println("----Create a new Citation----");
        System.out.print("Enter the number: ");
        String number_str = scanner.nextLine();
        int number = Integer.parseInt(number_str);
        citation.setNumber(number);

        System.out.print("Enter the type of offense: ");
        String typeOfOffense = scanner.nextLine();
        citation.setTypeOfOffense(typeOfOffense);

        System.out.print("Enter the date, use dd-MMM-yyyy: ");
        String date = scanner.nextLine();
        citation.setDate(date);

        System.out.print("Enter the description: ");
        String description = scanner.nextLine();
        citation.setDescription(description);

        System.out.print("Enter the person's last name: ");
        String lastname = scanner.nextLine();
        System.out.print("Enter the person's first name: ");
        String firstname = scanner.nextLine();
        System.out.print("Enter the person's address: ");
        String address = scanner.nextLine();
        System.out.print("Enter the person's phone number, use xxx-xxx-xxxx: ");
        String phonenumber = scanner.nextLine();

        Person person = new Person();
        person.setAddress(address);
        person.setFirstName(firstname);
        person.setLastName(lastname);
        person.setPhoneNumber(phonenumber);

        citation.setPerson(person);

        listOfCitaions.add(citation);
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Citation List\n\n");
        sb.append(title).append(" ").append(authority).append("\n\n");

        if (listOfCitaions.isEmpty()){
            sb.append("No citations currently exists.\n");
        } else {
            for (Citation citation: listOfCitaions){
                sb.append(citation.toString()).append("\n\n");
            }
        }
        return sb.toString();
    }

    

}
