import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lab15 {
    public static ArrayList<Customer> readCustomers(String filename)
            throws IOException {

        // Array list of Customer objects
        ArrayList<Customer> list = new ArrayList<>();
        // Create a new File object
        File file = new File(filename);      

        // If the file does not exist, throw a FileNotFound exception
        if (!file.exists()){
            throw new FileNotFoundException(filename + "not found.");
        }

        // Create a new Scanner on the file object
        Scanner fileScanner = new Scanner(file);  

        // While fileScanner has a next line
            // Read the next line and split it
            // Convert the rating to an int; throw NumberFormatException if bad
            // Convert the balance to a double; throw NumberFormatException if bad
            // Create a new customer object, add it to list
        while (fileScanner.hasNextLine()){
            String line = fileScanner.nextLine();
            String[] line_split = line.split(",");
            String firstName = line_split[0];
            String lastName = line_split[1];
            String rating_str = line_split[2];
            int rating = Integer.parseInt(rating_str);
            String balance_str = line_split[3];
            Double balance = Double.parseDouble(balance_str);

            Customer customer = new Customer(firstName, lastName, rating, balance);
            list.add(customer);
        }
        
        return list;
    }

    public static void problem9(){
        ArrayList<String> str_list = new ArrayList<>();
        File file = new File("testdata.txt");
        Scanner fs = null;
        try {
            fs = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.err.println("File not found.");
        }

        while (fs.hasNextLine()) {
            String line = fs.nextLine();
            str_list.add(line);
            System.out.println(line);            
        }
        System.out.println();

        ArrayList<String> patterns = new ArrayList<>();

        patterns.add("\\d");            // lines containing any digit
        patterns.add("[a-zA-Z]");       // lines containing any letter
        patterns.add("\\d+");           // lines containing an integer
        patterns.add("^a");             // lines beginning with letter 'a'
        patterns.add("s$");             // lines ending with letter 's'
        patterns.add("\\(");            // lines containing left parenthesis
        patterns.add("a.*e|e.*a");      // lines containing 'a' and 'e' in either order
        patterns.add("aeiou");          // lines containing the lower case vowels next to each other in order
        patterns.add("a.*e.*i.*o.*u");  // lines containing the lower case vowels in order

        for (String patternString : patterns) {
            System.out.println("Pattern: [" + patternString + "]");
            Pattern pattern = Pattern.compile(patternString);

            for (String line : str_list) {
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    System.out.println("Matched: " + line);
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        ArrayList<Customer> clist = null;
        // Problem 3
        // Call readCustomers with the data file as a parameter
        try {
            clist = readCustomers("customers.csv");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        // Print the array list
        System.out.println("Original list");
        for (Customer customer: clist){
            System.out.println(customer.toString());
        }
        System.out.println();

        // Problem #4
        // Create PriorityQueue queue1
        PriorityQueue<Customer> queue1 = new PriorityQueue<>();
        try {
            for (Customer customer: clist){
                queue1.add(customer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Problem #5
        // Create PriorityQueue queue2
        PriorityQueue<Customer> queue2 = new PriorityQueue<>(new Comparator<Customer>() {
            public int compare(Customer c1, Customer c2){
                return Double.compare(c1.getBalance(), c2.getBalance());
            }
        });
        try {
            for (Customer customer: clist){
                queue2.add(customer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Problem #6
        // Remove things one at a time from queue1 and print them
        System.out.println("Queue1 processing");
        try {
            while (true) {
                System.out.println(queue1.element());
                queue1.remove();                
            }
        } catch (Exception e) {
            System.err.println("Done");
            System.out.println();
        }

        // Problem #7
        // Remove things one at a time from queue2 and print them
        System.out.println("Queue2 processing");
        try {
            while (true) {
                System.out.println(queue2.element());
                queue2.remove();                
            }
        } catch (Exception e) {
            System.err.println("Done");
            System.out.println();
        }

        // Problem #8
        // Try this on your own
        ArrayList<Customer> test_List = null;
        String newline1 = "\nAlthea,Chen,8.3,300.0";
        String newline2 = "Zoie,Yu,8,None";
                        
        // verify the fail in integer parsing
        System.out.println("integer parsing");
        try (FileWriter fw = new FileWriter("customers.csv", true);
            PrintWriter pw = new PrintWriter(fw)) {
            pw.print(newline1);
        } catch (IOException e) {
            e.printStackTrace();
        } 

        try {
            test_List = readCustomers("customers.csv");
        } catch (NumberFormatException e) {
            System.err.println("Invalid number format");
        } catch (IOException e) {
            e.printStackTrace();
        } 
        System.out.println();

        // delete newline1
        System.out.println("deleting invalid new lines");
        File customerFile = new File("customers.csv");
        Scanner fs = null;
        try {
            fs = new Scanner(customerFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        ArrayList<String> contents = new ArrayList<>();
        while (fs.hasNextLine()) {
            String content = fs.nextLine();
            contents.add(content);
        }
        contents.remove(contents.size()-1);
        Path path = Paths.get("customers.csv");
        try {
            Files.write(path, contents); 
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println();

        // verify the fail in double parsing
        System.out.println("double parsing");
        try (FileWriter fw = new FileWriter("customers.csv", true);
            PrintWriter pw = new PrintWriter(fw)) {
            pw.print(newline2);
        } catch (IOException e) {
            e.printStackTrace();
        } 

        try {
            test_List = readCustomers("customers.csv");
        } catch (NumberFormatException e) {
            System.err.println("Invalid number format");
        } catch (IOException e) {
            e.printStackTrace();
        } 
        System.out.println();

        // delete newline2
        System.out.println("deleting invalid new lines");
        try {
            Files.write(path, contents); 
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println();

        // change file name
        System.out.println("change file name");
        try {
            test_List = readCustomers("bob.csv");
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println();

        // Problem #9
        problem9();
    }
}
