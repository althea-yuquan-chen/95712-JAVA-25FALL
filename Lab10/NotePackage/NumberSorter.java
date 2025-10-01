package Lab10.NotePackage;

import java.util.Comparator;

public class NumberSorter implements Comparator<Note>{
    public int compare(Note note1, Note note2){
        if (note1.getNoteNumber() == note2.getNoteNumber()) {return 0;}
        else if (note1.getNoteNumber() > note2.getNoteNumber()) {return 1;}
        else {return -1;}
    }

}
