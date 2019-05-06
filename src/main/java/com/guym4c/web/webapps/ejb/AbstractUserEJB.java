package com.guym4c.web.webapps.ejb;

import com.guym4c.web.webapps.entity.AbstractUserType;
import com.guym4c.web.webapps.entity.AppUser;
import com.guym4c.web.webapps.entity.AppUserGroup;
import com.guym4c.web.webapps.entity.AppUserGroupType;
import javax.persistence.EntityExistsException;

public abstract class AbstractUserEJB extends AbstractEntityEJB {
    
    private boolean exists(AppUser user) {
        return this.em.createNamedQuery("AppUser.all", AppUser.class)
                .setParameter("sussexID", user.getSussexId())
                .getResultList()
                .isEmpty();
    }
    
    public void create(AbstractUserType user) throws EntityExistsException {
        if (!this.exists(user.getAppUser())) {
            this.persist(user).flush();
        } else {
            throw new EntityExistsException();
        }
        
        if (user.getAppUser().isStudent()) {
            this.addToGroup(AppUserGroupType.STUDENT, user.getAppUser());
        } else {
            this.addToGroup(AppUserGroupType.SUPERVISOR, user.getAppUser());
        }
    }
    
    protected void addToGroup(AppUserGroupType group, AppUser user) {
        (new AppUserGroupEJB()).create(
                new AppUserGroup(group, user));
    }
}
