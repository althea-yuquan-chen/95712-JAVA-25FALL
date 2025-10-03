import java.util.HashMap;

public class Schedule {
    private static final String[] courseList = {"Statistics", "Java", "BIDA Capstone"};
    private HashMap<String, Integer> courses = new HashMap<>();
    public Schedule() {
        for (String s: courseList) {
            courses.put(s, 0);
        }
    }
    public void register(String course) {
        if (courses.containsKey(course)) {
            courses.put(course, courses.get(course) + 1);
        }
    }
    public void display() {
        System.out.println("Course - Enrollment");
        for (String s: courseList) {
            System.out.println(s + ": " + courses.get(s));
        }
    }
}
