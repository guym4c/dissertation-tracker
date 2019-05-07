package com.guym4c.web.webapps.ejb;

import com.guym4c.web.webapps.jsf.AuthBean;
import javax.ejb.EJB;
import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.NOT_SUPPORTED;
import static javax.ejb.TransactionAttributeType.REQUIRED;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class AbstractEntityEJB {
    
    @PersistenceContext
    protected EntityManager em;
    
    @EJB
    protected EventEJB log;
    
    @Inject
    protected AuthBean session;
    
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
