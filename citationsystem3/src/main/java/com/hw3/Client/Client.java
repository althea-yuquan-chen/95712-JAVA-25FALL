package com.hw3.Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import com.google.gson.Gson;
import com.hw3.CommonPackage.Citation;
import com.hw3.CommonPackage.Request;

/**
 * Class: Client
 * Purpose: Proxy class that encapsulates the client-side communication.
 * It handles the connection to the server and JSON conversion.
 */
public class Client {
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private Gson gson;

    /**
     * Constructor
     * Purpose: Creates the socket to the host on that port, creates the input and output streams.
     * @param host The server address (e.g., "localhost")
     * @param port The server port
     */
    public Client(String host, int port) {
        try {
            socket = new Socket(host, port);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            gson = new Gson();
        } catch (IOException e) {
            System.err.println("Error connecting to server: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Method: send
     * Purpose: Converts the Request to a JSON record and sends it to the server.
     * @param request The Request object to send.
     */
    public void send(Request request) {
        try {
            String json = gson.toJson(request);
            out.writeUTF(json);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method: receive
     * Purpose: Receives a JSON record from the server and converts it to a Citation object.
     * @return The Citation object received (or null).
     */
    public Citation receive() {
        try {
            String json = in.readUTF();
            return gson.fromJson(json, Citation.class);
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * Method: close
     * Purpose: Close the connection.
     */
    public void close() {
        try {
            if (in != null) in.close();
            if (out != null) out.close();
            if (socket != null) socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
