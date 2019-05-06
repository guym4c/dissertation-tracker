package com.guym4c.web.webapps.ejb;

import com.guym4c.web.webapps.entity.Event;
import javax.ejb.EJB;
import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.NOT_SUPPORTED;
import static javax.ejb.TransactionAttributeType.REQUIRED;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class AbstractEntityEJB {
    
    @PersistenceContext
    protected EntityManager em;
    
    @EJB
    private EventEJB eventBean;
    
    public AbstractEntityEJB() {}
    
    @TransactionAttribute(NOT_SUPPORTED)
    public <T> T get(String id, Class<T> type) {
        return this.em.find(type, id);
    }
    
    @TransactionAttribute(REQUIRED)
    protected EntityManager persist(Object object) {
        this.em.persist(object);
        return em;
    }
    
    @TransactionAttribute(REQUIRED)
    protected void log(Event event) {
        this.eventBean.create(event);
    } 
}
