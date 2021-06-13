package com.example.demo.resources;

import com.example.demo.filter.Logged;
import com.example.demo.persistance.entities.UserApp;
import com.example.demo.persistance.entities.pojos.CasePOJO;
import com.example.demo.persistance.entities.pojos.OwnerPOJO;
import com.example.demo.persistance.entities.pojos.PetPOJO;
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
    @Path("/pets")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listPets(){
        try{
            List<PetPOJO> petPOJOS= userAppService.getPets();
            if(petPOJOS!=null){
                return Response.status(Response.Status.OK).header("Access-Control-Allow-Origin", "*").entity(petPOJOS).build();
            }else{
                return Response.status(Response.Status.NOT_ACCEPTABLE).header("Access-Control-Allow-Origin", "*").entity(false).build();
            }
        }catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).header("Access-Control-Allow-Origin", "*").entity("error").build();
        }
    }

    @GET
    @Path("/owners")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listOwners(){
        try{
            List<OwnerPOJO> ownerPOJOS= userAppService.getOwners();
            if(ownerPOJOS!=null){
                return Response.status(Response.Status.OK).header("Access-Control-Allow-Origin", "*").entity(ownerPOJOS).build();
            }else{
                return Response.status(Response.Status.NOT_ACCEPTABLE).header("Access-Control-Allow-Origin", "*").entity(false).build();
            }
        }catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).header("Access-Control-Allow-Origin", "*").entity("error").build();
        }
    }

    @GET
    @Path("/cases")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listCases(){
        try{
            List<CasePOJO> casePOJOS= userAppService.getCases();
            if(casePOJOS!=null){
                return Response.status(Response.Status.OK).header("Access-Control-Allow-Origin", "*").entity(casePOJOS).build();
            }else{
                return Response.status(Response.Status.NOT_ACCEPTABLE).header("Access-Control-Allow-Origin", "*").entity(false).build();
            }
        }catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).header("Access-Control-Allow-Origin", "*").entity("error").build();
        }
    }

    /**
     * validate a user app.
     * @param input the fields of the user.
     * @return 200 if validated, 4** in other cases.
     */
    @POST
    //@Logged
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response validateLogin(MultipartFormDataInput input){
        try {
            Map<String, List<InputPart>> uploadForm= input.getFormDataMap();
            String username= uploadForm.get("form-username").get(0).getBodyAsString();
            String password= uploadForm.get("form-password").get(0).getBodyAsString();
            UserApp userApp= userAppService.validateUser(username, password);
            if(userApp!=null){
                UserAppPOJO userAppPOJO= new UserAppPOJO(userApp.getUsername(), userApp.getPassword(), userApp.getEmail(), userApp.getRole());
                return Response.status(Response.Status.OK).header("Access-Control-Allow-Origin", "*").entity(userAppPOJO).build();
            }else{
                return Response.status(Response.Status.NOT_FOUND).header("Access-Control-Allow-Origin", "*").entity(false).build();
            }
        } catch(Exception e){
            return Response.status(Response.Status.FORBIDDEN).header("Access-Control-Allow-Origin", "*").entity("error").build();
        }
    }
}