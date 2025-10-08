public class PatronFactory {

    public static Patron createPatron(String type, String name, int id) {
        String normalizedType = type.toLowerCase();
        
        if (normalizedType.equalsIgnoreCase("child")){
            return new Child(name, id);
        } else if (normalizedType.equalsIgnoreCase("senior")){
            return new Senior(name, id);
        } else {
            return new Patron(name, id);
        }
    }
}