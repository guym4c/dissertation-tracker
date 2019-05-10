package com.guym4c.web.webapps.ejb;

import com.guym4c.web.webapps.entity.AbstractUserType;
import com.guym4c.web.webapps.entity.Event;
import java.util.List;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.NOT_SUPPORTED;
import static javax.ejb.TransactionAttributeType.REQUIRED;

@Stateless
@DeclareRoles({"administrator", "supervisor", "student"})
public class EventEJB extends AbstractEntityEJB {
    
    @PermitAll
    @TransactionAttribute(REQUIRED)
    public void create(Event event) {
        event.setActingUser(this.session.getUser());
        this.persist(event).flush();
    }
    
    @RolesAllowed({"administrator"})
    @TransactionAttribute(NOT_SUPPORTED)
    public List<Event> getAll(AbstractUserType user) {
        return this.em.createNamedQuery("Event.byUser", Event.class)
                .setParameter("userId", user.getAppUser().getSussexId())
                .getResultList();
    }
    
    public List<Event> getAll() {
        return this.em.createNamedQuery("Event.all", Event.class)
                .getResultList();
    }
}
