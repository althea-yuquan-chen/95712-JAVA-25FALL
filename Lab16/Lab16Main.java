package Lab16;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class Lab16Main {

    /**
     * Main method to run all I/O tests and print the results.
     */
    public static void main(String[] args) {
        String fileName = "test.txt";
        
        // --- Run 1: n = 10,000 ---
        int n1 = 10000;
        System.out.println("Running tests with n = " + n1);
        
        // Write tests
        long pwTime1 = printWriterTest(fileName, n1);
        System.out.printf("  printWriterTest:    %15d ns\n", pwTime1);
        long bwTime1 = bufferWriterTest(fileName, n1);
        System.out.printf("  bufferWriterTest:   %15d ns\n", bwTime1);
        long fwTime1 = fileWriterTest(fileName, n1);
        System.out.printf("  fileWriterTest:     %15d ns\n", fwTime1);
        
        // Read tests
        long sTime1 = scannerTest(fileName, n1);
        System.out.printf("  scannerTest:        %15d ns\n", sTime1);
        long brTime1 = bufferedReaderTest(fileName, n1);
        System.out.printf("  bufferedReaderTest:   %15d ns\n", brTime1);
        long frTime1 = fileReaderTest(fileName, n1);
        System.out.printf("  fileReaderTest:     %15d ns\n", frTime1); 

        // --- Run 2: n = 1,000,000 ---
        int n2 = 1000000;
        System.out.println("\nRunning tests with n = " + n2);
        
        // Write tests
        long pwTime2 = printWriterTest(fileName, n2);
        System.out.printf("  printWriterTest:    %15d ns\n", pwTime2);
        long bwTime2 = bufferWriterTest(fileName, n2);
        System.out.printf("  bufferWriterTest:   %15d ns\n", bwTime2);
        long fwTime2 = fileWriterTest(fileName, n2);
        System.out.printf("  fileWriterTest:     %15d ns\n", fwTime2);
        
        // Read tests
        long sTime2 = scannerTest(fileName, n2);
        System.out.printf("  scannerTest:        %15d ns\n", sTime2);
        long brTime2 = bufferedReaderTest(fileName, n2);
        System.out.printf("  bufferedReaderTest:   %15d ns\n", brTime2);
        long frTime2 = fileReaderTest(fileName, n2);
        System.out.printf("  fileReaderTest:     %15d ns\n", frTime2); 
    }

    /**
     * 2. Tests PrintWriter wrapping BufferedWriter and FileWriter
     */
    public static long printWriterTest(String fileName, int n) {
        long startTime = System.nanoTime();
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fileName)))) {
            for (int i = 0; i < n; i++) {
                out.print('A');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        long endTime = System.nanoTime(); 
        return endTime - startTime;
    }

    /**
     * 3. Tests BufferedWriter wrapping FileWriter.
     */
    public static long bufferWriterTest(String fileName, int n) {
        long startTime = System.nanoTime();
        try (BufferedWriter out = new BufferedWriter(new FileWriter(fileName))) {
            for (int i = 0; i < n; i++) {
                out.write('A');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    /**
     * 4. Tests a simple FileWriter.
     */
    public static long fileWriterTest(String fileName, int n) {
        long startTime = System.nanoTime();
        try (FileWriter out = new FileWriter(fileName)) {
            for (int i = 0; i < n; i++) {
                out.write('A');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        long endTime = System.nanoTime(); 
        return endTime - startTime;
    }

    /**
     * 5. Tests Scanner wrapping BufferedReader and FileReader.
     */
    public static long scannerTest(String fileName, int n) {
        long startTime = System.nanoTime();
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(fileName)))) {
            scanner.useDelimiter("");
            for (int i = 0; i < n; i++) {
                scanner.next().charAt(0);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    /**
     * 6. Tests BufferedReader wrapping FileReader.
     */
    public static long bufferedReaderTest(String fileName, int n) {
        long startTime = System.nanoTime();
        try (BufferedReader in = new BufferedReader(new FileReader(fileName))) { 
            for (int i = 0; i < n; i++) { 
                in.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        long endTime = System.nanoTime(); 
        return endTime - startTime; 
    }

    /**
     * 7. Tests a simple FileReader.
     */
    public static long fileReaderTest(String fileName, int n) {
        long startTime = System.nanoTime(); 
        try (FileReader in = new FileReader(fileName)) { 
            for (int i = 0; i < n; i++) {
                in.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        long endTime = System.nanoTime();
        return endTime - startTime;
    }
}
