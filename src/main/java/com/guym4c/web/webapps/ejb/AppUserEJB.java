package com.guym4c.web.webapps.ejb;

import com.guym4c.web.webapps.entity.AppUser;

public class AppUserEJB extends AbstractUserEJB {
    
    public void markAsAdministrator(AppUser user) {
        user.setAdministrator(true);
    }   
}
