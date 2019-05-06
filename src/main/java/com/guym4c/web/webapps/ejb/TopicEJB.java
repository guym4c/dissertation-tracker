package com.guym4c.web.webapps.ejb;

import com.guym4c.web.webapps.entity.Topic;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.NOT_SUPPORTED;
import javax.persistence.EntityExistsException;

@Stateless
@TransactionAttribute(NOT_SUPPORTED)
public class TopicEJB extends AbstractEntityEJB {
    
    private boolean exists(Topic topic) {
        return this.em.createNamedQuery("Topic.byTitle", Topic.class)
                .setParameter("title", topic.getTitle())
                .getResultList()
                .isEmpty();
    }
    
    public void create(Topic topic) {
        if (!this.exists(topic)) {
            this.em.persist(topic);
        } else {
            throw new EntityExistsException();
        }
    }
}
