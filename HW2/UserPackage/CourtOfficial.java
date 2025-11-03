package UserPackage;

public class CourtOfficial extends User{
    private String title;

    public CourtOfficial(int id, String userName, String title) {
        super(id, userName);
        this.title = title;
    }

    @Override
    public String toString() {
        return super.toString() + ", CourtOfficial title: " + title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
