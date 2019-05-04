package com.guym4c.web.webapps.api;

import javax.ejb.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Singleton
@Path("/student")
@Produces(MediaType.APPLICATION_JSON)
public class StudentBySupervisor {
    
    @GET
    @Path("/{supervisor}")
    public Response get(@PathParam("supervisor") String supervisorId) {
        
    }
   
}
