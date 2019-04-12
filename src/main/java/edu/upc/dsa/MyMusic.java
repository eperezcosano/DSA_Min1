package edu.upc.dsa;

import edu.upc.dsa.exceptions.ArtistNotFoundException;
import edu.upc.dsa.exceptions.PlaylistNotFoundException;
import edu.upc.dsa.exceptions.UserNotFoundException;
import edu.upc.dsa.models.Artist;
import edu.upc.dsa.models.Playlist;

import java.util.List;

public interface MyMusic {

    /**
     * Add a new User
     * @param idUser user identifier
     * @param name name of the user
     * @param surname surname of the user
     */
    void addUser(String idUser, String name, String surname);

    /**
     * Add a new Artist
     * @param idArtist artist identifier
     * @param name name of the artist
     */
    void addArtist(String idArtist, String name);

    /**
     * Add a new play-list
     * @param tittle
     * @param idArtist
     * @param album
     * @param idUser
     */
    void addPlaylist(String idPlaylist, String tittle, String idArtist, String album, int duration, String idUser) throws ArtistNotFoundException, UserNotFoundException;

    /**/
    void setTittle(String tittle, String idPlaylist) throws PlaylistNotFoundException;

    /**
     * List of artists
     * @return list of artists
     */
    List<Artist> getArtists();

    List<Playlist> getPlaylist(String idUser) throws UserNotFoundException;

    Playlist getPlaylistById(String idPlaylist) throws PlaylistNotFoundException;

    int numUsers();
    int numArtist();
    int numPlaylists();
    void clear();
}
