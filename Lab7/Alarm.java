package Lab7;

public class Alarm {
    /* Class data */
    private String message;
    private int id;

    public Alarm(String message, int id) {
        this.message = message;
        this.id = id;
    }

    public Alarm() {
    }

    /* soundTheAlarm(): displays the message and simulates a 911 call
     * Parameters: None
     * Return: None
     */
    public void soundTheAlarm(){
        System.out.println(message + "Call 911");
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Alarm [message=" + message + ", id=" + id + "]";
    }

}
