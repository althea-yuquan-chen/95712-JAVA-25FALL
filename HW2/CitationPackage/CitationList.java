/*
 * Name: Althea Chen
 * AndrewID: yuuanch
 */



import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
            String userID_str = contents[8];
            int userID = Integer.parseInt(userID_str);

            Person person = new Person(fname, lname, address, phonenumber);
            Citation citation = new Citation(number, typeOfOffense, description, date, person, userID);
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
                    citation.getPerson().getFirstName(), citation.getPerson().getLastName(), citation.getPerson().getAddress(), citation.getPerson().getPhoneNumber(),
                    String.valueOf(citation.getUserID())};
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
     * Return: String
     */
    public String displayCitationType(String typeOfOffense){
        String output = null;
        ArrayList<String> output_list = new ArrayList<>();
        for (Citation citation: listOfCitaions){
            if (citation.getTypeOfOffense().equalsIgnoreCase(typeOfOffense)){
                output_list.add(citation.toString());
            }
        }

        if (output_list.isEmpty()){
            output = "None found.";
        } else {
            output = String.join("\n\n", output_list);
        }

        return output;
    }

    /*
     * displayCitation(int number): display the Citations with the given number
     * Parameters: int number
     * Return: String
     */
    public String displayCitation(int number){
        String output = null;
        ArrayList<String> output_list = new ArrayList<>();
        for (Citation citation: listOfCitaions){
            if (citation.getNumber() == number){
                output_list.add(citation.toString());
            }
        }

        if (output_list.isEmpty()){
            output = "None found.";
        } else {
            output = String.join("\n\n", output_list);
        }

        return output;
    }

    /*
     * displayCitation(String lastName): display the Citations with the given last name
     * Parameters: String lastName
     * Return: String
     */
    public String displayCitation(String lastName){
        String output = null;
        ArrayList<String> output_list = new ArrayList<>();
        for (Citation citation: listOfCitaions){
            if (citation.getPerson().getLastName().equalsIgnoreCase(lastName)){
                output_list.add(citation.toString());
            }
        }

        if (output_list.isEmpty()){
            output = "None found.";
        } else {
            output = String.join("\n", output_list);
        }

        return output;
    }

    /*
     * delete(int number): delete the Citation from listOfCitations
     * Paras: int 
     * Return: String
     */
    public String delete(int number){
        Boolean result = listOfCitaions.removeIf(c -> c.getNumber() == number);
        if (result){
            return "Deleted";
        } else {
            return "None found.";
        }
    }

    /*
     * sortByNumber: sort the list by citation number
     */
    public void sortByNumber(){
        Collections.sort(listOfCitaions);
    }

    /*
     * sortByName(): sort the list by last name
     */
    public void sortByName(){
        Collections.sort(listOfCitaions, new Comparator<Citation>() {
        @Override
        public int compare(Citation c1, Citation c2) {
           
            String lastName1 = c1.getPerson().getLastName();
            String lastName2 = c2.getPerson().getLastName();

            return lastName1.compareTo(lastName2);
        }
    });
    }

    /*
     * sortByType(): sort the list by type of offense
     */
    public void sortByType(){
        Collections.sort(listOfCitaions, new TypeSorter());
    }

    /*
     * findByUser(int userID): return one String containing any Citations associated with this userID
     * paras: int
     * Return: String
     */
    public String findbyUser(int userID){
        String output = null;
        ArrayList<String> output_list = new ArrayList<>();
        for (Citation citation: listOfCitaions){
            if (citation.getUserID() == userID){
                output_list.add(citation.toString());
            }
        }

        if (output_list.isEmpty()){
            output = "None found.";
        } else {
            output = String.join("\n\n", output_list);
        }

        return output;
    }

    public void add(Citation c){
        listOfCitaions.add(c);
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

    public ArrayList<Citation> getListOfCitations() {
        return listOfCitaions;
    }

    public void setListOfCitaions(ArrayList<Citation> listOfCitaions) {
        this.listOfCitaions = listOfCitaions;
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
