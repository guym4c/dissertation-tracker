package com.guym4c.web.webapps.ejb;

import com.guym4c.web.webapps.entity.Event;
import com.guym4c.web.webapps.entity.EventType;
import com.guym4c.web.webapps.entity.Topic;
import java.util.List;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.NOT_SUPPORTED;
import static javax.ejb.TransactionAttributeType.REQUIRED;
import javax.persistence.EntityExistsException;

@Stateless
@DeclareRoles({"administrator", "supervisor"})
public class TopicEJB extends AbstractEntityEJB {

    @TransactionAttribute(NOT_SUPPORTED)
    private boolean exists(Topic topic) {
        return !this.em.createNamedQuery("Topic.byTitle", Topic.class)
                .setParameter("title", topic.getTitle())
                .getResultList()
                .isEmpty();
    }
    
    @TransactionAttribute(REQUIRED)
    public void create(final Topic topic) throws EntityExistsException {
        if (this.exists(topic)) {
            throw new EntityExistsException();
        }
        
        this.persist(topic);
        
        Event event = new Event(EventType.TOPIC_CREATED);
        event.setEventData(topic.getTitle());
        this.log.create(event);
        
        this.em.flush();
    }
    
    @RolesAllowed({"supervisor"})
    public boolean exists (String title) {
        return this.exists(new Topic(title, ""));
    }
    
    @TransactionAttribute(NOT_SUPPORTED)
    @PermitAll
    public List<Topic> getAll() {
        return this.em.createNamedQuery("Topic.all", Topic.class)
                .getResultList();
    }
}
