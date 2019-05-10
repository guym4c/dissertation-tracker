package com.guym4c.web.webapps.api;

import com.guym4c.web.webapps.entity.Project;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Singleton
@Path("/project")
@Produces(MediaType.APPLICATION_JSON)
public class ProjectsBySupervisor extends ApiResolver {
    
    @GET
    @Path("/{supervisor}")
    @Override
    public Response get(@PathParam("supervisor") String supervisorId) {
        List<Project> projects = new ArrayList<>();
        if (supervisorId.equals("all")) {
            projects.addAll(this.retrieve("Project.all", Project.class));
        } else {
            projects.addAll(this.retrieve("Project.bySupervisor", Project.class, "supervisorId", supervisorId));
        }
        
        List<ProjectOutputType> results = new ArrayList<>();
        for (Project project : projects) {
            results.add(new ProjectOutputType(project));
        }
        return Response.ok(results).build();
    }
}
