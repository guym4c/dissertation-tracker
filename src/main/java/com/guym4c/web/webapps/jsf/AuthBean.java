package com.guym4c.web.webapps.jsf;

import com.guym4c.web.webapps.ejb.AppUserEJB;
import com.guym4c.web.webapps.ejb.EventEJB;
import com.guym4c.web.webapps.entity.AppUser;
import com.guym4c.web.webapps.entity.Event;
import com.guym4c.web.webapps.entity.EventType;
import java.io.IOException;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Named
@SessionScoped
public class AuthBean implements Serializable {
    
    protected static final long serialVersionUID = 1L;
    
    private AppUser user;
    
    private String userId;
    
    private String password;
    
    @EJB
    private AppUserEJB appUserBean;
    
    @EJB
    private EventEJB eventBean;
    
    public void login() throws IOException {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();
        try {
            request.login(this.userId, this.password);
        } catch (ServletException e) {
            //TODO handle failed login
        }
        
        this.user = appUserBean.get(this.userId, AppUser.class);
        
        eventBean.create(new Event(EventType.LOGIN));
        
        if (this.user.isStudent()) {
            context.redirect("/projects");
        } else {
            context.redirect("/supervisor/" + this.user.getSussexId());
        }
    }
    
    public void logout() throws IOException {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();
        try {
            request.logout();
            this.user = null;
        } catch (ServletException e) {
            //TODO handle failed logout
        }

        context.redirect("/login");
    }
    
    public boolean loggedIn() {
        return this.user != null;
    }
    
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public AppUser getUser() {
        return this.user;
    }
}
