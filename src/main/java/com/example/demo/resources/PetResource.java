package com.example.demo.resources;

import com.example.demo.persistance.entities.Pet;
import com.example.demo.persistance.entities.pojos.PetPOJO;
import com.example.demo.persistance.services.OwnerService;
import com.example.demo.persistance.services.PetService;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;

@Path("/owner/{userName}/pets")
public class PetResource {

    private PetService petService;

    public PetResource(){
        petService= new PetService();
    }

    /**
     * list all pets in the dataBase
     * @return a list with the pet in Json format
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response list(@PathParam("userName") String username){
        try{
            List<PetPOJO> petPOJOS= petService.list(username);
            if(petPOJOS!=null){
                return Response.status(Response.Status.OK).entity(petPOJOS).header("Access-Control-Allow-Origin", "*").build();
            }else {
                return Response.status(Response.Status.NOT_FOUND).entity(false).header("Access-Control-Allow-Origin", "*").build();
            }
        }catch (Exception e){
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity("error").header("Access-Control-Allow-Origin", "*").build();
        }
    }

    /**
     * persist a pate.
     * @param input the fields of the pet entity.
     * @return 201 if create, 4** in other cases.
     */
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@PathParam("userName") String ownerId, MultipartFormDataInput input) {
        try{
            Map<String, List<InputPart>> uploadForm= input.getFormDataMap();
            String username= ownerId;
            String microship= uploadForm.get("form-microship").get(0).getBodyAsString();
            String name= uploadForm.get("form-name").get(0).getBodyAsString();
            String specie= uploadForm.get("form-specie").get(0).getBodyAsString();
            String race= uploadForm.get("form-race").get(0).getBodyAsString();
            String size= uploadForm.get("form-size").get(0).getBodyAsString();
            String sex= uploadForm.get("form-sex").get(0).getBodyAsString();
            String picture= uploadForm.get("form-picture").get(0).getBodyAsString();
            if(microship.equals("")){
                microship=null;
            }
            Pet pet= new Pet(microship, name, specie, race, size, sex, picture);
            if(petService.create(username, pet)){
                PetPOJO petPOJO= new PetPOJO(pet.getId(), microship, name, specie, race, size, sex, picture, username);
                return Response.status(Response.Status.CREATED).entity(petPOJO).header("Access-Control-Allow-Origin", "*").build();
            }else{
                return Response.status(Response.Status.NOT_ACCEPTABLE).entity(false).header("Access-Control-Allow-Origin", "*").build();
            }
        }catch (Exception e){
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity("error").header("Access-Control-Allow-Origin", "*").build();
        }
    }
}