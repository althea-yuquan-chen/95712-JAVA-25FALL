import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

import UserPackage.UserList;

public class hw2Main {
    private static HashMap<String, String> configData = new HashMap<>();
    public static Scanner scanner = new Scanner(System.in);
    private static UserList userList;
    private static CitationList listofCitations;

    /*
     * newCitation(): prompt the user for the information for one new Citation, add it to listOfCitations
     * Parameter: None
     * Return: None
     */
    public static void newCitation(){
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

        System.out.print("Enter user ID: ");
        String userID_str = scanner.nextLine();
        int userID = Integer.parseInt(userID_str);
        citation.setUserID(userID);

        Person person = new Person();
        person.setAddress(address);
        person.setFirstName(firstname);
        person.setLastName(lastname);
        person.setPhoneNumber(phonenumber);

        citation.setPerson(person);

        listofCitations.add(citation);
    }

    /*
     * readConfigFile(): read the configuration.csv
     * Return: HashMap
     */
    public static HashMap<String, String> readConfigFile(){
        File config = new File("configuration.csv");
        Scanner fileScanner = null;

        try {
            fileScanner = new Scanner(config);
        } catch (Exception e) {
            System.err.println("File not found.");
            return configData;
        }

        while (fileScanner.hasNextLine()){
            String data_str = fileScanner.nextLine();
            String[] data_split = data_str.split(",");
            String name = data_split[0];
            String path = data_split[1];
            configData.put(name, path);
        }

        return configData;
    }

    public static HashMap<String, String> getConfigData() {
        return configData;
    }

    public static void setConfigData(HashMap<String, String> configData) {
        hw2Main.configData = configData;
    }

    public static UserList getUserList() {
        return userList;
    }

    public static void setUserList(UserList userList) {
        hw2Main.userList = userList;
    }

    public static CitationList getListofCitations() {
        return listofCitations;
    }

    public static void setListofCitations(CitationList listofCitations) {
        hw2Main.listofCitations = listofCitations;
    }

    public static void main(String[] args) {
        configData = readConfigFile();

        listofCitations = new CitationList();
        listofCitations.readCitationFile(configData.get("input file"));

        UserList userList = new UserList();
        userList.readUserFile(configData.get("user file"));
        
        int userchoice;

        do{
            userchoice = Menu.displayMenu();

            switch (userchoice){
                // Display all the Citation data using CitationList.toString()
                case 1: 
                    System.out.println(listofCitations.toString());
                    break;

                // Display all Citations by chosen typeOfOffense
                case 2:
                    System.out.print("Enter the type of offense: ");
                    String typeOfOffense = scanner.nextLine();
                    String output_2 = listofCitations.displayCitationType(typeOfOffense);
                    System.out.println(output_2);
                    break;

                // Search for a Citation by number
                case 3:
                    System.out.print("Enter the number: ");
                    String number_str = scanner.nextLine();
                    int number = Integer.parseInt(number_str);
                    String output_3 = listofCitations.displayCitation(number);
                    System.out.println(output_3);
                    break;

                // Search for a Citation by Person last name
                case 4:
                    System.out.print("Enter the person's last name: ");
                    String lastname = scanner.nextLine();
                    String output_4 = listofCitations.displayCitation(lastname);
                    System.out.println(output_4);
                    break;

                // Add a new Citation
                case 5:
                    newCitation();
                    break;

                // Delete an existing Citation by number
                case 6:
                    System.out.print("Enter the number of citation you want to delete: ");
                    String num_str = scanner.nextLine();
                    int num = Integer.parseInt(num_str);
                    String result = listofCitations.delete(num);
                    System.out.println(result);
                    break;

                // Sort citations by number and display
                case 7:
                    listofCitations.sortByNumber();
                    System.out.println(listofCitations.toString());
                    break;

                // Sort citations by last name and display
                case 8:
                    listofCitations.sortByName();
                    System.out.println(listofCitations.toString());
                    break;

                // Sort citations by type and display
                case 9:
                    listofCitations.sortByType();
                    System.out.println(listofCitations.toString());
                    break;
                
                // Display citations by userID
                case 10:
                    System.out.println(userList.toString());
                    System.out.print("Enter user ID: ");
                    String id_str = scanner.nextLine();
                    int id = Integer.parseInt(id_str);
                    for (Citation c: listofCitations.getListOfCitations()){
                        if (c.getUserID() == id){
                            System.out.println(c.toString());
                        }
                    }
                    break;

                // Quit, or error
                case 0:
                    listofCitations.writeCitationFile(configData.get("output file"));
                    break;
            }
        } while (userchoice != 0);
        
    }

}
