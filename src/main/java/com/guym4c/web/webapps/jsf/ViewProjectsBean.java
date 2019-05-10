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
    private ProjectEJB projectBean;
    
    @EJB
    private StudentEJB studentBean;
    
    private List<Project> projects;
    
    @PostConstruct
    public void initialise() {
        this.projects = projectBean.getAllUnassigned();
    }
    
    public void select(Project project) throws IOException {
        this.projectBean.select(
                this.studentBean.get(this.session.getUser().getSussexId()), project);
        this.context.redirect("/projects");
    }

    public List<Project> getProjects() {
        return projects;
    }
}
