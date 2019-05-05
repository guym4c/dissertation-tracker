package com.guym4c.web.webapps.ejb;

import com.guym4c.web.webapps.entity.Topic;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.NOT_SUPPORTED;

@Stateless
@TransactionAttribute(NOT_SUPPORTED)
public class TopicEJB extends AbstractEntityEJB {
    
    public void create(Topic topic) {
        this.em.persist(topic);
    }
}
