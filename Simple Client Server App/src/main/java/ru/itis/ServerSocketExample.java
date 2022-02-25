package ru.itis;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketExample {
    public static void main(String[] args) throws Throwable {
        ServerSocket server = new ServerSocket(80);
        System.out.println("1");
        Socket client = server.accept();

        System.out.println(2);
        BufferedReader fromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
        PrintWriter toClient = new PrintWriter(client.getOutputStream(), true);

        System.out.println(3);
        String line;
        System.out.println(fromClient.readLine());
        System.out.println(4);
        while ((line = fromClient.readLine()) != null){
            System.out.println(line);
        }
    }
}
