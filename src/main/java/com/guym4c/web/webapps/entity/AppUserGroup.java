package com.guym4c.web.webapps.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.*;

@Entity(name = "AppUserGroup")
public class AppUserGroup extends AbstractEntity implements Serializable {
    
    @Column(name = "groupName", nullable = false)
    private AppUserGroupType groupType;
    
    @ManyToOne
    @JoinColumn(name = "userName")
    private AppUser appUser;
    
    public AppUserGroup() {
        super();
    }

    public AppUserGroup(AppUserGroupType group, AppUser appUser) {
        this();
        this.groupType = group;
        this.appUser = appUser;
    }

    public AppUserGroupType getGroupType() {
        return groupType;
    }

    public void setGroupType(AppUserGroupType groupType) {
        this.groupType = groupType;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }
}
