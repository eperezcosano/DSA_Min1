package edu.upc.dsa.models;

import java.util.LinkedList;
import java.util.List;

public class Artist {

    //Attributes
    private String idArtist;
    private String name;
    private List<Playlist> playlists;

    //Constructors
    public Artist() {}

    public Artist(String idArtist, String name) {
        this.idArtist = idArtist;
        this.name = name;
        this.playlists = new LinkedList<>();
    }

    //Methods
    public void addPlaylist(Playlist playlist) {
        this.playlists.add(playlist);
    }

    //Getters and setters
    public String getIdArtist() {
        return idArtist;
    }

    public void setIdArtist(String idArtist) {
        this.idArtist = idArtist;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
    }
}
