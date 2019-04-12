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


}
