package Lab7;

import java.util.ArrayList;

public class AlarmCollection {
    ArrayList<Alarm> alarms;

    public AlarmCollection() {
        this.alarms = new ArrayList<>();
    }

    public AlarmCollection(ArrayList<Alarm> alarms) {
        this.alarms = alarms;
    }

    public void display(){
        for (Alarm data: alarms){
            System.out.println(data.toString());
        }
    }

    public void add(Alarm alarm){
        this.alarms.add(alarm);
    }

    @Override
    public String toString() {
        return "AlarmCollection [alarms=" + alarms + "]";
    }

    

}
