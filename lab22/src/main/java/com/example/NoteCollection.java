package com.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class NoteCollection {
    private ArrayList<Note> noteList = new ArrayList<>();
    public NoteCollection() {}
    public void add(Note n) { noteList.add(n);}
    public ArrayList<Note> getAllNotes() {
        // Note: This method breaks encapsulation.
        // Instead of returning noteList, this method
        //    should make a new copy of noteList and
        //    return that: as in
        //    ArrayList<Note> temp = new ArrayList<>();
        //    for (Note n: noteList) {
        //        temp.add(n);
        //    }
        //    return temp;
        // This isn't enough, though, because the Note objects themselves
        //    are references. Here, it would be really helpful if the Note
        //    class and its children had a copy constructor.
        // This is true for the other methods in this class
        return noteList;
    }
    public Note getNoteByNumber(int number) {
        for (Note note: noteList) {
            if (note.getNoteNumber() == number) {
                return note;
            }
        }
        return null;
    }
    public ArrayList<Note> getNoteByName(String name) {
        ArrayList<Note> matches = new ArrayList<>();
        for (Note note: noteList) {
            if (note.getName().equals(name)) {
                matches.add(note);
            }
        }
        return matches;
    }

    public void readNotes(String filename) {
        File file = new File(filename);
        Scanner fileScanner = null;
        try {
            fileScanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("Could not open file");
            System.exit(0);
        }
        while (fileScanner.hasNextLine()) {
            String[] data = fileScanner.nextLine().split(",");
            // Notes have name and body; number assigned in constructor
            noteList.add( new Note(data[0],data[1]));
        }
    }

    public int getCount() { return noteList.size();}
}
