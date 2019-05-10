package com.guym4c.web.webapps.jsf;

import com.guym4c.web.webapps.ejb.StartupEJB;
import com.guym4c.web.webapps.ejb.StudentEJB;
import com.guym4c.web.webapps.entity.AppUser;
import com.guym4c.web.webapps.entity.AppUserGroup;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 * Utility class containing helpful methods on users
 */
@Named
@RequestScoped
public class UserUtilBean extends AbstractBean {
    
    @EJB
    private StudentEJB studentEJB;
    
    /**
     * @return The user's default URI
     */
    public String userHome() {
        
        if (!this.session.loggedIn()) {
            return "/login";
        } else if (this.session.getUser().isSupervisor()) {
            return "/supervisor/" + this.session.getUser().getSussexId();
        } else {
            return "/projects";
        }
    }
    
    /**
     * @return if the logged in user is in the admin group
     */
    public boolean isAdmin() {
        if (!this.session.loggedIn()) {
            return false;
        }
        return this.isAdmin(this.session.getUser());
        
    }
    
    /**
     * @param user
     * @return If $user is in the admin group
     */
    public boolean isAdmin(AppUser user) {
        boolean isAdmin = false;
        for (AppUserGroup group : user.getGroups()) {
            if (group.getGroup().equals(AppUserGroup.ADMINISTRATOR)) {
                isAdmin = true; 
            }
        }
        return isAdmin;
    }
    
    /**
     * @return the name of the default admin created on deploy, as does not comply with Sussex ID regex
     */
    public String defaultAdminUsername() {
        return StartupEJB.DEFAULT_ADMIN_USERNAME;
    }
    
    /**
     * @return True if this user has selected a project or is a supervisor.
     */
    public boolean hasProject() {
        if (!this.session.loggedIn()) {
            return false;
        }
        if (this.session.getUser().isSupervisor()) {
            return true;
        }
        return this.studentEJB.get(
                this.session.getUser().getSussexId())
                .getProject() != null;
    }
    
    /**
     * @return True if this user has not selected a project or is a supervisor.
     */
    public boolean projectNotSelected() {
        if (!this.session.loggedIn()) {
            return true;
        }
        if (this.session.getUser().isSupervisor()) {
            return true;
        }
        return this.studentEJB.get(
                this.session.getUser().getSussexId())
                .getProject() == null;
    }
    
}
