/* 
 Project: Lab 4 Group Work
 Purpose Details: Get and Post
 Course: IST 411
 Author: Team 3
 Date Developed: 2/10/2020
 Last Date Changed: 2/11/2020
 Revision: 1
 */

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Everyone add your name:
 *
 * @author Kristina Mantha
 * Sam Janvey
 * Chris Lefebvre
 */
public class ClientHandler implements Runnable {
    
    private final Socket socket;
    
    public ClientHandler(Socket socket) {
        this.socket = socket;
    }
    
    @Override
    public void run() {
        
        System.out.println("\nClientHandler Started for "
                + this.socket);
        try {
            handleRequest(this.socket);
        } catch (IOException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("ClientHandler Terminated for "
                + this.socket + "\n");
    }
    
    private void handleRequest(Socket socket) throws IOException {
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));) {
            String headerLine = in.readLine();
            StringTokenizer tokenizer = new StringTokenizer(headerLine);
            String httpMethod = tokenizer.nextToken();
            
            if (httpMethod.equals("GET")) {
                System.out.println("Get Method Processed");
                String httpQueryString = tokenizer.nextToken();
                StringBuilder responseBuffer = new StringBuilder();
                responseBuffer
                        .append("<html><h1>WebServer Home Page.... </h1><br>")
                        .append("<b>Welcome to my web server!</b><BR>")
                        .append("</html>");
                sendResponse(socket, 200, responseBuffer.toString());
            } else {
                System.out.println("The HTTP method is not recognized");
                sendResponse(socket, 405, "Method not allowed");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    private void sendResponse(Socket socket, int statusCode, String responseString) throws IOException {
        String statusLine;
        String serverHeader = "Server: WebServer\r\n";
        String contentTypeHeader = "Content-Type: text/html\r\n";
        
        try (DataOutputStream out = new DataOutputStream(socket.getOutputStream());) {
            
            if (statusCode == 200) {
                statusLine = "HTTP/1.0 200 OK" + "\r\n";
                String contentLengthHeader = "Content-Length: "
                        + responseString.length() + "\r\n";
                
                out.writeBytes(statusLine);
                out.writeBytes(serverHeader);
                out.writeBytes(contentTypeHeader);
                out.writeBytes(contentLengthHeader);
                out.writeBytes("\r\n");
                out.writeBytes(responseString);                
            } else if (statusCode == 405) {
                statusLine = "HTTP/1.0 405 Method Not Allowed" + "\r\n";
                out.writeBytes(statusLine);
                out.writeBytes("\r\n");
            } else {
                statusLine = "HTTP/1.0 404 Not Found" + "\r\n";
                out.writeBytes(statusLine);
                out.writeBytes("\r\n");
            }
            out.close();
        } catch (IOException ex) {
            
        }
    }
    
}
