package ru.itis;

import ru.itis.exceptions.RequestException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;

public class WebClientApp {
    public static void main(String[] args)throws IOException {
        SimpleWebClient web = new SimpleWebClient();
        web.setMethod("GET");
        web.setHost("google.com");
        web.setParameters(Collections.singletonMap("id", "69293"));

        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(web.sendRequest("")))){

            String line;

            while ((line = br.readLine()) != null){
                System.out.println(line);
            }
        }catch (IOException e){
            throw new RequestException("Request error", e);
        }
    }
}
