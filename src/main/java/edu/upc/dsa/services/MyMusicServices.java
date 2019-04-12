package edu.upc.dsa.services;

import edu.upc.dsa.*;
import edu.upc.dsa.exceptions.*;
import edu.upc.dsa.models.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Api(value = "/music", description = "Endpoint to MyMusicServices")
@Path("/music")
public class MyMusicServices {

    private MyMusic myMusic;

    public MyMusicServices() {

        this.myMusic = MyMusicImpl.getInstance();

        if (this.myMusic.numUsers() == 0) {

            this.myMusic.addUser("User1","Ethan", "Perez");
            this.myMusic.addUser("User2","Charlotte", "Mont");

            this.myMusic.addArtist("Artist1", "Madonna");
            this.myMusic.addArtist("Artist2", "Rihanna");
            this.myMusic.addArtist("Artist3", "Beyonce");
        }
    }

    @POST
    @ApiOperation(value = "add artist")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
    })
    @Path("/{idArtist}/{name}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addArtist(@PathParam("idArtist") String idArtist, @PathParam("name") String name) {
        this.myMusic.addArtist(idArtist, name);
        return Response.status(201).build();
    }

    @GET
    @ApiOperation(value = "get list of artists")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response = Artist.class, responseContainer="List")
    })
    @Path("/artists")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getArtists() {
        List<Artist> artists = this.myMusic.getArtists();
        GenericEntity<List<Artist>> entity = new GenericEntity<List<Artist>>(artists) {};
        return Response.status(200).entity(entity).build();
    }

    @POST
    @ApiOperation(value = "add playlist")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "ArtistNotFoundException"),
            @ApiResponse(code = 405, message = "UserNotFoundException")
    })
    @Path("/playlist/{idArtist}/{idUser}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPlaylist(Playlist playlist, @PathParam("idArtist") String idArtist, @PathParam("idUser") String idUser) {
        try {
            this.myMusic.addPlaylist(playlist.getIdPlaylist(), playlist.getTitle(), idArtist, playlist.getAlbum(), playlist.getDuration(), idUser);
            return Response.status(201).build();
        } catch (ArtistNotFoundException e) {
            e.printStackTrace();
            return Response.status(404).build();
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            return Response.status(405).build();
        }
    }

    @PUT
    @ApiOperation(value = "update playlist tittle")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Playlist not found")
    })
    @Path("/{idPlaylist}")
    public Response updateTrack(String tittle, @PathParam("idPlaylist") String idPlaylist) {
        try {
            this.myMusic.setTittle(tittle, idPlaylist);
            return Response.status(201).build();
        } catch (PlaylistNotFoundException e) {
            e.printStackTrace();
            return Response.status(404).build();
        }
    }

    @GET
    @ApiOperation(value = "get list playlist of an user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response = Playlist.class, responseContainer="List"),
            @ApiResponse(code = 404, message = "UserNotFoundException")
    })
    @Path("/{idUser}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlaylistsByUser(@PathParam("idUser") String idUser) {
        try {
            List<Playlist> playlists = this.myMusic.getPlaylist(idUser);
            GenericEntity<List<Playlist>> entity = new GenericEntity<List<Playlist>>(playlists) {};
            return Response.status(200).entity(entity).build();
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            return Response.status(404).build();
        }
    }

}
