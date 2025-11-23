package com.hw3.Server;

import java.util.ArrayList;
import java.util.HashMap;

import com.hw3.CommonPackage.Citation;
import com.hw3.CommonPackage.FieldType;
import com.hw3.CommonPackage.Request;
import com.hw3.CommonPackage.RequestType;
import com.hw3.CommonPackage.Configuration;

/**
 * Class: HW3Server
 * Purpose: The main server program. It reads configuration, initializes data,
 * listens for client requests, processes them using CitationList, and sends back responses.
 */
public class HW3Server {
    
    public static void main(String[] args) {
        // 1. Read configuration
        HashMap<String, String> config = Configuration.readConfigurationFile();
        
        // Get port and filename from config (providing defaults just in case)
        int port = Integer.parseInt(config.getOrDefault("Port", "3000"));
        String citationFile = config.getOrDefault("CitationFile", "citations.csv");

        // 2. Initialize CitationList and read data
        CitationList citationList = new CitationList();
        citationList.readCitationFile(citationFile);
        System.out.println("Citation data loaded.");

        // 3. Create Server (Proxy)
        Server server = new Server(port);

        boolean running = true;
        while (running) {
            // 4. Receive Request
            Request request = server.receive();
            
            // If connection dropped or request is null
            if (request == null) break;

            // Prepare the response object
            Citation responseCitation = null;

            RequestType type = request.getRequestType();
            FieldType field = request.getFieldType();
            Citation reqCitation = request.getCitation(); // The data sent by client

            if (type == RequestType.QUIT) {
                running = false;
                System.out.println("Client requested QUIT.");
            } 
            else if (type == RequestType.INSERT) {
                // Add the citation
                citationList.add(reqCitation);
                // Send back an empty object as acknowledgement 
                responseCitation = new Citation(); 
            } 
            else if (type == RequestType.DELETE) {
                // To delete, we first need to find the specific object in our list
                // Assuming the client sends a dummy citation with the ID to delete
                Citation target = citationList.findByNumber(String.valueOf(reqCitation.getNumber()));
                if (target != null) {
                    citationList.delete(target);
                }
                // Send back an empty object 
                responseCitation = new Citation();
            } 
            else if (type == RequestType.SEARCH) {
                // Handle 4 types of searches
                if (field == FieldType.NUMBER) {
                    // Search by Number (Unique)
                    responseCitation = citationList.findByNumber(String.valueOf(reqCitation.getNumber()));
                } 
                else if (field == FieldType.NAME) {
                    // Search by Last Name (List)
                    String lastName = reqCitation.getPerson().getLastName();
                    ArrayList<Citation> results = citationList.findByName(lastName);
                    // Only send back the first one 
                    if (results != null && !results.isEmpty()) {
                        responseCitation = results.get(0);
                    }
                } 
                else if (field == FieldType.TYPEOFOFFENSE) {
                    // Search by Offense (List)
                    String offense = reqCitation.getTypeOfOffense();
                    ArrayList<Citation> results = citationList.findByTypeOfOffense(offense);
                    if (results != null && !results.isEmpty()) {
                        responseCitation = results.get(0);
                    }
                } 
                else if (field == FieldType.USERID) {
                    // Search by UserID (List)
                    String userId = String.valueOf(reqCitation.getUserID());
                    ArrayList<Citation> results = citationList.findByUserID(userId);
                    if (results != null && !results.isEmpty()) {
                        responseCitation = results.get(0);
                    }
                }
            }

            // 5. Send Response (if not quitting)
            if (running) {
                // If nothing found, responseCitation is null. 
                // Ideally we send null or an empty object to indicate "not found".
                server.send(responseCitation);
            }
        }

        // 6. Close resources and save data
        server.close();
        citationList.writeCitationFile(citationFile);
        System.out.println("Server stopped and data saved.");
    }
}
