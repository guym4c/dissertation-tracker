package com.guym4c.web.webapps.ejb;

import com.guym4c.web.webapps.entity.AbstractUserType;
import com.guym4c.web.webapps.entity.Event;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.NOT_SUPPORTED;

@Stateless
@TransactionAttribute(NOT_SUPPORTED)
public class EventEJB extends AbstractEntityEJB {
    
    public void create(Event event) {
        this.persist(event).flush();
    }
    
    private List<Event> getAll(AbstractUserType user) {
        return this.em.createNamedQuery("Event.byUser", Event.class)
                .setParameter("userId", user.getAppUser().getSussexId())
                .getResultList();
    }
}
