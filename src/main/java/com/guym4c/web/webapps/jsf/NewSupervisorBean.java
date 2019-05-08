package com.guym4c.web.webapps.jsf;

import com.guym4c.web.webapps.ejb.SupervisorEJB;
import com.guym4c.web.webapps.entity.Supervisor;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityExistsException;

@Named
@RequestScoped
public class NewSupervisorBean extends NewUserBean {
    
    @EJB
    private SupervisorEJB supervisorBean;
    
    private String department;
    
    private String telephoneNumber;
    
    public void create() throws EntityExistsException, UnsupportedEncodingException, UnsupportedEncodingException, NoSuchAlgorithmException, IOException {
        this.supervisorBean.create(new Supervisor(
            this.constructUser(),
            this.department,
            this.telephoneNumber));
        this.context.redirect("/supervisor/" + this.sussexId);
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }
}
