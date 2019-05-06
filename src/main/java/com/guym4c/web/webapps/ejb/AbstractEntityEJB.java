package com.guym4c.web.webapps.ejb;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class AbstractEntityEJB {
    
    @PersistenceContext
    protected EntityManager em;
    
    public <T> T get(long id, Class<T> type) {
        return this.em.find(type, id);
    }
    
    protected EntityManager persist(Object object) {
        this.em.persist(object);
        return em;
    } 
}
