import javax.xml.catalog.Catalog;

public class View {
    private Catalog catalog;
    private ListOfPatrons listOfPatrons;

    public View(){};

    public View(Catalog catalog, ListOfPatrons listOfPatrons) {
        this.catalog = catalog;
        this.listOfPatrons = listOfPatrons;
    }

    public void displayCatalog() {
        System.out.println(catalog.toString());
    }

    public void displayPatrons() {
        System.out.println(listOfPatrons.toString());
    }

}
