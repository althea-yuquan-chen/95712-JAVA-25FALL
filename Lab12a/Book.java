public abstract class Book {
    protected String title;
    protected int id;
    protected int pubYear;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPubYear() {
        return pubYear;
    }

    public void setPubYear(int pubYear) {
        this.pubYear = pubYear;
    }

    public Book(String title, int id, int pubYear) {
        this.title = title;
        this.id = id;
        this.pubYear = pubYear;
    }

    public String toString() {
        String output = String.format("%30s %2d %8d", title, id, pubYear);
        return output;
        // return "*** Book ***" + "\n" +
        //         "ID: " + id + "\n" +
        //         "Title: " + title + "\n" +
        //         "Published: " + pubYear;

    }
}
