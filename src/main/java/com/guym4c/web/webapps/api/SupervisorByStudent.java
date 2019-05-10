package com.guym4c.web.webapps.api;

import com.guym4c.web.webapps.entity.Supervisor;
import java.util.ArrayList;
import java.util.List;
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
        List<Supervisor> supervisors = new ArrayList<>();
        if (studentId.equals("all")) {
            supervisors.addAll(this.retrieve("Supervisor.all", Supervisor.class));
        } else {
            supervisors.addAll(this.retrieve("Supervisor.byStudent", Supervisor.class, "studentId", studentId));
        }
        
        List<SupervisorOutputType> results = new ArrayList<>();
        for (Supervisor supervisor : supervisors) {
            results.add(new SupervisorOutputType(supervisor));
        }
        return this.createResponse(results);
    }
}
