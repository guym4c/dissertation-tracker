package com.guym4c.web.webapps.jsf;

import com.guym4c.web.webapps.ejb.SupervisorEJB;
import com.guym4c.web.webapps.entity.Supervisor;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class ViewAllSupervisorsBean extends AbstractBean {
    
    @EJB
    private SupervisorEJB supervisorBean;
    
    private List<Supervisor> supervisors;
    
    @PostConstruct
    public void initialise() {
        this.supervisors = this.supervisorBean.getAll();
    }

    public List<Supervisor> getSupervisors() {
        return supervisors;
    }

    public void setSupervisors(List<Supervisor> supervisors) {
        this.supervisors = supervisors;
    }
}
