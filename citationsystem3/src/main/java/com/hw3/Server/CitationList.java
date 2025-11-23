/*
 * Name: Althea Chen
 * AndrewID: yuquanch
 */

package com.hw3.Server;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.hw3.CommonPackage.Citation;
import com.hw3.CommonPackage.Person;

// Class: CitationList
// Purpose: This class encapsulates the list of Citations

public class CitationList {
    private ArrayList<Citation> listOfCitations = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in), fileScanner;

    private Map<String, Citation> numberMap;
    private Map<String, ArrayList<Citation>> nameMap;
    private Map<String, ArrayList<Citation>> offenseMap;
    private Map<String, ArrayList<Citation>> userMap;

    public CitationList() {
        listOfCitations = new ArrayList<>();
        numberMap = new HashMap<>();
        nameMap = new HashMap<>();
        offenseMap = new HashMap<>();
        userMap = new HashMap<>();
    }


    /*
     * readCitationFile(): read a Citation CSV file
     * Parameters: String of file name
     * Return: None
     */
    public void readCitationFile(String filename){
        File file = new File(filename);
        try {
            fileScanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            String[] contents = line.split(",");
            if (contents.length < 9) continue;
            
            String number_str = contents[0];
            int number = Integer.parseInt(number_str);
            String typeOfOffense = contents[1];
            String description = contents[2];
            String date = contents[3];
            String fname = contents[4];
            String lname = contents[5];
            String address = contents[6];
            String phonenumber = contents[7];
            String userID_str = contents[8];
            int userID = Integer.parseInt(userID_str);

            Person person = new Person(fname, lname, address, phonenumber);
            Citation citation = new Citation(number, typeOfOffense, description, date, person, userID);
            listOfCitations.add(citation);
        }
        createMaps();
    }

    /*
     * createMaps(): add data to map
     */
    private void createMaps() {
        numberMap.clear();
        nameMap.clear();
        offenseMap.clear();
        userMap.clear();

        for (Citation c : listOfCitations) {
            addToMaps(c);
        }
    }

    private void addToMaps(Citation c) {
        // 1. Number (Unique)
        numberMap.put(String.valueOf(c.getNumber()), c);

        // 2. Name (List)
        String name = c.getPerson().getLastName();
        nameMap.computeIfAbsent(name, k -> new ArrayList<>()).add(c);

        // 3. Offense (List)
        String offense = c.getTypeOfOffense();
        offenseMap.computeIfAbsent(offense, k -> new ArrayList<>()).add(c);

        // 4. UserID (List)
        String userId = String.valueOf(c.getUserID());
        userMap.computeIfAbsent(userId, k -> new ArrayList<>()).add(c);
    }

    /*
     * add(Citation c): Add the citation to the list and all maps
     */
    public void add(Citation c) {
        if (c == null) return;
        listOfCitations.add(c);
        addToMaps(c);
    }

    /*
     * Search methods
     */
    public Citation findByNumber(String number) {
        return numberMap.get(number);
    }

    public ArrayList<Citation> findByName(String name) {
        return nameMap.get(name);
    }

    public ArrayList<Citation> findByTypeOfOffense(String offense) {
        return offenseMap.get(offense);
    }

    public ArrayList<Citation> findByUserID(String userId) {
        return userMap.get(userId);
    }


    /*
     * writeCitationFile(): write a Citation CSV file
     * Parameters: String of file name
     * Return: None
     */
    public void writeCitationFile(String filename){
        try (PrintWriter printWriter = new PrintWriter(new FileWriter(filename))) {
            
            for (Citation citation : listOfCitations) {
                // build the csv string
                String[] datalist = {
                    String.valueOf(citation.getNumber()), 
                    citation.getTypeOfOffense(), 
                    citation.getDescription(), 
                    citation.getDate(), 
                    citation.getPerson().getFirstName(), 
                    citation.getPerson().getLastName(), 
                    citation.getPerson().getAddress(), 
                    citation.getPerson().getPhoneNumber(),
                    String.valueOf(citation.getUserID())
                };
                
                String tocsv = String.join(",", datalist);

                // write the csv string to the file
                printWriter.println(tocsv);
            }
            
            System.out.println("Data successfully saved to " + filename);

        } catch (IOException e) {
            System.err.println("Error writing to file " + filename + ": " + e.getMessage());
        }
    }

    /*
     * delete(Citation c): delete the Citation
     */
    public void delete(Citation c) {
        if (c == null) return;
        listOfCitations.remove(c);
        numberMap.remove(String.valueOf(c.getNumber()));
        removeFromListMap(nameMap, c.getPerson().getLastName(), c);
        removeFromListMap(offenseMap, c.getTypeOfOffense(), c);
        removeFromListMap(userMap, String.valueOf(c.getUserID()), c);
    }

    private void removeFromListMap(Map<String, ArrayList<Citation>> map, String key, Citation c) {
        ArrayList<Citation> list = map.get(key);
        if (list != null) {
            list.remove(c);
            if (list.isEmpty()) {
                map.remove(key);
            }
        }
    }
 


}
