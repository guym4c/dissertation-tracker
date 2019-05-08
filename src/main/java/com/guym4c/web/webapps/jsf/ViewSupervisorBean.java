package com.guym4c.web.webapps.jsf;

import com.guym4c.web.webapps.ejb.SupervisorEJB;
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
}
