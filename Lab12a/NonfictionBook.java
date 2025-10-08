public class NonfictionBook extends Book {
    protected String subject;

    public NonfictionBook(String title, int id, int year, String subject) {
        super(title, id, year);
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String toString() {
        return super.toString() + String.format("%15s", subject);
        // return super.toString() + "\n"
        //         + "Subject: " + subject;
    }
}
