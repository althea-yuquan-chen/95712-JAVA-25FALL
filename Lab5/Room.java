package Lab5;

public class Room {
    /* Class data */
    private double length, width;
    private int id;
    private String name;

    public Room(double length, double width, int id, String name) {
        this.length = length;
        this.width = width;
        this.id = id;
        this.name = name;
    }

    public Room() {
    }

    /* getArea(): computes and returns the room's area
     * Parameters: None
     * Return: Double
     */
    public double getArea(){
       return length * width; 
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
