
/*
 * Name: Althea Chen
 * AndrewID: yuquanch
 */

package Lab5;
import java.util.Scanner;

public class Lab5 {
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        Sensor temperature = new Sensor(0.0, 120.0, 68.0, 1.0, "kitchen", "temperature", 1);
        Device extinguisher = new Device("fire extinguisher", "kitchen", 1);
        Room kitchen = new Room(12.0, 15, 1, "kitchen");
        Alarm bell = new Alarm("Ding!Ding!", 1);

        System.out.println(temperature.toString());
        System.out.println(extinguisher.toString());
        System.out.println(kitchen.toString());
        System.out.println(bell.toString());

        System.out.println();
        System.out.println("Welcome to My Room!");
        String userchoice = "Y";
        while (userchoice.equals("Y")) {
            System.out.print("Do you want to enter a new value? Y/N: ");
            userchoice = scanner.nextLine();
            if (userchoice.equals("Y")){
                System.out.println("Now the current temperature is " + temperature.getCurrentValue());
                System.out.print("Please provide a new temperature: ");
                String new_temp_str = scanner.nextLine();
                double new_temp = Double.parseDouble(new_temp_str);
                temperature.setCurrentValue(new_temp);
                if (temperature.trip()){
                    extinguisher.actuate();
                    bell.soundTheAlarm();
                    temperature.setCurrentValue(68.0);
                }

            }
        }
        

        //     Device extinguisher = new Device("fire extinguisher", "kitchen", 1);
        //     kitchen.addDevice(extinguisher);
        //     for (int i = 0; i < 5; i++) {
        //         Sensor s = new Sensor(0.0, 120.0,
        //                 68.0, 1.0, "kitchen",
        //                 "temperature", i+1);
        //         kitchen.addSensor(s);
        //     }
        //     for (int i = 0; i < 3; i++) {
        //         Alarm a = new Alarm("Ding! Ding!", i+1);
        //         kitchen.addAlarm(a);
        //     }
        //     kitchen.display();

        //     Device chemicalFoamer = new Device("chemical foamer", "kitchen", 2);
        //     kitchen.addDevice(chemicalFoamer);
        //     kitchen.display();
    }

    /*
     * For the four classes: Sensor, Device, Room, Alarm, 
     * all other three classes should be connected to Room, based on their type.
     * If later there would be more rooms, like living rooms, bedrooms, and with more sensors and devices,
     * they could be attached to a specific object of Room class.
     * Users first input in which room they are, and then set aome attributes to the specific sensor.
     * 
     * In this case, main would first call every class to display their attributes (toString()),
     * then would call sensor to get the currect value, and use setters to change it.
     * The new attribute set by the user would be saved for the next call unless it's illegal (actuate the alarm).
     * In cases with more rooms, main class should first call room.getName() to get the location for other sensors and devices,
     * then shares the same workflow.
     */
}
