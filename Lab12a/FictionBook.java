public class FictionBook extends Book {
    protected String authorName;

    public FictionBook(String title, int id, int year, String authorName) {
        super(title, id, year);
        this.authorName = authorName;
    }
    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String toString() {
        return super.toString() + String.format("%15s", authorName);
        // return super.toString() + "\n"
        //         + "Author : " + authorName;
    }

}
