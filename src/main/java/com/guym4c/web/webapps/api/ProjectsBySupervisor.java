package com.guym4c.web.webapps.api;

import com.guym4c.web.webapps.entity.Project;
import javax.ejb.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Singleton
@Path("/project")
@Produces(MediaType.APPLICATION_JSON)
public class ProjectsBySupervisor extends ApiResolver {
    
    @GET
    @Path("/project/{supervisor}")
    @Override
    public Response get(@PathParam("supervisor") String supervisorId) {
        return supervisorId.equals("all") 
            ? this.respond("Project.all", Project.class)
            : this.respond("Project.bySupervisor", "supervisorId", supervisorId, Project.class);
    }
}
