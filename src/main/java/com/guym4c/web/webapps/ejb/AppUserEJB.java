package com.guym4c.web.webapps.ejb;

import com.guym4c.web.webapps.entity.AppUser;
import com.guym4c.web.webapps.entity.AppUserGroup;
import com.guym4c.web.webapps.entity.Event;
import com.guym4c.web.webapps.entity.EventType;
import java.util.List;
import javax.annotation.security.DeclareRoles;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.REQUIRED;
import static javax.ejb.TransactionAttributeType.NOT_SUPPORTED;

@Stateless
@DeclareRoles({"administrator"})
public class AppUserEJB extends AbstractUserEJB {
    
    @TransactionAttribute(REQUIRED)
    public void markAsAdministrator(final AppUser user, boolean log) {
        this.addToGroup(AppUserGroup.ADMINISTRATOR, user);
        
        if (log) {
            Event event = new Event(EventType.MARKED_AS_ADMIN);
            event.setTargetUser(user);
            this.log.create(event);
        }
        
        this.em.flush();
    }
    
    public void markAsAdministrator(AppUser user) {
        this.markAsAdministrator(user, true);
    }
    
    @TransactionAttribute(NOT_SUPPORTED)
    public List<AppUser> getAll(String group) {
        return this.em.createNamedQuery("AppUser.byGroup", AppUser.class)
                .setParameter("group", group)
                .getResultList();
    }
}
