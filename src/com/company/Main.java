package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) {
        System.out.println("starting connection");
        try {
            Socket server = new Socket("127.0.0.1", 5500);

            PrintWriter toServer = new PrintWriter(server.getOutputStream(), true);
            BufferedReader fromServer = new BufferedReader(new InputStreamReader(server.getInputStream()));
            Scanner sc = new Scanner(System.in);
            String message = "";
            while (!message.equals("exit")) {
                System.out.println("What do you want send to the server ");
                message = sc.nextLine();  // messaggio inviato al server
                System.out.println("\n"+"phrase: " + message);
                toServer.println(message);
                String answer = fromServer.readLine();
                System.out.println("answer from server:"+ answer);
                System.out.println("you,ve inserted"+answer.split(":")[1]+" charaters"+"\n");
            }

            fromServer.close();
            server.close();
            System.out.println("closing connection");
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

