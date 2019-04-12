package edu.upc.dsa;

import edu.upc.dsa.exceptions.ArtistNotFoundException;
import edu.upc.dsa.exceptions.PlaylistNotFoundException;
import edu.upc.dsa.exceptions.UserNotFoundException;
import edu.upc.dsa.models.Artist;
import edu.upc.dsa.models.Playlist;
import edu.upc.dsa.models.User;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class MyMusicImpl implements MyMusic {

    //Logger
    private final static Logger log = Logger.getLogger(MyMusicImpl.class.getName());

    //Facade
    private static MyMusicImpl instance;
    private HashMap<String, User> users;
    private List<Playlist> playlists;
    private List<Artist> artists;

    //Private Constructor
    private MyMusicImpl() {
        this.users = new HashMap<>();
        this.playlists = new ArrayList<>();
        this.artists = new LinkedList<>();
    }

    //getInstance Method
    public static MyMusic getInstance() {
        if (instance == null) instance = new MyMusicImpl();
        return instance;
    }

    @Override
    public void addUser(String idUser, String name, String surname) {
        User user = new User(idUser, name, surname);
        this.users.put(user.getIdUser(), user);
        log.info("User " + user.getName() + " added");
    }

    @Override
    public void addArtist(String idArtist, String name) {
        Artist artist = new Artist(idArtist, name);
        this.artists.add(artist);
        log.info("Artist " + artist.getName() + " added");
    }

    @Override
    public void addPlaylist(String idPlaylist, String tittle, String idArtist, String album, int duration, String idUser) throws ArtistNotFoundException, UserNotFoundException {

        User user = this.users.get(idUser);
        if (user == null) {
            log.error("User not found");
            throw new UserNotFoundException();
        }
        log.info("User is " + user.getName());

        int position = this.getArtistById(idArtist);
        Artist artist = this.artists.get(position);
        log.info("Artist is " + artist.getName());

        Playlist playlist = new Playlist(idPlaylist, tittle, album, duration);
        log.info("Playlist created");

        artist.addPlaylist(playlist);
        user.addPlaylist(playlist);
        this.playlists.add(playlist);

        log.info("Playlist added");
    }

    @Override
    public void setTittle(String tittle, String idPlaylist) throws PlaylistNotFoundException {
        Playlist playlist = this.getPlaylistById(idPlaylist);
        playlist.setTitle(tittle);
        log.info("Tittle set to: " + tittle);
    }

    @Override
    public List<Artist> getArtists() {
        return this.artists;
    }

    @Override
    public List<Playlist> getPlaylist(String idUser) throws UserNotFoundException {
        User user = this.users.get(idUser);
        if (user == null) {
            log.error("User not found");
            throw new UserNotFoundException();
        }
        log.info("User is " + user.getName());

        return user.getPlaylists();
    }

    @Override
    public int numUsers() {
        return this.users.size();
    }

    @Override
    public int numArtist() {
        return this.artists.size();
    }

    @Override
    public int numPlaylists() {
        return this.playlists.size();
    }

    @Override
    public void clear() {
        this.users = new HashMap<>();
        this.playlists = new ArrayList<>();
        this.artists = new LinkedList<>();
    }


    //Methods
    private int getArtistById(String idArtist) throws ArtistNotFoundException {
        for (int i = 0; i < this.numArtist(); i++) {
            if (idArtist.equals(this.artists.get(i).getIdArtist())) return i;
        }
        log.info("Artist not found");
        throw new ArtistNotFoundException();
    }

    public Playlist getPlaylistById(String idPlaylist) throws PlaylistNotFoundException {
        for (int i = 0; i < this.numPlaylists(); i++) {
            if (idPlaylist.equals(this.playlists.get(i).getIdPlaylist())) return this.playlists.get(i);
        }
        log.info("Playlist not found");
        throw new PlaylistNotFoundException();
    }
}
