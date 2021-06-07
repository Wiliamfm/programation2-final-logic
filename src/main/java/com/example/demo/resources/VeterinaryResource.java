package com.example.demo.resources;

import com.example.demo.persistance.entities.Owner;
import com.example.demo.persistance.entities.Veterinary;
import com.example.demo.persistance.services.VeterinaryService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/veterinary")
public class VeterinaryResource {

    private VeterinaryService veterinaryService;

    public VeterinaryResource(){
        veterinaryService= new VeterinaryService();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Veterinary veterinary) {
        if(veterinaryService.create(veterinary)){
            return Response.status(Response.Status.CREATED).entity(veterinary).build();
        }else
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity(veterinary).build();
    }
}