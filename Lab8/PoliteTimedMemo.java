public class PoliteTimedMemo extends TimedMemo{
    protected static String DEFAULT_GREETING = "Dear";
    protected static String DEFAULT_CLOSING = "Yours truly,";

    public PoliteTimedMemo(String name, String body, String from, String to){
        super(name, body, from, to);
    }

    public String toString(){
        return DEFAULT_GREETING + "\n" + super.toString() + "\n" + DEFAULT_CLOSING;
    }

}
