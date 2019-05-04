package com.guym4c.web.webapps.api;

import com.guym4c.web.webapps.entity.Student;
import javax.ejb.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Singleton
@Path("/student")
@Produces(MediaType.APPLICATION_JSON)
public class StudentsBySupervisor extends ApiResolver {
    
    @GET
    @Path("/{supervisor}")
    @Override
    public Response get(@PathParam("supervisor") String supervisorId) {
        return this.respond("Student.bySupervisor", "supervisorId", supervisorId, Student.class);
    }
    
    @GET
    @Path("/all")
    @Override
    public Response getAll() {
        return this.respond("Student.all", Student.class);
    }
}
