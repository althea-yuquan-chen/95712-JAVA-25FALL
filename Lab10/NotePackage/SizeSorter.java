package Lab10.NotePackage;

import java.util.Comparator;

public class SizeSorter implements Comparator<Note>{
    public int compare(Note note1, Note note2){
        int size1 = note1.toString().length();
        int size2 = note2.toString().length();
        
        if (size1 == size2) {return 0;}
        else if (size1 > size2) {return 1;}
        else {return -1;}
    }

}
