package com.guym4c.web.webapps.ejb;

import com.guym4c.web.webapps.entity.AppUserGroup;
import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.NOT_SUPPORTED;

@Stateless
@TransactionAttribute(NOT_SUPPORTED)
@PermitAll
public class AppUserGroupEJB extends AbstractEntityEJB {
    
    public void create(AppUserGroup group) {
        this.persist(group).flush();
    }
}
