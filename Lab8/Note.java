public  class Note {
    // Static data, shared by all objects
    protected static int noteCount = 0;     // Keeps track of # of notes created so far
    protected static final String FOOTER = "***** Powered by Make-A-Note *****";

    // Note data, specific to one object
    protected String name;
    protected String body;
    protected int noteNumber;

    // Default constructor should not be used
    public Note() {}

    // Overloaded constructor
    public Note(String name, String body) {
        this.name = name;
        this.body = body;
        noteCount++;                    // Increment how many notes have been created
        this.noteNumber = noteCount;    // Set this note's id to the current count
    }

    // Getters
    public int getNoteNumber() { return noteNumber; }
    public String getName() {return name;}

    // toString for printing
    public String toString() { return "Name: " + name + "\nBody: "
            + body + "\nNote# " + noteNumber + "\n" + FOOTER;}
}
