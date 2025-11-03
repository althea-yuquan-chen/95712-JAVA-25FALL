package UserPackage;

public class Officer extends User{
    private String rank;

    public Officer(int id, String userName, String rank) {
        super(id, userName);
        this.rank = rank;
    }

    @Override
    public String toString() {
        return super.toString() + ", Officer rank: " + rank;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }


}
