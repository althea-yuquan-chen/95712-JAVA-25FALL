package Lab18;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    public static void handleClient(Socket clientConnection) throws IOException{
        // Wire up a scanner on the client socket
        Scanner in = new Scanner(clientConnection.getInputStream());

        // Wire up a writer on the client socket
        // Note: PrintWriter autoflush enabled
        PrintWriter out = new PrintWriter( 
            new BufferedWriter(new OutputStreamWriter(clientConnection.getOutputStream())), true);

        Scanner scanner = new Scanner(System.in);
        // Get the input message
        String message;
        while (!(message = in.nextLine()).equals("QUIT")){
            System.out.println("Server: " + message);

            // Send the replay
            System.out.print("Enter a reply: ");
            String reply = scanner.nextLine();
            out.println(reply);
            out.flush();
        }
        System.out.println("QUIT");
    }


    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket clientConnection = null;
        int port = 8001;
        try {
            if (args.length == 1) {
                port = Integer.parseInt(args[0]);
            }
            // Create teh server socket
            serverSocket = new ServerSocket(port);
            // Retrieve the client socket when a client tries to connect
            clientConnection = serverSocket.accept();

            handleClient(clientConnection);
            // Wire up a scanner on the client socket
            // Scanner in = new Scanner(clientConnection.getInputStream());

            // // Wire up a writer on the client socket
            // // Note: PrintWriter autoflush enabled
            // PrintWriter out = new PrintWriter( 
            //     new BufferedWriter(new OutputStreamWriter(clientConnection.getOutputStream())), true);

            // Scanner scanner = new Scanner(System.in);
            // // Get the input message
            // String message;
            // while (!(message = in.nextLine()).equals("QUIT")){
            //     System.out.println("Server: " + message);

            //     // Send the replay
            //     System.out.print("Enter a reply: ");
            //     String reply = scanner.nextLine();
            //     out.println(reply);
            //     out.flush();
            // }
            // System.out.println("QUIT");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (clientConnection != null) {
                try {
                    clientConnection.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
