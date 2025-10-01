package Lab10.NotePackage;

import java.util.ArrayList;
import java.util.Collections;

public class NoteCollection {
    private ArrayList<Note> noteList;

    public NoteCollection(){
        this.noteList = new ArrayList<>();
    }

    public void add(Note note){
        this.noteList.add(note);
    }

    public void sortByName(){
        Collections.sort(noteList);
    }

    public void sortbynumber(){
        Collections.sort(noteList, new NumberSorter());
    }

    public void sortbysize(){
        Collections.sort(noteList, new SizeSorter());
    }

    public ArrayList<Note> getAllNotes(){
        return this.noteList;
    }

    public Note getNoteByNumber(int number){
        for (Note note: this.noteList){
            if (note.getNoteNumber() == number){
                return note;
            }
        }
        return null;
    }

    public ArrayList<Note> getNoteByName(String name){
        ArrayList<Note> matchingNotes = new ArrayList<>();
        for (Note note: this.noteList){
            if (note.getName().equals(name)){
                matchingNotes.add(note);
            }
        }
        return matchingNotes;

    }

}
