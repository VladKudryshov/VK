package com.VladKudryshov.VK.model;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class AccountVK {
    private String response = null;
    private String firstName = null;
    private String lastName = null;
    private String UrlPhoto = null;
    private String online = null;

    //Counters
    private String friends;
    private String groups;
    private String videos;
    private String audios;


    public AccountVK(String response) {
        this.response = response;
        DataAccount();
    }

    public void DataAccount(){
        JsonElement jelement = new JsonParser().parse(response);
        JsonObject  jobject = jelement.getAsJsonObject();
        JsonArray jarray = jobject.getAsJsonArray("response");
        jobject = jarray.get(0).getAsJsonObject();

        firstName = jobject.get("first_name").toString().replaceAll("\"","");
        lastName = jobject.get("last_name").toString().replaceAll("\"","");
        UrlPhoto = jobject.get("photo_100").toString().replaceAll("\"","");
        online = isOnline(jobject.get("online").toString());

        jobject = jobject.getAsJsonObject("counters");
        friends = jobject.get("friends").toString();
        groups = jobject.get("groups").toString();
        videos = jobject.get("videos").toString();
        audios = jobject.get("audios").toString();
    }

    private String isOnline(String online){
        if(online.equals("1")){return "Online";}
        return "Offline";
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUrlPhoto() {
        return UrlPhoto;
    }

    public String getOnline() {
        return online;
    }

    public String getFriends() {
        return friends;
    }

    public String getGroups() {
        return groups;
    }

    public String getVideos() {
        return videos;
    }

    public String getAudios() {
        return audios;
    }
}
