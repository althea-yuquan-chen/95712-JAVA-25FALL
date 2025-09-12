package Lab6;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;



public class Lab6Main {
    private static File myfile;
    private static Scanner fileScanner;
    private static ArrayList<YearPop> data;

    /*
     * openFile(): open an CSV file in a try-catch block
     * Parameters: String (CSV file)
     * Return: boolean type whether the file is open successfully or not
     */
    public boolean openFile(String filename){
        myfile = new File(filename);
        Boolean result = true;
        try {
            fileScanner = new Scanner(myfile);
        } catch (FileNotFoundException e){
            result = false;
        }

        return result;
    }

    /*
     * makeData(): take one String parameter named line in the CSV format, splits it,
     * creates a new YearPop object with that data in it
     * Parameters: String (lines in a CSV format)
     * Return: YearPop
     */
    public YearPop makeData(String line){
        String[] contents = line.split(",");
        int year = Integer.parseInt(contents[0].trim());
        double pop = Double.parseDouble(contents[1].trim());

        return new YearPop(year , pop);
    }

    /*
     * createList(): new up data: use a loop to read lines from the data file,
     * call makeData() on each line, then place the returned YearPop object into 
     * the data ArrayList
     * Parameters: None;
     * Return: None
     */
    public void createList(){
        data = new ArrayList<>();
        while (fileScanner.hasNextLine()){
            String line = fileScanner.nextLine();

            if (line.trim().isEmpty()){
                continue;
            }

            YearPop yearPopObject = makeData(line);
            data.add(yearPopObject);
        }

    }

    /*
     * findYear(): look for year in the ArrayList data
     * if it finds it, return the population density;
     * if not, return -1.0
     * Parameters: year
     * Return: population
     */
    public double FindYear(int year){
        for (YearPop dataPoint: data){
            if (dataPoint.getYear() == year){
                return dataPoint.getPop();
            }
        }
        return -1;

    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        Lab6Main lab = new Lab6Main();

        // if (!lab.openFile("paPop.csv")) {
        //     System.out.println("Error: The file 'paPop.csv' could not be opened. Exiting.");
        //     sc.close();
        //     return; // Exit the program
        // }

        if (lab.openFile("Lab6/paPop.csv")){
            lab.createList();
        }

        String userChoice;
        do {
            System.out.print("Please enter the year:");
            String userinput = sc.nextLine();
            int year = Integer.parseInt(userinput);

            double pop = lab.FindYear(year);

            System.out.println("Year: " + year);
            System.out.println("Population density: " + pop);

            System.out.print("Do you want to look up another? Y/N: ");
            userChoice = sc.nextLine();

        } while (userChoice.equals("Y"));

    }
}
