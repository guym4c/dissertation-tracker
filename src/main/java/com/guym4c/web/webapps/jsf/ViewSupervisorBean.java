package com.guym4c.web.webapps.jsf;

import com.guym4c.web.webapps.ejb.AppUserEJB;
import com.guym4c.web.webapps.ejb.ProjectEJB;
import com.guym4c.web.webapps.ejb.SupervisorEJB;
import com.guym4c.web.webapps.entity.Project;
import com.guym4c.web.webapps.entity.Supervisor;
import java.io.IOException;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class ViewSupervisorBean extends AbstractBean {
    
    private Supervisor supervisor;
    
    @EJB
    private SupervisorEJB supervisorEJB;
    
    @EJB
    private AppUserEJB appUserEJB;
    
    @EJB
    private ProjectEJB projectEJB;
    
    @Inject
    private UserUtilBean userUtilBean;
    
    @PostConstruct
    public void initialise() {
        this.supervisor = supervisorEJB.get(this.request.getParameter("sussexId"));
    }

    public Supervisor getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Supervisor supervisor) {
        this.supervisor = supervisor;
    }
    
    public void markAsAdministrator() {
        appUserEJB.markAsAdministrator(supervisor.getAppUser());
    }
    
    public void unselect(Project project) throws IOException {
        this.reject(project);
    }
    
    public void accept(Project project) throws IOException {
        projectEJB.accept(project);
        this.context.redirect(this.userUtilBean.userHome());
    }
    
    public void reject(Project project) throws IOException {
        projectEJB.reject(project);
        this.context.redirect(this.userUtilBean.userHome());
    }
}
