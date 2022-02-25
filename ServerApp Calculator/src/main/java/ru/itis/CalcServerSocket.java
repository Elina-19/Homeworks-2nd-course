package ru.itis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.rmi.ServerException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalcServerSocket {

    protected static final Pattern methodPattern = Pattern.compile("([\\w]+) (.+)");
    protected static final Pattern pattern = Pattern.compile("\\/([\\w]+)\\?param1=([\\d]+)&param2=([\\d]+) ");
    protected static final String GET_METHOD = "GET";
    protected static final String PATH = "calc";
    protected static final String HTTP_VERSION = "HTTP/1.1";

    protected ServerSocket server;
    protected Socket client;

    public void start(int port){
        try{
            server = new ServerSocket(port);

            while(true) {
                client = server.accept();
                handle(client);

                client.close();
            }
        }catch (IOException e){
            throw new IllegalStateException(e);
        }
    }

    private void handle(Socket client) throws ServerException{
        try{
            BufferedReader fromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter toClient = new PrintWriter(client.getOutputStream());

            try {
                String result = handleRequest(fromClient);
                sendResponse(toClient, result);
            }catch (IllegalArgumentException e){
                sendError(toClient, e);
            }
        }catch(IOException e){
            throw new ServerException("Problem with handling connection.", e);
        }
    }

    private String handleRequest(BufferedReader br) throws IOException{
        String line = br.readLine();
        Matcher matcher;

        try {
            matcher = methodPattern.matcher(line);
        }catch (NullPointerException e){
            throw new IllegalArgumentException(e);
        }

        if (matcher.find()){
            if (!matcher.group(1).toUpperCase().equals(GET_METHOD)){
                client.close();
                return null;
            }

            matcher = pattern.matcher(matcher.group(2));
            matcher.find();

            try {
                if (!matcher.group(1).equals(PATH)) {
                    throw new IllegalArgumentException("Incorrect path");
                }

                Integer param1 = Integer.valueOf(matcher.group(2));
                Integer param2 = Integer.valueOf(matcher.group(3));
                Integer sum = param1 + param2;

                String result = param1 + " + " + param2 + " = " + sum;

                return result;
            }catch (IllegalStateException e){
                throw new IllegalArgumentException("Incorrect request");
            }
        }else {
            client.close();
            return null;
        }
    }

    private void sendResponse(PrintWriter pw, String result){
        String response = "<!DOCTYPE html>" +
                        "<html>" +
                        "<header></header><body>" + result + "</body>" +
                        "</html>";

        pw.write(HTTP_VERSION + " 200 OK\r\n");
        pw.write("Content-Type: text/html;charset=UTF-8\r\n");
        pw.write("Content-Length: " + response.getBytes(StandardCharsets.UTF_8).length + "\r\n");
        pw.write("\r\n");
        pw.write(response);
        pw.flush();
    }

    private void sendError(PrintWriter pw, Exception e){
        String response = "<!DOCTYPE html>" +
                        "<html>" +
                        "<header></header><body>" + "Error: " + e.getMessage() + "</body>" +
                        "</html>";

        pw.write(HTTP_VERSION + " 400 Bad Request\r\n");
        pw.write("Content-Type: text/html;charset=UTF-8\r\n");
        pw.write("Content-Length: " + response.getBytes(StandardCharsets.UTF_8).length + "\r\n");
        pw.write("\r\n");
        pw.write(response);
        pw.flush();
    }
}

