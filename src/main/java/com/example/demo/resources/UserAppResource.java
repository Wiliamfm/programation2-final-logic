package com.example.demo.resources;

import com.example.demo.persistance.entities.UserApp;
import com.example.demo.persistance.services.UserAppService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/userApp")
public class UserAppResource {

    private UserAppService userAppService;

    public UserAppResource(){
        userAppService= new UserAppService();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listOwners(){
        List<UserApp> userApps= userAppService.listUsers();
        userAppService.close();
        return Response.status(Response.Status.OK).entity(userApps).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(UserApp userApp) {
        if(userAppService.create(userApp)){
            userAppService.close();
            return Response.status(Response.Status.CREATED).entity(userApp).build();
        }else{
            userAppService.close();
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity(userApp).build();
        }
    }
}