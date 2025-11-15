package Lab18;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Socket clientSocket = null;
        String address = "localhost";
        int port = 8001;
        try {
            if (args.length == 2) {
                address = args[0];
                port = Integer.parseInt(args[1]);
            }
            // Create the client socket and wire it to a reader
            // There's no guarantee that port 8001 is free
            clientSocket = new Socket(address, port);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));

            // Also wire it to a writer
            // Note: PrintWriter has autoflush enabled (the "true" at the end)
            PrintWriter out = new PrintWriter(
                    new BufferedWriter( new OutputStreamWriter(clientSocket.getOutputStream())), true);

            // And wire up a scanner on the keyboard
            Scanner scanner = new Scanner(System.in);
            String outMessage;

            do{
                // For now, a hard-coded message to send
                System.out.print("Enter a string message: ");
                outMessage = scanner.nextLine();
                String inMessage;
                // Send the message to the server
                out.println(outMessage);
                out.flush();
                // Receive the return message
                inMessage = in.readLine();
                System.out.println(inMessage);
            } while (!outMessage.equals("QUIT"));
            

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (clientSocket != null) {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
