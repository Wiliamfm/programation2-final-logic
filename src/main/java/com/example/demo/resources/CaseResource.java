package com.example.demo.resources;

import com.example.demo.persistance.entities.Case;
import com.example.demo.persistance.services.CaseService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/owner/pet/case")
public class CaseResource {

    private CaseService caseService;

    public CaseResource(){
        caseService= new CaseService();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Case cas) {
        if(caseService.create(cas)){
            return Response.status(Response.Status.CREATED).entity(cas).build();
        }else
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity(cas).build();
    }
}