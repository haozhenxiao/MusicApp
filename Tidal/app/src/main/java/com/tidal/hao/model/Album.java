package com.tidal.hao.model;

/**
 * Created by hao on 4/28/2017.
 */

public class Album {
    private String albumImage;
    private String albumName;

    public Album(String albumImage, String albumName){
        this.albumImage = albumImage;
        this.albumName = albumName;
    }

    public String getAlbumImage() {
        return albumImage;
    }

    public String getAlbumName() {
        return albumName;
    }
}
