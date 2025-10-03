public class Graduate extends Student {
    protected String capstone;
    public Graduate(String name, double gpa, String capstone) {
        super(name, gpa);
        this.capstone = capstone;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +
               "capstone: " + capstone;
    }

    @Override
    public String complain() {
        return "Why did I take Java?";
    }

}
