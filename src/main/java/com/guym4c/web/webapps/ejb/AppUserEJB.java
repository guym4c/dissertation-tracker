package com.guym4c.web.webapps.ejb;

import com.guym4c.web.webapps.entity.AppUser;
import com.guym4c.web.webapps.entity.AppUserGroupType;
import com.guym4c.web.webapps.entity.Event;
import com.guym4c.web.webapps.entity.EventType;
import javax.annotation.security.DeclareRoles;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.NOT_SUPPORTED;

@Stateless
@TransactionAttribute(NOT_SUPPORTED)
@DeclareRoles({"administrator"})
public class AppUserEJB extends AbstractUserEJB {
    
    public void markAsAdministrator(final AppUser user) {
        this.addToGroup(AppUserGroupType.ADMIN, user);
        
        this.log(new Event(EventType.MARKED_AS_ADMIN) {{
            setTargetUser(user);
        }});
        
        this.em.flush();
    }   
}
