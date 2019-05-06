package com.guym4c.web.webapps.ejb;

import com.guym4c.web.webapps.entity.AppUserGroup;

public class AppUserGroupEJB extends AbstractEntityEJB {
    
    public void create(AppUserGroup group) {
        this.persist(group).flush();
    }
}
