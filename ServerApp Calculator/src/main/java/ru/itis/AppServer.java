package ru.itis;

public class AppServer {
    private static final int PORT = 80;

    public static void main(String[] args){
        CalcServerSocket server = new CalcServerSocket();
        server.start(PORT);
    }
}

