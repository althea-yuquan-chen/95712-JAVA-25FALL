public class TimedMemo extends Memo{
    protected String today = java.time.LocalDate.now().toString();

    public TimedMemo(){}

    public TimedMemo(String name, String body, String from, String to){
        super(name, body, from, to);
    }

    public String toString(){
        return "Date: " + today + "\n" + super.toString();
    }


}
