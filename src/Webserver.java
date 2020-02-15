/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.BufferedReader;
import java.io.DataOutputStream;
import static java.io.FileDescriptor.out;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author GRamos
 * Chris Lefebvre
 * Sam Janvey
 */
public class Webserver {
    
    ArrayList<String> diaryEntry = new ArrayList<String>();

    public Webserver() {
        
           System.out.println("Webserver Started");
           diaryEntry.add("Dear Diary, Today was a good day");
           diaryEntry.add("Dear Diary, Today was a bad day");
           try (ServerSocket serverSocket = new ServerSocket(8080)) {
               while (true) {
                   System.out.println("Waiting for client request");
                   Socket remote = serverSocket.accept();
                   System.out.println("Connection made");
                   new Thread(new ClientHandler(remote)).start();
               }
           } catch (IOException ex) {
               ex.printStackTrace();
           }
    }
    
    public static void main(String args[]) {
           new Webserver();
     }
    
    public class ClientHandler implements Runnable {
       private final Socket socket;
       
       public ClientHandler(Socket socket) {
           this.socket = socket;
        }
        
       @Override
       public void run() {
           System.out.println("\nClientHandler Started for " +
               this.socket);
           handleRequest(this.socket);
           System.out.println("ClientHandler Terminated for "
               + this.socket + "\n");
       }
    }
     
     
    public void handleRequest(Socket socket) {
           try (BufferedReader in = new BufferedReader(
                   new InputStreamReader(socket.getInputStream()));) {
               String headerLine = in.readLine();
               StringTokenizer tokenizer = new StringTokenizer(headerLine);
               String httpMethod = tokenizer.nextToken();
               
               if (httpMethod.equals("GET")) {
                    System.out.println("Get method processed");                    
                    sendResponse(socket, 200, diaryEntry.toString());
                } 
               
               else if (httpMethod.equals("POST")){
                   System.out.println("Post method processed");
                   String postBodyString = in.readLine();
                   diaryEntry.add(postBodyString);
                   System.out.println(diaryEntry);
               }
               
               else {
                    System.out.println("The HTTP method is not recognized");
                    sendResponse(socket, 405, "Method Not Allowed");
                }
                      
           } catch (Exception e) {
               e.printStackTrace();
           }
           
       }
       public void sendResponse(Socket socket,
               int statusCode, String responseString) {
           
           String statusLine;
           String serverHeader = "Server: WebServer\r\n";
           String contentTypeHeader = "Content-Type: text/html\r\n";
           
           try (DataOutputStream out =
                   new DataOutputStream(socket.getOutputStream());) {
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
               // Handle exception
           }
           
       }
       
}
