// Lab3
// Your name: Althea Chen
// Your Andrew id: yuquanch

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

// class IOSTuff
// Tests some issues of input and output

public class IOStuff {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String firstName, lastName, ageString, salaryString, category;
        String id;
        int age;
        double salary;

        File file = new File("employee.csv");
        Scanner fileScanner = null;
        try {
            fileScanner = new Scanner(file);
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }

        // System.out.print("Enter first name: ");
        // firstName = scanner.nextLine();
        // System.out.print("Enter last name: ");
        // lastName = scanner.nextLine();
        // System.out.print("Enter id: ");
        // id = scanner.nextLine();
        // try {
        //     System.out.print("Enter age: ");
        //     ageString = scanner.nextLine();
        //     age = Integer.parseInt(ageString);
        //     // age = scanner.nextInt(); // can break by inputting a decimal number
        //     System.out.print("Enter salary: ");
        //     salaryString = scanner.nextLine();
        //     salary = Double.parseDouble(salaryString);
        //     // salary = scanner.nextDouble();
        // } catch (NumberFormatException e ) { // good exception control, with an illegal age, there's no opportunities for users to input the salary
        //     System.out.println(e);
        //     age = 0;
        //     salary = 0.0;
        // }


        // System.out.println("First Name = " + firstName);
        // System.out.println("Last Name = " + lastName);
        // System.out.println("Age = " + age);
        // System.out.println("ID = " + id);
        // System.out.println("Salary = " + salary);

        System.out.printf("%13s %13s %6s %6s %8s %9s\n", "First Name", "Last Name", "Age", "ID", "Salary", "Catogory");
        // System.out.printf("%13s %13s %6d %6s %8.2f\n", firstName, lastName, age, id, salary);

        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            String[] contents = line.split(",");
            firstName = contents[0];
            lastName = contents[1];
            id =  contents[2];
            age = Integer.parseInt(contents[3]);
            salary = Double.parseDouble(contents[4]);
            if ((salary > 150000) || (salary < 0)){
                category = "Error";
            } else if (salary > 70000) {
                category = "High";
            } else if (salary > 35000) {
                category = "Medium";
            } else {
                category = "Low";
            };
            System.out.printf("%13s %13s %6d %6s %8.2f %9s\n", firstName, lastName, age, id, salary, category);
        }



    }
}
