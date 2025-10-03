import java.util.ArrayList;
import java.util.Scanner;

public class Lab11Main {

    public static void main(String[] args) {
        ArrayList<Student> students = new ArrayList<Student>();
        // Problem 1: change these to use the Factory Method class
        students.add(new Student("Smith", 3.2));                // name, gpa
        students.add(new Undergraduate("Jones", 3.75, "IS"));    // name, gpa, major
        students.add(new Graduate("Adams", 3.89, "ML Project")); // name, gpa, capstone

        // Problem 2: display each student and their complaint
        for (Student student: students){
            System.out.println(student.toString());
            System.out.println(student.complain());
        }

        // Problem 3: create a Schedule object
        // Loop over the Students; print their name, and register
        // them for Java. Then display the schedule
        Schedule schedule = new Schedule();
        for (Student student: students){
            System.out.println(student.name);
            schedule.register("Java");
        }
        schedule.display();

        // Problem 4: display
        for (Student student: students){
            System.out.println(student.toString());
        }

        // Problem 5: comment out Schedule, create a
        //   ScheduleProxy instead
        // Then comment out the schedule register and display and call
        //   ScheduleProxy.register and ScheduleProxy.display instead

    }
}
