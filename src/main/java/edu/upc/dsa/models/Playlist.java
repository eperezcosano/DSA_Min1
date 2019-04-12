package edu.upc.dsa.models;

public class Playlist {

    //Attributes
    private String idPlaylist;
    private String title;
    private String album;
    private int duration;

    //Constructors
    public Playlist() {}

    public Playlist(String idPlaylist, String title, String album, int duration) {
        this.idPlaylist = idPlaylist;
        this.title = title;
        this.album = album;
        this.duration = duration;
    }

    //Getters and setters
    public String getIdPlaylist() {
        return idPlaylist;
    }

    public void setIdPlaylist(String idPlaylist) {
        this.idPlaylist = idPlaylist;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
