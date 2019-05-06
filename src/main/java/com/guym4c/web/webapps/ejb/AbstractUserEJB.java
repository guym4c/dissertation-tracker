package com.guym4c.web.webapps.ejb;

import com.guym4c.web.webapps.entity.AbstractUserType;
import com.guym4c.web.webapps.entity.AppUser;
import javax.persistence.EntityExistsException;

public abstract class AbstractUserEJB extends AbstractEntityEJB {
    
    private boolean exists(AppUser user) {
        return this.em.createNamedQuery("AppUser.all", AppUser.class)
                .setParameter("sussexID", user.getSussexId())
                .getResultList()
                .isEmpty();
    }
    
    public void create(AbstractUserType userType) throws EntityExistsException {
        if (!this.exists(userType.getAppUser())) {
            this.persist(userType).flush();
        } else {
            throw new EntityExistsException();
        }
    }
}
