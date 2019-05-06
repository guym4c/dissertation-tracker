package com.guym4c.web.webapps.ejb;

import com.guym4c.web.webapps.entity.AppUserGroup;
import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.REQUIRED;

@Stateless
@PermitAll
public class AppUserGroupEJB extends AbstractEntityEJB {
    
    @TransactionAttribute(REQUIRED)
    public void create(AppUserGroup group) {
        this.persist(group).flush();
    }
}
