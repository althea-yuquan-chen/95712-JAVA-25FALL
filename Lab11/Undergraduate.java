public class Undergraduate extends Student {
    protected String major;

    public Undergraduate(String name, double gpa, String major) {
        super(name, gpa);
        this.major = major;
    }

    @Override
    public String toString() {
        return super.toString()  + "\n" +
               "major: " + major;
    }

    @Override
    public String complain() {
        return "Not enough beer";
    }

}
