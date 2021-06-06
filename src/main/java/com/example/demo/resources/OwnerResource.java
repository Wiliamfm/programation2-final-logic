package com.example.demo.resources;

import com.example.demo.persistance.entities.Owner;
import com.example.demo.persistance.services.OwnerService;

import javax.ws.rs.*;

@Path("/owner")
public class OwnerResource {

    private OwnerService ownerService;

    public OwnerResource(){
        ownerService= new OwnerService();
    }

    @GET
    @Produces("text/plain")
    public String hello() {
        Owner owner= new Owner(null, 0, "asdf", "ne", "nei");
        if(ownerService.create(owner)){
            return "Hello, World!";
        }else
            return "a";
    }
}