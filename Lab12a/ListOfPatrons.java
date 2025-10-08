import java.util.ArrayList;

public class ListOfPatrons {
    private ArrayList<Patron> patrons = new ArrayList<>();

    public ListOfPatrons( ) {
    }

    public void addPatron(Patron patron) {
        patrons.add(patron);
    }

    public void displayPatrons() {
        System.out.println(this.patrons.toString());
    }

    public Patron searchPatronByID(int id) {
        for (Patron patron: patrons){
            if (patron.getId() == id){
                return patron;
            }
        }
        return null;
    }

    public String toString(){
        StringBuilder output = new StringBuilder();
        output.append("Patrons\n");
        for (Patron patron: patrons){
            output.append(patron.toString()).append("\n");
        }
        return output.toString();
    }
}
