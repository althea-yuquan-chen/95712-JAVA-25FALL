package Lab7;

public class Room {
    /* Class data */
    private double length, width;
    private int id;
    private String name;

    private SensorCollection sensors;
    private AlarmCollection alarms;
    private Device device;

    public Room(double length, double width, int id, String name) {
        this.length = length;
        this.width = width;
        this.id = id;
        this.name = name;

        this.sensors = new SensorCollection();
        this.alarms = new AlarmCollection();

    }


    public Room() {
        this.sensors = new SensorCollection();
        this.alarms = new AlarmCollection();
    }

    /* getArea(): computes and returns the room's area
     * Parameters: None
     * Return: Double
     */
    public double getArea(){
       return length * width; 
    }

    
    /*
     * Add a SensorCollection, and AlarmCollection, and one Device to Room
     */
    public void addSensor(Sensor s){
        this.sensors.add(s);
    }

    public void addAlarm(Alarm a){
        this.alarms.add(a);
    }

    public void addDevice(Device d){
        this.device = d;
    }

    public void display(){
        System.out.println(device.toString());
        sensors.display();
        alarms.display();
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Room [length=" + length + ", width=" + width + ", id=" + id + ", name=" + name + "]";
    }

}
