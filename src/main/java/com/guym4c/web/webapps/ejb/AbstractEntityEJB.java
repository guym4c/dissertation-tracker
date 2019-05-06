package com.guym4c.web.webapps.ejb;

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
    protected EventEJB log;
    
    @TransactionAttribute(NOT_SUPPORTED)
    public <T> T get(String id, Class<T> type) {
        return this.em.find(type, id);
    }
    
    @TransactionAttribute(REQUIRED)
    protected EntityManager persist(Object object) {
        this.em.persist(object);
        return this.em;
    }
}
