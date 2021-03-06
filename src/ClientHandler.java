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
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
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
 * Gregory Ramos
 */
public class ClientHandler implements Runnable {
    
    private final Socket socket;
    protected File file;
    
    public ClientHandler(Socket socket) {
        this.socket = socket;
        this.file = new File("src/app/File/DiaryFile");
        
    }

//    
//    @Override
//    public void run() {
//        
//        System.out.println("\nClientHandler Started for "
//                + this.socket);
//        try {
//            handleRequest(this.socket);
//        } catch (IOException ex) {
//            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        System.out.println("ClientHandler Terminated for "
//                + this.socket + "\n");
//    }
//    
//    private void handleRequest(Socket socket) throws IOException {
//        try (BufferedReader in = new BufferedReader(
//                new InputStreamReader(socket.getInputStream()));) {
//            String headerLine = in.readLine();
//            StringTokenizer tokenizer = new StringTokenizer(headerLine);
//            String httpMethod = tokenizer.nextToken();
//            
//            if (httpMethod.equals("GET")) {
//                System.out.println("Get Method Processed");
//                String httpQueryString = tokenizer.nextToken();
//                StringBuilder responseBuffer = new StringBuilder();
//                responseBuffer
//                        .append("<html><h1>WebServer Home Page.... </h1><br>")
//                        .append("<b>Welcome to my web server!</b><BR>")
//                        .append("</html>");
//                sendResponse(socket, 200, responseBuffer.toString());
//            } else if(httpMethod.equals("POST")){
//                System.out.println("POST Method Recieved");
//                String httpPostString = tokenizer.nextToken();
//                String diaryEntry = in.readLine();
//                writeToDiary(diaryEntry);        
//                
////                StringBuilder responseBuffer = new StringBuilder();
////                responseBuffer
////                        .append("<html><h1>WebServer Home Page.... </h1><br>")
////                        .append("<b>Welcome to my web server!</b><BR>")
////                        .append("</html>");
////                sendResponse(socket, 200, responseBuffer.toString());
//                }
//                else {
//                System.out.println("The HTTP method is not recognized");
//                sendResponse(socket, 405, "Method not allowed");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        
//    }
//    
//    private void sendResponse(Socket socket, int statusCode, String responseString) throws IOException {
//        String statusLine;
//        String serverHeader = "Server: WebServer\r\n";
//        String contentTypeHeader = "Content-Type: text/html\r\n";
//        
//        try (DataOutputStream out = new DataOutputStream(socket.getOutputStream());) {
//            
//            if (statusCode == 200) {
//                statusLine = "HTTP/1.0 200 OK" + "\r\n";
//                String contentLengthHeader = "Content-Length: "
//                        + responseString.length() + "\r\n";
//                
//                out.writeBytes(statusLine);
//                out.writeBytes(serverHeader);
//                out.writeBytes(contentTypeHeader);
//                out.writeBytes(contentLengthHeader);
//                out.writeBytes("\r\n");
//                out.writeBytes(responseString);                
//            } else if (statusCode == 405) {
//                statusLine = "HTTP/1.0 405 Method Not Allowed" + "\r\n";
//                out.writeBytes(statusLine);
//                out.writeBytes("\r\n");
//            } else {
//                statusLine = "HTTP/1.0 404 Not Found" + "\r\n";
//                out.writeBytes(statusLine);
//                out.writeBytes("\r\n");
//            }
//            out.close();
//        } catch (IOException ex) {
//            
//        }
//    }
//    
//    
//    public void writeToDiary(String txt){
//        try{
//           FileWriter fw = new FileWriter(file); 
////           fw.write ("This is the Diary of Team 3.");
////           fw.flush();    //writes content of buffer to destination and emptys buffer(does not close stream, can still write)
////           fw.close();    //closes data stream permentantly
//           fw.append(txt + "\r\n");//append is newer format over write
//  
//        }catch (IOException ex){
//            
//        }
//        
//    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }
}
