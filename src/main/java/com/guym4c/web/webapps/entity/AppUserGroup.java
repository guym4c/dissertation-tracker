package com.guym4c.web.webapps.entity;

import javax.persistence.Entity;
import javax.persistence.*;

@Entity(name = "AppUserGroup")
public class AppUserGroup extends AbstractEntity {
    
    public static String ADMINISTRATOR = "administrator";
    public static String SUPERVISOR = "supervisor";
    public static String STUDENT = "student";
    
    @Column(name = "groupName", nullable = false)
    private String group;
    
    @ManyToOne
    @JoinColumn(name = "userName")
    private AppUser appUser;
    
    public AppUserGroup() {
        super();
    }

    public AppUserGroup(String group, AppUser appUser) {
        this();
        this.group = group;
        this.appUser = appUser;
    }

    public String getGroup() {
        return group;
    }

    public void setGroupType(String group) {
        this.group = group;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }
}
