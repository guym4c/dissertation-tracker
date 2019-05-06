package com.guym4c.web.webapps.ejb;

import com.guym4c.web.webapps.entity.Event;
import com.guym4c.web.webapps.entity.EventType;
import com.guym4c.web.webapps.entity.Topic;
import javax.annotation.security.DeclareRoles;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.NOT_SUPPORTED;
import javax.persistence.EntityExistsException;

@Stateless
@TransactionAttribute(NOT_SUPPORTED)
@DeclareRoles({"administrator", "supervisor"})
public class TopicEJB extends AbstractEntityEJB {
    
    public TopicEJB() {
        super();
    }
    
    private boolean exists(Topic topic) {
        return this.em.createNamedQuery("Topic.byTitle", Topic.class)
                .setParameter("title", topic.getTitle())
                .getResultList()
                .isEmpty();
    }
    
    public void create(final Topic topic) throws EntityExistsException {
        if (this.exists(topic)) {
            throw new EntityExistsException();
        }
        
        this.persist(topic);
        
        this.log(new Event(EventType.TOPIC_CREATED) {{
            setEventData(topic.getTitle());
        }});
        
        this.em.flush();
    }
}
