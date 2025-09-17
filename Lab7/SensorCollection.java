package Lab7;

import java.util.ArrayList;

public class SensorCollection {
    ArrayList<Sensor> sensors;

    public SensorCollection() {
        this.sensors = new ArrayList<>();
    }

    public SensorCollection(ArrayList<Sensor> sensors) {
        this.sensors = sensors;
    }

    public void display(){
        for (Sensor data: sensors){
            System.out.println(data.toString());
        }
    }

    public void add(Sensor sensor){
        this.sensors.add(sensor);
    }
    
    @Override
    public String toString() {
        return "SensorCollection [sensors=" + sensors + "]";
    }


}
