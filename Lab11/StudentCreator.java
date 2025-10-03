public class StudentCreator {
    public static Student createRecord(String kindOfStudent, String name, double gpa, String otherThing){
        Student student = null;
        if (kindOfStudent.equalsIgnoreCase("graduate")){
            student = new Graduate(name, gpa, otherThing);
        } else if (kindOfStudent.equalsIgnoreCase("undergraduate")){
            student = new Undergraduate(name, gpa, otherThing);
        } else {
            student = new Student(name, gpa);
        }

        return student;
    }

}
