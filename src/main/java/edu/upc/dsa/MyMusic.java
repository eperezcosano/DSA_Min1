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
     * @param tittle playlist tittle
     * @param idArtist artist identifier
     * @param album album name
     * @param idUser user identifier
     * @throws UserNotFoundException if user does not exist
     * @throws ArtistNotFoundException if artist does not exist
     */
    void addPlaylist(String idPlaylist, String tittle, String idArtist, String album, int duration, String idUser) throws ArtistNotFoundException, UserNotFoundException;

    /**
     * Set playlist tittle
     * @param tittle playlist tittle
     * @param idPlaylist playlist identifier
     * @throws PlaylistNotFoundException if playlist does not exist
     */
    void setTittle(String tittle, String idPlaylist) throws PlaylistNotFoundException;

    /**
     * List of artists
     * @return list of artists
     */
    List<Artist> getArtists();

    /**
     * List of playlist of an user
     * @param idUser user identifier
     * @return list of playlist
     * @throws UserNotFoundException if user does not exists
     */
    List<Playlist> getPlaylist(String idUser) throws UserNotFoundException;

    /**
     * Get playlist by its id
     * @param idPlaylist playlist identifier
     * @return playlist
     * @throws PlaylistNotFoundException if playlist does not exist
     */
    Playlist getPlaylistById(String idPlaylist) throws PlaylistNotFoundException;

    /**
     * Get the number of users
     * @return number of users
     */
    int numUsers();

    /**
     * Get the number of artists
     * @return number of artists
     */
    int numArtist();

    /**
     * Get the number of playlists
     * @return number of playlists
     */
    int numPlaylists();

    /**
     * Clear data structures
     */
    void clear();
}
