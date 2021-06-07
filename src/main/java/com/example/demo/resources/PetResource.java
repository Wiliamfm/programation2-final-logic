package com.example.demo.resources;

import com.example.demo.persistance.entities.Owner;
import com.example.demo.persistance.entities.Pet;
import com.example.demo.persistance.services.PetService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/owner/pet")
public class PetResource {

    private PetService petService;

    public PetResource(){
        petService= new PetService();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Pet pet) {
        if(petService.create(pet)){
            return Response.status(Response.Status.CREATED).entity(pet).build();
        }else
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity(pet).build();
    }
}