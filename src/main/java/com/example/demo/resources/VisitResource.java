package com.example.demo.resources;

import com.example.demo.persistance.entities.Visit;
import com.example.demo.persistance.entities.pojos.VisitPOJO;
import com.example.demo.persistance.services.VisitService;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Path("/veterinary/{vetId}/visits")
public class VisitResource {

    private VisitService visitService;

    public VisitResource(){
        visitService= new VisitService();
    }

    /**
     * list all pets in the dataBase
     * @return a list with the pet in Json format
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response list(@PathParam("vetId") String username){
        try{
            List<VisitPOJO> visitPOJOS= visitService.list(username);
            if(visitPOJOS!=null){
                return Response.status(Response.Status.OK).entity(visitPOJOS).header("Access-Control-Allow-Origin", "*").build();
            }else {
                return Response.status(Response.Status.NOT_FOUND).entity(false).header("Access-Control-Allow-Origin", "*").build();
            }
        }catch (Exception e){
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity("error").header("Access-Control-Allow-Origin", "*").build();
        }
    }

    @POST
    @Path("/{petId}")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@PathParam("vetId") String vetId,@PathParam("petId") String petId, MultipartFormDataInput input) {
        try{
            Map<String, List<InputPart>> uploadForm= input.getFormDataMap();
            int pet= Integer.parseInt(petId);
            String type= uploadForm.get("form-type").get(0).getBodyAsString();
            String description= uploadForm.get("form-description").get(0).getBodyAsString();
            Visit visit= new Visit(new Date().toString(), type, description);
            if(visitService.create(visit, pet, vetId)){
                VisitPOJO visitPOJO= new VisitPOJO(visit.getId(), visit.getCreatedAt(), type, description, vetId, pet);
                return Response.status(Response.Status.CREATED).entity(visitPOJO).header("Access-Control-Allow-Origin", "*").build();
            }else {
                return Response.status(Response.Status.NOT_ACCEPTABLE).entity(false).header("Access-Control-Allow-Origin", "*").build();
            }
        }catch (Exception e){
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity("error").header("Access-Control-Allow-Origin", "*").build();
        }
    }
}