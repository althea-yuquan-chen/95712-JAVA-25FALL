package Lab7;

public class Lab7 {

    public static void main(String[] args){
        Room room1 = new Room(12, 15, 1, "kitchen");
        Device extinguisher = new Device("fire extinguisher", "kitchen", 1);

        room1.addDevice(extinguisher);

        for (int i = 1; i < 6; i++){
            Sensor s = new Sensor(0, 120, 68, 1, "kitchen", "temperature", i);
            room1.addSensor(s);
        }

        for (int i = 1; i < 4; i++){
            Alarm a = new Alarm("Ding!Ding!", i);
            room1.addAlarm(a);
        }

        room1.display();

        Device chemicalFoamer = new Device("chemical foamer", "kitchen", 2);
        room1.addDevice(chemicalFoamer);

        room1.display();
         

    }
  

}
