package com.guym4c.web.webapps.ejb;

import com.guym4c.web.webapps.entity.AppUser;
import com.guym4c.web.webapps.entity.Event;
import com.guym4c.web.webapps.entity.EventType;

public class AppUserEJB extends AbstractUserEJB {
    
    public void markAsAdministrator(final AppUser user) {
        user.setAdministrator(true);
        
        this.log(new Event(EventType.MARKED_AS_ADMIN) {{
            setTargetUser(user);
        }});
        
        this.em.flush();
    }   
}
