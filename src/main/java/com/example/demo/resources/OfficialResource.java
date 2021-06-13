package com.example.demo.resources;

import com.example.demo.persistance.entities.Official;
import com.example.demo.persistance.entities.UserApp;
import com.example.demo.persistance.entities.pojos.OfficialPOJO;
import com.example.demo.persistance.services.OfficialService;
import com.example.demo.persistance.services.UserAppService;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;

@Path("/official")
public class OfficialResource {

    private OfficialService officialService;

    public OfficialResource(){
        officialService= new OfficialService();
    }

    @GET
    @Produces("text/plain")
    public String hello() {
        return "Hello, World!";
    }

    /**
     * create official.
     * @param input fields of the official.
     * @return 201 if created, 4** other way.
     */
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(MultipartFormDataInput input){
        try{
            Map<String, List<InputPart>> uploadForm= input.getFormDataMap();
            String username= uploadForm.get("form-username").get(0).getBodyAsString();
            String password= uploadForm.get("form-password").get(0).getBodyAsString();
            String email= uploadForm.get("form-email").get(0).getBodyAsString();
            String name= uploadForm.get("form-name").get(0).getBodyAsString();
            Official official= new Official(username, password, email, name);
            if(officialService.create(official)){
                OfficialPOJO officialPOJO= new OfficialPOJO(username, password, email, official.getRole(), name);
                return Response.status(Response.Status.CREATED).entity(officialPOJO).header("Access-Control-Allow-Origin", "*").build();
            }else{
                return Response.status(Response.Status.NOT_ACCEPTABLE).entity(false).header("Access-Control-Allow-Origin", "*").build();
            }
        }catch(Exception e){
            return Response.status(Response.Status.BAD_REQUEST).entity("error").header("Access-Control-Allow-Origin", "*").build();
        }
    }
}