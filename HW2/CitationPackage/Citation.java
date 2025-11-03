/*
 * Name: Althea Chen
 * AndrewID: yuquanch
 */



import java.util.Arrays;

// Class: Citation
// Purpose: Models one ticket

public class Citation implements Comparable<Citation>{
    private int number;
    private String typeOfOffense;
    private String description;
    private String date;
    private Person person;
    private int userID;

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public Citation(int number, String typeOfOffense, String description, String date, Person person, int userID) {
        this.number = number;
        this.typeOfOffense = typeOfOffense;
        this.description = description;
        this.date = date;
        this.person = person;
        this.userID = userID;
    }

    public Citation() {
    }

    public int compareTo(Citation c){
        return this.getNumber() - c.getNumber();
    }
    
    public String toCSV(){
        return number + "," + typeOfOffense + "," + description + "," + date + "," + person.toCSV() + "," + userID;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getTypeOfOffense() {
        return typeOfOffense;
    }

    public void setTypeOfOffense(String typeOfOffense) {
        String[] offenses = {"Parking", "Speeding", "Jaywalking", "Inebriated"};
        if (Arrays.asList(offenses).contains(typeOfOffense)){
            this.typeOfOffense = typeOfOffense;
        } else {
            System.out.println("Invalid type of offense!");
        }

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Citation #").append(number).append("\n");
        sb.append("Type of Offense: ").append(typeOfOffense).append("\n");
        sb.append("Description: ").append(description).append("\n");
        sb.append("Date: ").append(date).append("\n");
        sb.append("Person: ").append(person.toString()).append("\n");
        sb.append("UserID: ").append(userID);

        return sb.toString();
    }

    


}
