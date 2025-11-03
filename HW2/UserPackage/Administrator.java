package UserPackage;

public class Administrator extends User{
    private String deparment;

    public Administrator(int id, String userName, String deparment) {
        super(id, userName);
        this.deparment = deparment;
    }

    @Override
    public String toString() {
        return super.toString() + ", Administrator deparment: " + deparment;
    }

    public String getDeparment() {
        return deparment;
    }

    public void setDeparment(String deparment) {
        this.deparment = deparment;
    }
}
