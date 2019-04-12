package edu.upc.dsa;

import edu.upc.dsa.exceptions.ArtistNotFoundException;
import edu.upc.dsa.exceptions.PlaylistNotFoundException;
import edu.upc.dsa.exceptions.UserNotFoundException;
import edu.upc.dsa.models.Artist;
import edu.upc.dsa.models.Playlist;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MyMusicTest {

    private MyMusic myMusic;

    @Before
    public void setUp() {
        this.myMusic = MyMusicImpl.getInstance();

        Assert.assertEquals(0, this.myMusic.numUsers());

        this.myMusic.addUser("User1","Ethan", "Perez");
        this.myMusic.addUser("User2","Charlotte", "Mont");

        Assert.assertEquals(2, this.myMusic.numUsers());
        Assert.assertEquals(0, this.myMusic.numArtist());

        this.myMusic.addArtist("Artist1", "Madonna");
        this.myMusic.addArtist("Artist2", "Rihanna");
        this.myMusic.addArtist("Artist3", "Beyonce");

        Assert.assertEquals(3, this.myMusic.numArtist());
    }

    @After
    public void tearDown() {
        this.myMusic.clear();

        Assert.assertEquals(0, this.myMusic.numUsers());
        Assert.assertEquals(0, this.myMusic.numArtist());
        Assert.assertEquals(0, this.myMusic.numPlaylists());
    }

    @Test
    public void testAddPlaylist() throws ArtistNotFoundException, UserNotFoundException {

        Assert.assertEquals(0, this.myMusic.numPlaylists());

        this.myMusic.addPlaylist("Playlist1", "MyFirstPlaylist", "Artist1", "Time", 60, "User1");

        Assert.assertEquals(1, this.myMusic.numPlaylists());
    }

    @Test(expected = ArtistNotFoundException.class)
    public void testAddPlaylistArtistNotFound() throws ArtistNotFoundException, UserNotFoundException {

        Assert.assertEquals(0, this.myMusic.numPlaylists());

        this.myMusic.addPlaylist("Playlist1", "MyFirstPlaylist", "ArtistX", "Time", 60, "User1");

        Assert.assertEquals(0, this.myMusic.numPlaylists());
    }

    @Test(expected = UserNotFoundException.class)
    public void testAddPlaylistUserNotFound() throws ArtistNotFoundException, UserNotFoundException {

        Assert.assertEquals(0, this.myMusic.numPlaylists());

        this.myMusic.addPlaylist("Playlist1", "MyFirstPlaylist", "Artist1", "Time", 60, "UserX");

        Assert.assertEquals(0, this.myMusic.numPlaylists());
    }

    @Test
    public void testSetTittle() throws PlaylistNotFoundException, ArtistNotFoundException, UserNotFoundException {

        Assert.assertEquals(0, this.myMusic.numPlaylists());

        this.myMusic.addPlaylist("Playlist1", "MyFirstPlaylist", "Artist1", "Time", 60, "User1");

        Assert.assertEquals(1, this.myMusic.numPlaylists());

        Assert.assertEquals("MyFirstPlaylist", this.myMusic.getPlaylistById("Playlist1").getTitle());

        this.myMusic.setTittle("NewTitle", "Playlist1");

        Assert.assertEquals("NewTitle", this.myMusic.getPlaylistById("Playlist1").getTitle());
    }

    @Test(expected = PlaylistNotFoundException.class)
    public void testSetTittlePlaylistNotFound() throws PlaylistNotFoundException, ArtistNotFoundException, UserNotFoundException {

        Assert.assertEquals(0, this.myMusic.numPlaylists());

        this.myMusic.addPlaylist("Playlist1", "MyFirstPlaylist", "Artist1", "Time", 60, "User1");

        Assert.assertEquals(1, this.myMusic.numPlaylists());

        Assert.assertEquals("MyFirstPlaylist", this.myMusic.getPlaylistById("Playlist1").getTitle());

        this.myMusic.setTittle("NewTitle", "PlaylistX");

        Assert.assertEquals("MyFirstPlaylist", this.myMusic.getPlaylistById("Playlist1").getTitle());

    }

    @Test
    public void testGetPlaylist() throws UserNotFoundException, ArtistNotFoundException {

        Assert.assertEquals(0, this.myMusic.numPlaylists());

        this.myMusic.addPlaylist("Playlist1", "MyFirstPlaylist", "Artist1", "Time", 60, "User1");

        Assert.assertEquals(1, this.myMusic.numPlaylists());

        Assert.assertEquals("MyFirstPlaylist", this.myMusic.getPlaylist("User1").get(0).getTitle());
        Assert.assertEquals("Time", this.myMusic.getPlaylist("User1").get(0).getAlbum());
        Assert.assertEquals(60, this.myMusic.getPlaylist("User1").get(0).getDuration());
    }

    @Test(expected = UserNotFoundException.class)
    public void testGetPlaylistUserNotFound() throws UserNotFoundException, ArtistNotFoundException {

        Assert.assertEquals(0, this.myMusic.numPlaylists());

        this.myMusic.addPlaylist("Playlist1", "MyFirstPlaylist", "Artist1", "Time", 60, "User1");

        Assert.assertEquals(1, this.myMusic.numPlaylists());

        Assert.assertEquals("MyFirstPlaylist", this.myMusic.getPlaylist("UserX").get(0).getTitle());
        Assert.assertEquals("Time", this.myMusic.getPlaylist("UserX").get(0).getAlbum());
        Assert.assertEquals(60, this.myMusic.getPlaylist("UserX").get(0).getDuration());
    }

}
