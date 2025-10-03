public class GenerateID {
    private int id = 0;
    private static GenerateID instance;

    private GenerateID(){};

    public static GenerateID getInstance(){
        if (instance == null){
            instance = new GenerateID();
        }
        return instance;
    }

    public int newID(){
        this.id++;
        return this.id;
    }

}
