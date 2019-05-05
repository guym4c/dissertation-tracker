package com.guym4c.web.webapps.api;

import com.guym4c.web.webapps.entity.Supervisor;
import javax.ejb.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Singleton
@Path("/supervisor")
@Produces(MediaType.APPLICATION_JSON)
public class SupervisorByStudent extends ApiResolver {
    
    @GET
    @Path("/{student}")
    @Override
    public Response get(@PathParam("student") String studentId) {
        return studentId.equals("all") 
            ? this.respond("Supervisor.all", Supervisor.class)
            : this.respond("Supervisor.byStudent", "studentId", studentId, Supervisor.class);
    }
}
