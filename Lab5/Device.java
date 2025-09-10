package Lab5;

public class Device {
    /* Class Data */
    private String type, location;
    private int id;
    
    public Device() {
    }

    public Device(String type, String location, int id) {
        this.type = type;
        this.location = location;
        this.id = id;
    }

    /* actuate(): prints the formatted data in all CAPS
     * Parameters: None
     * Return: None
     */
    public void actuate(){
        System.out.println(this.toString().toUpperCase());
    }

    @Override
    public String toString() {
        return "Device [type=" + type + ", location=" + location + ", id=" + id + "]";
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
