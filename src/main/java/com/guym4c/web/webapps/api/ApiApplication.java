package com.guym4c.web.webapps.api;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/api")
public class ApiApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> classes = new HashSet<Class<?>>() {{
            add(ProjectsBySupervisor.class);
            add(StudentsBySupervisor.class);
            add(SupervisorByStudent.class);
        }};
        return classes;
    }
}
