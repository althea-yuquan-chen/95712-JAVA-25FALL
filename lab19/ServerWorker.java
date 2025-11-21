import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ServerWorker extends Thread{

    private Socket socket;
    private Scanner sc;
    private DataInputStream input;
    private DataOutputStream output;
    
    private static int clientCounter = 0;

    public ServerWorker(Socket sk){
        this.socket = sk;
        try {
            input = new DataInputStream(sk.getInputStream());
            output = new DataOutputStream(sk.getOutputStream());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        sc = new Scanner(System.in);

        this.start();
        
    }


    public void run(){
        clientCounter += 1;
        System.out.println("Starting new connection for client " + clientCounter);

        String message;
        String reply;

        try{
            while (true) {
                message = input.readUTF();
            
                if (message.equalsIgnoreCase("quit")){
                    break;
                }

                System.out.println(clientCounter + ") Server received: " + message);
                System.out.print(clientCounter + ") Please reply: ");
                reply = sc.nextLine();
                output.writeUTF(reply);

            }
        } catch(EOFException e) {
            System.err.println("Connection closed");            
        } catch(IOException e){
            System.err.println("IO Error: " + e.getMessage());
        } finally{
            try {
                if (this.socket != null && this.socket.isClosed()){
                    this.socket.close();
                }
            } catch (Exception e) {
                System.err.println("Error closing socket: " + e.getMessage());
            }
        }
        
    }

}
