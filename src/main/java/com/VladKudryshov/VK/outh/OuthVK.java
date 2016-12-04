package com.VladKudryshov.VK.outh;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class OuthVK {
    private String response;
    private String token;
    private int userId;

    public OuthVK(String response) {
        this.response = response;
        Parse();
    }

    private void Parse(){
        JsonElement jelement = new JsonParser().parse(response);
        JsonObject jobject = jelement.getAsJsonObject();
        this.userId = Integer.valueOf(jobject.get("user_id").toString());
        this.token = jobject.get("access_token").toString().replaceAll("\"","");
    }


    public String getToken() {
        return token;
    }

    public int getUserId() {
        return userId;
    }
}
