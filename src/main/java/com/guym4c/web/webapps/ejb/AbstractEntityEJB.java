package com.guym4c.web.webapps.ejb;

import com.guym4c.web.webapps.entity.Event;
import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class AbstractEntityEJB {
    
    @PersistenceContext
    protected EntityManager em;
    
    @EJB
    private EventEJB eventBean;
    
    public AbstractEntityEJB() {}
    
    public <T> T get(long id, Class<T> type) {
        return this.em.find(type, id);
    }
    
    protected EntityManager persist(Object object) {
        this.em.persist(object);
        return em;
    }
    
    protected void log(Event event) {
        this.eventBean.create(event);
    } 
}
