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
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * Everyone add your name:
 *
 * @author Kristina Mantha
 * Gregory Ramos
 */
public class HTTPClient {
    
    private String requestType;
    private String userInput;

    public HTTPClient() {
        Scanner input = new Scanner(System.in);
        
        System.out.println("HTTP Client is Started");
        System.out.print("Which request type would you like? Please type GET or POST?");
        requestType = input.nextLine();
        
        if(requestType.equals("POST")){
            System.out.println("Since you selected POST, please type what you would like to add to the diary:");
        }

        try {
            InetAddress serverInetAddress
                    = InetAddress.getByName("127.0.0.1");
            Socket connection = new Socket(serverInetAddress, 8080);

            try (OutputStream out = connection.getOutputStream();
                    BufferedReader in
                    = new BufferedReader(new InputStreamReader(
                                    connection.getInputStream()))) {
                sendGet(out);
                System.out.println(getResponse(in));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void sendGet(OutputStream out) {
        try {
            out.write("GET /default\r\n".getBytes());
            out.write("User-Agent: Mozilla/5.0\r\n".getBytes());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private String getResponse(BufferedReader in) {
        try {
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine).append("\n");
            }
            return response.toString();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return "";
    }

    public static void main(String args[]) {
        new HTTPClient();
    }

}
