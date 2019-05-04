package com.guym4c.web.webapps.api;

import javax.ejb.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Singleton
@Path("/supervisor")
@Produces(MediaType.APPLICATION_JSON)
public class SupervisorByStudent {
    
    @GET
    @Path("/{student}")
    public Response get(@PathParam("student") String studentId) {
        
    }
   
}
