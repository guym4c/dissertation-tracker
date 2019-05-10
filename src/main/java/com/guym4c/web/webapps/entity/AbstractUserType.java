package com.guym4c.web.webapps.entity;

import javax.persistence.*;

/**
 * Superclass for entities associated with an AppUser (student, supervisor)
 */
@MappedSuperclass
public abstract class AbstractUserType extends AbstractEntity {
    
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(nullable = false)
    protected AppUser appUser;
    
    public AbstractUserType() {
        super();
    }
    
    public AbstractUserType(AppUser appUser) {
        this();
        this.appUser = appUser;
    }
    
    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }
}
