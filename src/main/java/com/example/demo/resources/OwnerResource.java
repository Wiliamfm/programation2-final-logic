package com.example.demo.resources;

import com.example.demo.filter.Logged;
import com.example.demo.persistance.entities.Owner;
import com.example.demo.persistance.entities.pojos.OwnerPOJO;
import com.example.demo.persistance.services.OwnerService;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Path("/owner")
public class OwnerResource {

    private OwnerService ownerService;

    public OwnerResource(){
        ownerService= new OwnerService();
    }

    /**
     * Get basic information about an specific owner.
     * @param userName the userName of the UserApp.
     * @return 200 if owner found, some 4** status in other cases.
     */
    @GET
    @Path("/{username}")
    //@Logged
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOwner(@PathParam("username") String userName){//@HeaderParam("role") String role){
        try{
            OwnerPOJO ownerPOJO= ownerService.getOwner(userName);
            if(ownerPOJO!=null){
                return Response.status(Response.Status.OK).entity(ownerPOJO).header("Access-Control-Allow-Origin", "*").build();
            }else{
                return Response.status(Response.Status.NOT_FOUND).entity(false).header("Access-Control-Allow-Origin", "*").build();
            }
        }catch (Exception e){
            return Response.status(Response.Status.BAD_REQUEST).entity("Error").header("Access-Control-Allow-Origin", "*").build();
        }
    }

    /**
     * Create a owner and persist it.
     * @param input the form submited by the client.
     * @return status 201 if created, 4** in other cases.
     */
    @POST
    //@Logged
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(MultipartFormDataInput input){//, @HeaderParam("role") String role) {
        if(true){//role.equals("owner")){
            try{
                Map<String, List<InputPart>> uploadForm= input.getFormDataMap();
                String username= uploadForm.get("form-username").get(0).getBodyAsString();
                String password= uploadForm.get("form-password").get(0).getBodyAsString();
                String email= uploadForm.get("form-email").get(0).getBodyAsString();
                String name= uploadForm.get("form-name").get(0).getBodyAsString();
                String address= uploadForm.get("form-address").get(0).getBodyAsString();
                String neighborhood= uploadForm.get("form-neighborhood").get(0).getBodyAsString();
                Owner owner= new Owner(username, password, email, name, address, neighborhood);
                if(ownerService.create(owner)){
                    OwnerPOJO ownerPOJO= new OwnerPOJO(username, password, email, owner.getRole(), owner.getPersonId(), name, address, neighborhood);
                    return Response.status(Response.Status.CREATED).entity(ownerPOJO).header("Access-Control-Allow-Origin", "*").build();
                }else{
                    return Response.status(Response.Status.NOT_ACCEPTABLE).entity(false).header("Access-Control-Allow-Origin", "*").build();
                }
            }catch(IOException e){
                return Response.status(Response.Status.BAD_REQUEST).entity("Error in the form").header("Access-Control-Allow-Origin", "*").build();
            }catch(Exception e){
                return Response.status(Response.Status.UNAUTHORIZED).entity("Internal Error").header("Access-Control-Allow-Origin", "*").build();
            }
        }else{
            //return Response.status(Response.Status.FORBIDDEN).entity(role).build();
            return Response.status(Response.Status.FORBIDDEN).entity(null).build();
        }
    }

    /**
     * Modify an owner given it is id.
     * @param input the form.
     * @return 200 status if modify successful, 4** other case.
     */
    @POST
    @Path("/{username}")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response modify(@PathParam("username") String username, MultipartFormDataInput input){
        try{
            Map<String, List<InputPart>> uploadForm= input.getFormDataMap();
            String address= uploadForm.get("form-address").get(0).getBodyAsString();
            String neighborhood= uploadForm.get("form-neighborhood").get(0).getBodyAsString();
            if(ownerService.modify(username, address, neighborhood)){
                return Response.status(Response.Status.OK).entity(true).header("Access-Control-Allow-Origin", "*").build();
            }else{
                return Response.status(Response.Status.NOT_ACCEPTABLE).entity(false).header("Access-Control-Allow-Origin", "*").build();
            }
        }catch (Exception e){
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity("error").header("Access-Control-Allow-Origin", "*").build();
        }
    }
}