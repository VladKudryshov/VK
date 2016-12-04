package com.VladKudryshov.VK.wall;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;


public class WallVk {
    private String response = null;
    public WallVk(String response) {
        this.response = response;
    }

    private ArrayList DataPost (){
        JsonElement jelement = new JsonParser().parse(response);
        JsonObject jobject = jelement.getAsJsonObject();
        jobject = jobject.getAsJsonObject("response");
        JsonArray jarray = jobject.getAsJsonArray("items");
        int count = Integer.valueOf(jobject.get("count").toString());
        ArrayList<Integer> postId = new ArrayList<Integer>(count);
        for(int i = 0; i<count; i++) {
            jobject = jarray.get(i).getAsJsonObject();
            postId.add(Integer.valueOf(jobject.get("id").toString()));
        }
        return postId;
    }

    public ArrayList getPosts(){
        return DataPost();
    }
}
