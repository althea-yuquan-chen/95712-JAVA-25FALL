import java.util.ArrayList;

public class Catalog {
    private ArrayList<Book> books = new ArrayList<>();
    public Catalog() {
        books.add(new FictionBook("Murder on the Java Express", 1, 2025, "Barrett"));
        books.add(new FictionBook("Java is Fun", 2, 2025, "JoeStudent"));
        books.add(new NonfictionBook("Java 101", 3, 2020, "Java"));
        books.add(new NonfictionBook("Python 101", 4, 2018, "Python"));
    }
    public void add(Book book) {
        books.add(book);
    }
    public Book searchByTitle(String title) {
        for (Book book : books) {
            if (book.getTitle().equals(title)) {
                return book;
            }
        }
        return null;
    }

    public Book searchByID(int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }

    public void display() {
        for (Book book: books){
            System.out.println(book);
        }
    }

    public String toString(){
        StringBuilder output = new StringBuilder();
        output.append("Catalog\n");
        String type = "";
        for (Book book: books){
            if (book instanceof FictionBook){
                type = "Fiction";
            } else {
                type = "Nonfiction";
            }
            output.append(String.format("%10s %s\n", type, book.toString()));
        }
        return output.toString();
    }
}
