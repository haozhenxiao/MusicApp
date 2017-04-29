package com.tidal.hao.model;

/**
 * Created by hao on 4/29/2017.
 */

public class Track {
    private String title;
    private String artist;
    private int duration;

    public Track(String title, String artist, int duration){
        this.title = title;
        this.artist = artist;
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getDuration() {
        int minutes = duration / 60;
        int seconds = duration % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }
}
