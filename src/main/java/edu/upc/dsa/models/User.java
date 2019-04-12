package edu.upc.dsa.models;

import java.util.LinkedList;
import java.util.List;

public class User {

    //Attributes
    private String idUser;
    private String name;
    private String surname;
    private List<Playlist> playlists;

    //Constructors
    public User() {

    }

    public User(String idUser, String name, String surname) {
        this.idUser = idUser;
        this.name = name;
        this.surname = surname;
        this.playlists = new LinkedList<>();
    }

    //Methods
    @Override
    public String toString() {
        String string =
                "[id: " + idUser + ", " +
                        "Name: " + name + ", " +
                        "Surname: " + surname + " , " +
                        "Playlists: ";
        for (Playlist playlist : playlists) {
            string += playlist.getTitle() + ", ";
        }
        string += "]";
        return string;
    }

    public void addPlaylist(Playlist playlist) {
        this.playlists.add(playlist);
    }

    //Getters and setters
    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
    }

}
