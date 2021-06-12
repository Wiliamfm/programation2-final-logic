package com.example.demo.resources;

import com.example.demo.persistance.entities.UserApp;
import com.example.demo.persistance.entities.pojos.UserAppPOJO;
import com.example.demo.persistance.services.UserAppService;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(MultipartFormDataInput input){
        Map<String, List<InputPart>> uploadForm= input.getFormDataMap();
        try {
            String type= uploadForm.get("case").get(0).getBodyAsString();
            String username= uploadForm.get("form-username").get(0).getBodyAsString();
            String password= uploadForm.get("form-password").get(0).getBodyAsString();
            System.out.println(type+ username+ password+"aaa");
            if(type.equals("create")){
                String email= uploadForm.get("form-email").get(0).getBodyAsString();
                String role= uploadForm.get("form-role").get(0).getBodyAsString();
                UserApp userApp= new UserApp(username, password, email, role);
                userApp.setOwner(null);
                userApp.setOfficial(null);
                userApp.setVeterinary(null);
                if(userAppService.create(userApp)){
                    userAppService.close();
                    UserAppPOJO userAppPOJO= new UserAppPOJO(userApp.getUsername(), userApp.getPassword(), userApp.getEmail(), userApp.getRole());
                    return Response.status(Response.Status.CREATED).header("Access-Control-Allow-Origin", "*").entity(userAppPOJO).build();
                }else{
                    userAppService.close();
                    return Response.status(Response.Status.NOT_ACCEPTABLE).header("Access-Control-Allow-Origin", "*").entity(null).build();
                }
            }else if(type.equals("userLogin")){
                String role= userAppService.validateUser(username, password);
                if(role.equals("no match") || role.equals("not found")){
                    return Response.status(Response.Status.NOT_FOUND).header("Access-Control-Allow-Origin", "*").entity("no found").build();
                }else if(role.equals("error")){
                    return Response.status(Response.Status.NOT_ACCEPTABLE).header("Access-Control-Allow-Origin", "*").entity("error").build();
                }else{
                    return Response.status(Response.Status.FOUND).header("Access-Control-Allow-Origin", "*").entity(role).build();
                }
            }else{
                return Response.status(Response.Status.NOT_ACCEPTABLE).header("Access-Control-Allow-Origin", "*").entity(null).build();
            }
        } catch (IOException e) {
            userAppService.close();
            return Response.status(Response.Status.BAD_REQUEST).header("Access-Control-Allow-Origin", "*").entity(e).build();
        } catch(Exception e){
            userAppService.close();
            return Response.status(Response.Status.FORBIDDEN).header("Access-Control-Allow-Origin", "*").entity(e).build();

        }
    }
}