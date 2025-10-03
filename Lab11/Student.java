public class Student {
    protected String name;
    protected double gpa;
    protected int id;

    public Student(String name, double gpa) {
        this.name = name;
        this.gpa = gpa;
        // ID is auto-assigned
        setNewID();
    }

    public void setNewID() {
        // Problem 4 changes the following line
        //  Also need the GenerateID class
        GenerateID genID = GenerateID.getInstance();
        this.id = genID.newID();
    }

    @Override
    public String toString() {
        return "Name: " + name + "\n" +
               "gpa: " + gpa + "\n" +
               "id: " + id;
    }

    public String complain() {
        return "Gaa, too much work";
    }

}
