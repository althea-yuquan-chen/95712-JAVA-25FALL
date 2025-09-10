package Lab5;

public class Sensor {
    /* Class Data */
    private double minimum, maximum, currentValue, interval;
    private String location, type;
    private int id;

    public Sensor() {
    }

    /* overloaded constructor for all the member data */ 
    public Sensor(double minimum, double maximum, double currentValue, double interval, String location, String type,
            int id) {
        this.minimum = minimum;
        this.maximum = maximum;
        this.currentValue = currentValue;
        this.interval = interval;
        this.location = location;
        this.type = type;
        this.id = id;
    }

    /*
     * trip(): returns true if the currentValue is less than minimum or greater than maximum and returns false otherwise
     * Parameters: None
     * Return: Boolean
     */
    public boolean trip() {
        return currentValue < minimum || currentValue > maximum;
    }

    /* Getters and setters */
    public double getMinimum() {
        return minimum;
    }

    public double getMaximum() {
        return maximum;
    }

    public double getCurrentValue() {
        return currentValue;
    }

    public double getInterval() {
        return interval;
    }

    public String getLocation() {
        return location;
    }

    public String getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public void setMinimum(double minimum) {
        this.minimum = minimum;
    }

    public void setMaximum(double maximum) {
        this.maximum = maximum;
    }

    public void setCurrentValue(double currentValue) {
        this.currentValue = currentValue;
    }

    public void setInterval(double interval) {
        this.interval = interval;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Sensor [minimum=" + minimum + ", maximum=" + maximum + ", currentValue=" + currentValue + ", interval="
                + interval + ", location=" + location + ", type=" + type + ", id=" + id + "]";
    }

}
