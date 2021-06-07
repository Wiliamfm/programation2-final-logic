package com.example.demo.resources;

import com.example.demo.persistance.entities.Owner;
import com.example.demo.persistance.entities.pojos.OwnerPOJO;
import com.example.demo.persistance.services.OwnerService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/userApp/owner")
public class OwnerResource {

    private OwnerService ownerService;

    public OwnerResource(){
        ownerService= new OwnerService();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listOwners(){
        List<OwnerPOJO> owners= ownerService.listOwners();
        ownerService.close();
        if(owners!=null){
            return Response.status(Response.Status.OK).entity(owners).build();
        }else{
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Owner owner) {
        if(ownerService.create(owner)){
            ownerService.close();
            return Response.status(Response.Status.CREATED).entity(owner).build();
        }else{
            ownerService.close();
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity(owner).build();
        }
    }
}