package com.VladKudryshov.VK.response;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static org.apache.http.protocol.HTTP.USER_AGENT;

public class ResponseServer {
    private URL url = null;
    private HttpURLConnection con = null;
    private StringBuffer response = null;

    public ResponseServer() {
    }

    public ResponseServer(URL url, HttpURLConnection con) {
        this.url = url;
        this.con = con;
    }

    public String ResponseServerVK(){
        response = new StringBuffer();
        try {
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", USER_AGENT);
            con.setRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true);
        }catch (Exception e){}
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine = "";
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
        }catch (Exception e){
            return "Error answer server";
        }
        return response.toString();
    }

    public void setUrl(String url) {
        try{
            this.url = new URL(url);
        }catch (MalformedURLException e){
            e.toString();
        }
    }

}
