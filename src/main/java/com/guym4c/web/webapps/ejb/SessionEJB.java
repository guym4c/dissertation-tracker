package com.guym4c.web.webapps.ejb;

import com.guym4c.web.webapps.entity.AppUser;
import javax.ejb.Stateful;

@Stateful
public class SessionEJB {
    
    private AppUser user;
    
    public void login(AppUser user) {
        this.user = user;
    }
    
    public void logout() {
        this.user = null;
    }
    
    public AppUser getUser() {
        return this.user;
    }
    
    public boolean loggedIn() {
        return this.user != null;
    }
}
