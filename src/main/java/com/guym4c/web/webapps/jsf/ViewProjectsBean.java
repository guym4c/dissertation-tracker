package com.guym4c.web.webapps.jsf;

import com.guym4c.web.webapps.ejb.ProjectEJB;
import com.guym4c.web.webapps.ejb.StudentEJB;
import com.guym4c.web.webapps.entity.Project;
import java.io.IOException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class ViewProjectsBean extends AbstractBean {
    
    @EJB
    private ProjectEJB projectEJB;
    
    @EJB
    private StudentEJB studentEJB;
    
    private List<Project> projects;
    
    @PostConstruct
    public void initialise() {
        this.projects = projectEJB.getAllUnassigned();
    }
    
    public void select(Project project) throws IOException {
        this.projectEJB.select(
                this.studentEJB.get(this.session.getUser().getSussexId()), project);
        this.context.redirect("/projects");
    }

    public List<Project> getProjects() {
        return projects;
    }
}
