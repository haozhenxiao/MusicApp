package com.tidal.hao.model;

/**
 * Created by hao on 4/28/2017.
 */

public class Album {
    private String albumImage;
    private String albumName;
    private String id;
    private String coverImageMedium;

    public Album(String albumImage, String albumName, String id, String coverImageMedium){
        this.albumImage = albumImage;
        this.albumName = albumName;
        this.id = id;
        this.coverImageMedium = coverImageMedium;
    }

    public String getAlbumImage() {
        return albumImage;
    }

    public String getAlbumName() {
        return albumName;
    }

    public String getId() { return id; }

    public String getCoverImageMedium() { return coverImageMedium; }
}
