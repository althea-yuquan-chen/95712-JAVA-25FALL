package Lab10.NotePackage;

public class PoliteTimedMemo extends TimedMemo{
    protected static String DEFAULT_GREETING = "Dear";
    protected static String DEFAULT_CLOSING = "Yours truly,";

    public PoliteTimedMemo(String name, String body, String from, String to){
        super(name, body, from, to);
    }

    public String toString(){
        return "Date: " + super.today + "\nName: " + name + "\n" + 
        DEFAULT_GREETING + " " + from + ":\n" + body + "\n" + DEFAULT_CLOSING
         + "\n" + to + "\nNote# " + super.noteNumber + "\n" + FOOTER;
    }

}
