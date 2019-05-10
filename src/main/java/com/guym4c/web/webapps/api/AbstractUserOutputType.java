package com.guym4c.web.webapps.api;

import com.guym4c.web.webapps.entity.AbstractUserType;
import com.guym4c.web.webapps.entity.AppUser;

public abstract class AbstractUserOutputType {
    
    private final String id;
    
    private final String sussexId;
    
    private final String name;
    
    private final String surname;
    
    private final String email;
    
    public AbstractUserOutputType(AbstractUserType user) {
        this.id = user.getId();
        AppUser appUser = user.getAppUser();
        this.sussexId = appUser.getSussexId();
        this.name = appUser.getName();
        this.surname = appUser.getSurname();
        this.email = appUser.getEmail();
    }
    
    public String getId() {
        return id;
    }

    public String getSussexId() {
        return sussexId;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }
}
