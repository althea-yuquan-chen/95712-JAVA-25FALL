import java.util.ArrayList;

public class Patron {
    protected String name;
    protected int id;
    protected CheckedOutBook checkedOutBooks;

    public Patron(String name, int id) {
        this.name = name;
        this.id = id;
        checkedOutBooks = new CheckedOutBook();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void checkOutBook(Book book) {
        checkedOutBooks.checkOutBook(book);
    }

    @Override
    public String toString() {
        String type = "Patron";
        if (this instanceof Child){
            type = "Child";
        } else if (this instanceof Senior){
            type = "Senior";
        }
        String output = String.format("%8s %10s %3d", type, name, id);
        return output;
        // return "*** Patron ***" + "\n" +
        //         "Name: " + name + "\n" +
        //         "ID: " + id + "\n" +
        //         "CheckedOut Books: \n" + checkedOutBooks.toString();
    }
}
