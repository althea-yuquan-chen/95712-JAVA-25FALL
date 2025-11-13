import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class CargoFileOperations {
    private String filename;

    public CargoFileOperations(String filename) {
        this.filename = filename;
    }

    public void writeList(ArrayList<Cargo> mylist) {
        // Your code here
        // Write the objects in mylist to an ObjectOutputStream
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;

        try {
            fos = new FileOutputStream(this.filename);
            oos = new ObjectOutputStream(fos);
        } catch (IOException e) {
            System.err.println("Output file failed to open: " + e.getMessage());
            System.exit(1);
        }

        try {
            for (Cargo cargo : mylist) {
                oos.writeObject(cargo);
            }
        } catch (IOException e) {
            System.err.println("Error writing cargo object to file: " + e.getMessage());
            System.exit(1);
        }

        finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    System.err.println("Error closing output file: " + e.getMessage());
                    System.exit(1);
                }
            }
        }
    }

    public ArrayList<Cargo> readList( ) {
        ArrayList<Cargo> mylist = new ArrayList<>();
        // Your code here
        // Read the objects from an ObjectInputStream into mylist and return it
        FileInputStream fis = null;
        ObjectInputStream ois = null;

        try {
            fis = new FileInputStream(this.filename);
            ois = new ObjectInputStream(fis);
        } catch (IOException e) {
            System.err.println("Input file failed to open: " + e.getMessage());
            // If the file can't be opened, return the empty list
            return mylist;
        }

        try {
            while (fis.available()>0) {
                Object obj = ois.readObject();
                mylist.add((Cargo) obj);
            }
        } catch (IOException e) {
            System.err.println("Error reading cargo object from file: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("Error: The Cargo class definition was not found: " + e.getMessage());
        }

        finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    System.err.println("Error closing input file: " + e.getMessage());
                }
            }
        }

        return mylist;
    }

    public void display() {
        // Your code here
        // Use the utility classes to get and display file information
        // Declare a Path object and display its properties
        Path path = Paths.get(this.filename);
        System.out.println("Path.toString(): " + path.toString());
        System.out.println("Path.toAbsolutePath(): " + path.toAbsolutePath());
        System.out.println("Path.getRoot(): " + path.getRoot());

        // Declare a File object and display its properties
        File file = new File(this.filename);
        System.out.println("File.isDirectory(): " + file.isDirectory());
        System.out.println("File.getAbsolutePath(): " + file.getAbsolutePath());

        // Display Files method results on the Path variable
        System.out.println("Files.isExecutable(): " + Files.isExecutable(path));
        System.out.println("Files.isReadable(): " + Files.isReadable(path));
        System.out.println("Files.isWriteable(): " + Files.isWritable(path));
        
    }
}
