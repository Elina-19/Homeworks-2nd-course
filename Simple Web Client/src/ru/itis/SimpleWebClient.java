package ru.itis;

import java.io.*;
import java.net.Socket;
import java.util.Map;

import ru.itis.exceptions.RequestException;

public class SimpleWebClient {
    private static int port = 80;
    private static final String HTTP_VERSION = "HTTP/1.1";
    private static final String HOST_KEY = "HOST:";
    private static String hostValue;
    private static String path;

    private static final String GET_METHOD = "GET";
    private static final String POST_METHOD = "POST";

    private String method;
    private Map<String, String> parameters;

    public InputStream sendRequest(String pathParam){
        checkNull(pathParam);

        try {
            Socket socket = new Socket(hostValue, port);
            PrintWriter toServer = new PrintWriter(
                    new OutputStreamWriter(
                    socket.getOutputStream()));

            switch (method){
                case GET_METHOD:
                    toServer.println(method + " " + path + setRequestParameters() + " " + HTTP_VERSION);
                    toServer.println(HOST_KEY + hostValue);
                    toServer.println();

                case POST_METHOD:
                    toServer.println(method + " " + path + " " + HTTP_VERSION);
                    toServer.println(HOST_KEY + hostValue);

                    String params = setRequestParameters();
                    toServer.println("Content-Length: " + params.length());
                    toServer.println();

                    toServer.println(setRequestParameters());
                    break;
                default: throw new IllegalArgumentException("This method impossible");
            }

            toServer.flush();

            return socket.getInputStream();
        }catch (IOException e){
            throw new RequestException("Request error", e);
        }
    }

    private void checkNull(String pathParam){
        if (hostValue == null){
            throw new NullPointerException("Host can't be null");
        }

        if (method == null){
            throw new NullPointerException("Method can't be null");
        }

        if (pathParam == null || pathParam.trim().equals("")){
            path = "/";
        }
    }

    public void setHost(String host){
        hostValue = host;
    }

    public void setMethod(String method){
        switch (method.toUpperCase()){
            case GET_METHOD:
                this.method = GET_METHOD;
                break;
            case POST_METHOD:
                this.method = POST_METHOD;
                break;
            default: throw new IllegalArgumentException("This method impossible");
        }
    }

    public void setParameters(Map<String, String> parameters){
        this.parameters = parameters;
    }

    public void setPort(int port){
        port = port;
    }

    private String setRequestParameters(){
        if (parameters != null && parameters.size() != 0) {
            StringBuilder sb = new StringBuilder();

            if (method.equals(GET_METHOD)){
                sb.append("?");
            }

            for (Map.Entry<String, String> parameter : parameters.entrySet()) {
                sb.append(parameter.getKey() + "=" + parameter.getValue() + "&");
            }

            return sb.substring(0, sb.length() - 1);
        }

        return "";
    }
}
