package com.tidal.hao.model;

/**
 * Created by hao on 4/26/2017.
 */

public class Artist {
    private String profilePicture;
    private String name;

    public Artist(String profilePicture, String name){
        this.profilePicture = profilePicture;
        this.name = name;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public String getName() {
        return name;
    }
}
