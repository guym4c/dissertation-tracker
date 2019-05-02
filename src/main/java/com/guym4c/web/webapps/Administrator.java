package com.guym4c.web.webapps;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="administrators")
public class Administrator implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name="sussex_id", unique = true)
    private String sussexId;
    
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(nullable = false)
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
