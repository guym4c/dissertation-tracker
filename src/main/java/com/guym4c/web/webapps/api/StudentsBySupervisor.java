package com.guym4c.web.webapps.api;

import com.guym4c.web.webapps.entity.Student;
import java.util.ArrayList;
import java.util.List;
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
        List<Student> students = new ArrayList<>();
        if (supervisorId.equals("all")) {
            students.addAll(this.retrieve("Student.all", Student.class));
        } else {
            students.addAll(this.retrieve("Student.bySupervisor", Student.class, "supervisorId", supervisorId));
        }
        
        List<StudentOutputType> results = new ArrayList<>();
        for (Student student : students) {
            results.add(new StudentOutputType(student));
        }
        return this.createResponse(results);
    }
}
