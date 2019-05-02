package com.guym4c.web.webapps;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="supervisors")
public class Supervisor implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name="sussex_id", unique = true)
    private String sussexId;
    
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(nullable = false)
    private AppUser appUser;
    
    @Column(name = "department")
    private String department;
    
    @Column(name="telephone_number")
    private String telephoneNumber;

    public Supervisor() {}

    public Supervisor(AppUser appUser, String department, String telephoneNumber) {
        this.appUser = appUser;
        this.department = department;
        this.telephoneNumber = telephoneNumber;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }
    
    
}
