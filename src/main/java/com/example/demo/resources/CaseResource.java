package com.example.demo.resources;

import com.example.demo.persistance.entities.PetCase;
import com.example.demo.persistance.entities.pojos.CasePOJO;
import com.example.demo.persistance.services.CaseService;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Path("/owner/{username}/pets/{petId}/case")
public class CaseResource {

    private CaseService caseService;

    public CaseResource(){
        caseService= new CaseService();
    }

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@PathParam("petId") String petId, MultipartFormDataInput input) {
        try{
            Map<String, List<InputPart>> uploadForm= input.getFormDataMap();
            int pet= Integer.parseInt(petId);
            String type= uploadForm.get("form-type").get(0).getBodyAsString();
            String description= uploadForm.get("form-description").get(0).getBodyAsString();
            PetCase petCase= new PetCase(new Date().toString(), type, description);
            if(caseService.create(pet, petCase)){
                CasePOJO casePOJO= new CasePOJO(petCase.getId(), petCase.getCreatedAt(), petCase.getType(), petCase.getDescription(), pet);
                return Response.status(Response.Status.CREATED).entity(casePOJO).header("Access-Control-Allow-Origin", "*").build();

            }else {
                return Response.status(Response.Status.NOT_ACCEPTABLE).entity(false).header("Access-Control-Allow-Origin", "*").build();
            }
        }catch (Exception e){
            e.printStackTrace();
            caseService.close();
            return Response.status(Response.Status.BAD_REQUEST).entity("error").header("Access-Control-Allow-Origin", "*").build();
        }
    }
}