package com.example.demo.resources;

import com.example.demo.persistance.services.VisitService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/veterinary/visit")
public class VisitResource {

    private VisitService visitService;

    public VisitResource(){
        visitService= new VisitService();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create() {
        if(true){
            return Response.status(Response.Status.CREATED).entity(null).build();
        }else
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity(null).build();
    }
}