package com.guym4c.web.webapps.entity;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class Administrator implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    private String sussexId;
    
    @OneToOne
    @JoinColumn(nullable = false)
    @MapsId
    private AppUser appUser;

    public Administrator() {}

    public Administrator(AppUser appUser) {
        this.appUser = appUser;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }
}
