package com.guym4c.web.webapps.jsf;

import com.guym4c.web.webapps.ejb.StartupEJB;
import com.guym4c.web.webapps.ejb.StudentEJB;
import com.guym4c.web.webapps.entity.AppUser;
import com.guym4c.web.webapps.entity.AppUserGroup;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class UserUtilBean extends AbstractBean {
    
    @EJB
    private StudentEJB studentBean;
    
    public String userHome() {
        
        if (!this.session.loggedIn()) {
            return "/login";
        } else if (this.session.getUser().isSupervisor()) {
            return "/supervisor/" + this.session.getUser().getSussexId();
        } else {
            return "/projects";
        }
    }
    
    public boolean isAdmin() {
        if (!this.session.loggedIn()) {
            return false;
        }
        return this.isAdmin(this.session.getUser());
        
    }
    
    public boolean isAdmin(AppUser user) {
        boolean isAdmin = false;
        for (AppUserGroup group : user.getGroups()) {
            if (group.getGroup().equals(AppUserGroup.ADMINISTRATOR)) {
                isAdmin = true; 
            }
        }
        return isAdmin;
    }
    
    public String defaultAdminUsername() {
        return StartupEJB.DEFAULT_ADMIN_USERNAME;
    }
    
    public boolean hasProject() {
        if (!this.session.loggedIn()) {
            return false;
        }
        if (this.session.getUser().isSupervisor()) {
            return true;
        }
        return this.studentBean.get(
                this.session.getUser().getSussexId())
                .getProject() != null;
    }
    
    public boolean projectNotSelected() {
        if (!this.session.loggedIn()) {
            return true;
        }
        if (this.session.getUser().isSupervisor()) {
            return true;
        }
        return this.studentBean.get(
                this.session.getUser().getSussexId())
                .getProject() == null;
    }
    
}
