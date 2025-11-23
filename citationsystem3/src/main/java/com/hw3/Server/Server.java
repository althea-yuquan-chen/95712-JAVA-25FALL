package com.hw3.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.google.gson.Gson;
import com.hw3.CommonPackage.Citation;
import com.hw3.CommonPackage.Request;

/**
 * Class: Server
 * Purpose: Proxy class that encapsulates the server-side communication.
 * It hides the details of JSON conversion and socket handling.
 */
public class Server {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private DataInputStream in;
    private DataOutputStream out;
    private Gson gson;

    /**
     * Constructor
     * Purpose: Creates the ServerSocket on that port, calls accept() which returns the socket 
     * to use for talking to the client, and creates the input and output streams on the client's socket.
     * * @param port The port number to listen on.
     */
    public Server(int port) {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server started. Waiting for client...");
            
            // Wait for a client to connect
            clientSocket = serverSocket.accept(); 
            System.out.println("Client connected.");
            
            // Initialize streams and Gson
            in = new DataInputStream(clientSocket.getInputStream());
            out = new DataOutputStream(clientSocket.getOutputStream());
            gson = new Gson();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method: receive
     * Purpose: Receives a JSON record from the client and converts it to a Request object.
     * * @return The Request object sent by the client.
     */
    public Request receive() {
        try {
            String json = in.readUTF();
            return gson.fromJson(json, Request.class);
        } catch (IOException e) {
            // Connection likely closed or error occurred
            return null; 
        }
    }

    /**
     * Method: send
     * Purpose: Converts the Citation to a JSON record and sends it to the client.
     * * @param citation The Citation object to send.
     */
    public void send(Citation citation) {
        try {
            String json = gson.toJson(citation);
            out.writeUTF(json);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method: close
     * Purpose: Close the connection and the socket.
     */
    public void close() {
        try {
            if (in != null) in.close();
            if (out != null) out.close();
            if (clientSocket != null) clientSocket.close();
            if (serverSocket != null) serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
