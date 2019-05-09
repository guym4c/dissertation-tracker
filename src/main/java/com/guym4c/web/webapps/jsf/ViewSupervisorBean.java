package com.guym4c.web.webapps.jsf;

import com.guym4c.web.webapps.ejb.AppUserEJB;
import com.guym4c.web.webapps.ejb.ProjectEJB;
import com.guym4c.web.webapps.ejb.SupervisorEJB;
import com.guym4c.web.webapps.entity.Project;
import com.guym4c.web.webapps.entity.Supervisor;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class ViewSupervisorBean extends AbstractBean {
    
    private Supervisor supervisor;
    
    @EJB
    private SupervisorEJB supervisorBean;
    
    @EJB
    private AppUserEJB appUserBean;
    
    @EJB
    private ProjectEJB projectBean;
    
    @PostConstruct
    public void initialise() {
        this.supervisor = supervisorBean.get(this.request.getParameter("sussexId"));
    }

    public Supervisor getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Supervisor supervisor) {
        this.supervisor = supervisor;
    }
    
    public void markAsAdministrator() {
        appUserBean.markAsAdministrator(supervisor.getAppUser());
    }
    
    public void unselect(Project project) {
        projectBean.reject(project);
    }
}
