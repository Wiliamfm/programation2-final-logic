package com.example.demo.resources;

import com.example.demo.persistance.entities.Owner;
import com.example.demo.persistance.entities.UserApp;
import com.example.demo.persistance.entities.Veterinary;
import com.example.demo.persistance.entities.pojos.VeterinaryPOJO;
import com.example.demo.persistance.services.UserAppService;
import com.example.demo.persistance.services.VeterinaryService;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;

@Path("/veterinary")
public class VeterinaryResource {

    private VeterinaryService veterinaryService;

    public VeterinaryResource(){
        veterinaryService= new VeterinaryService();
    }

    /**
     * Create veterinary.
     * @param input fields of the veterinary.
     * @return 201 if create, 4** other cases.
     */
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(MultipartFormDataInput input) {
        try {
            Map<String, List<InputPart>> uploadForm= input.getFormDataMap();
            String username= uploadForm.get("form-username").get(0).getBodyAsString();
            String password= uploadForm.get("form-password").get(0).getBodyAsString();
            String email= uploadForm.get("form-email").get(0).getBodyAsString();
            String name= uploadForm.get("form-name").get(0).getBodyAsString();
            String address= uploadForm.get("form-address").get(0).getBodyAsString();
            String neighborhood= uploadForm.get("form-neighborhood").get(0).getBodyAsString();
            Veterinary veterinary= new Veterinary(username, password,email,name,address,neighborhood);
            if(veterinaryService.create(veterinary)){
                VeterinaryPOJO veterinaryPOJO= new VeterinaryPOJO(username, password, email, veterinary.getRole(), name, address, neighborhood);
                return Response.status(Response.Status.CREATED).entity(veterinaryPOJO).header("Access-Control-Allow-Origin", "*").build();
            }else{
                return Response.status(Response.Status.NOT_ACCEPTABLE).entity(false).header("Access-Control-Allow-Origin", "*").build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity("error").header("Access-Control-Allow-Origin", "*").build();
        }
    }


    /**
     * Modify a veterinary.
     * @param input fields to modify.
     * @return true if modify, false other way.
     */
    @POST
    @Path("/modify")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response modify(MultipartFormDataInput input) {
        try {
            Map<String, List<InputPart>> uploadForm= input.getFormDataMap();
            String username= uploadForm.get("form-username").get(0).getBodyAsString();
            String address= uploadForm.get("form-address").get(0).getBodyAsString();
            String neighborhood= uploadForm.get("form-neighborhood").get(0).getBodyAsString();
            if(veterinaryService.modify(username,address,neighborhood)){
                return Response.status(Response.Status.OK).entity(true).header("Access-Control-Allow-Origin", "*").build();
            }else{
                return Response.status(Response.Status.NOT_ACCEPTABLE).entity(false).header("Access-Control-Allow-Origin", "*").build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity("error").header("Access-Control-Allow-Origin", "*").build();
        }
    }
}