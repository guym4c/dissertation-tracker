package com.guym4c.web.webapps.ejb;

import com.guym4c.web.webapps.entity.AppUser;
import com.guym4c.web.webapps.entity.Event;
import com.guym4c.web.webapps.entity.Student;
import com.guym4c.web.webapps.entity.Supervisor;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.NOT_SUPPORTED;

@Stateless
@TransactionAttribute(NOT_SUPPORTED)
public class EventEJB extends AbstractEntityEJB {
    
    public void create(Event event) {
        this.em.persist(event);
    }
    
    public List<Event> getAll(Supervisor supervisor) {
        return this.getAll(supervisor.getAppUser());
    }
    
    public List<Event> getAll(Student student) {
        return this.getAll(student.getAppUser());
    }
    
    private List<Event> getAll(AppUser user) {
        return this.em.createNamedQuery("Event.byUser", Event.class)
                .setParameter("userId", user.getSussexId())
                .getResultList();
    }
    
}
