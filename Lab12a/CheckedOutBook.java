import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class CheckedOutBook {
    private Map<Book, LocalDate> books = new HashMap();

    public CheckedOutBook() {}
    public void checkOutBook(Book book) {
        books.put(book, LocalDate.now().plusDays(14));
    }
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Map.Entry<Book, LocalDate> entry : books.entrySet()) {
            result.append(entry.getKey().toString() + "\n");
            result.append("Due: ").append(entry.getValue()).append("\n");
        }
        return result.toString();
    }
}
