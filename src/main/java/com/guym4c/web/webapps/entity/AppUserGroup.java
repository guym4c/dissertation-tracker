package com.guym4c.web.webapps.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.*;

@Entity(name = "AppUserGroup")
public class AppUserGroup extends AbstractEntity implements Serializable {
    
    @Column(name = "group", nullable = false)
    private AppUserGroupType groupType;
    
    @ManyToOne
    @JoinColumn(name = "user")
    private AppUser user;
    
    public AppUserGroup() {
        super();
    }

    public AppUserGroup(AppUserGroupType group, AppUser user) {
        this();
        this.groupType = group;
        this.user = user;
    }

    public AppUserGroupType getGroupType() {
        return groupType;
    }

    public void setGroupType(AppUserGroupType groupType) {
        this.groupType = groupType;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }
}
