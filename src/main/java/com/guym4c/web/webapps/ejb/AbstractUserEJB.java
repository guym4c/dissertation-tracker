package com.guym4c.web.webapps.ejb;

import com.guym4c.web.webapps.entity.AppUser;

public abstract class AbstractUserEJB extends AbstractEntityEJB {
    
    public boolean exists(String sussexId) {
        return this.em.createNamedQuery("AppUser.all", AppUser.class)
                .setParameter("sussexID", sussexId)
                .getResultList()
                .isEmpty();
    }
    
}
