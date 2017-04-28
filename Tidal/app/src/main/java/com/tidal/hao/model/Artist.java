package com.tidal.hao.model;

/**
 * Created by hao on 4/26/2017.
 */

public class Artist {
    private String profilePicture;
    private String name;
    private int id;

    public Artist(String profilePicture, String name, int id){
        this.profilePicture = profilePicture;
        this.name = name;
        this.id = id;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public String getName() {
        return name;
    }

    public int getId() { return id; }
}
