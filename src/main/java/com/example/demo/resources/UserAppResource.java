package com.example.demo.resources;

import com.example.demo.filter.Logged;
import com.example.demo.persistance.entities.UserApp;
import com.example.demo.persistance.entities.pojos.UserAppPOJO;
import com.example.demo.persistance.services.UserAppService;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Path("/userApp")
public class UserAppResource{

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

    /**
     * create and persist a user app.
     * @param input the fields of the user.
     * @return 200 if create, 4** in other cases.
     */
    @POST
    //@Logged
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(MultipartFormDataInput input){
        try {
            return null;
        } catch(Exception e){
            return Response.status(Response.Status.FORBIDDEN).header("Access-Control-Allow-Origin", "*").entity("Exception").build();
        }
    }
}