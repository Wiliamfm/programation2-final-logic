package com.example.demo.resources;

import javax.ws.rs.*;

@Path("/hello-world")
public class OfficialResource {
    @GET
    @Produces("text/plain")
    public String hello() {
        return "Hello, World!";
    }
}