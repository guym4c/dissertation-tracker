package com.guym4c.web.webapps.jsf;

import com.guym4c.web.webapps.ejb.StartupEJB;
import com.guym4c.web.webapps.entity.AppUser;
import com.guym4c.web.webapps.entity.AppUserGroup;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class UserUtilBean extends AbstractBean {
    
    public String userHome() {
        
        if (!this.session.loggedIn()) {
            return "/login";
        } else if (this.session.getUser().isSupervisor()) {
            return "/supervisors/" + this.session.getUser().getSussexId();
        } else {
            return "/home/student";
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
    
}
