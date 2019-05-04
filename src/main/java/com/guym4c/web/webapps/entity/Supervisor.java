package com.guym4c.web.webapps.entity;

import java.io.Serializable;
import javax.persistence.*;

@NamedQueries({
    
    @NamedQuery(name = "Supervisor.byStudent",
            query = "SELECT su, su.appUser FROM Student st INNER JOIN st.project p INNER JOIN p.supervisor su WHERE st.appUser.sussexId = :studentId"),
    
    @NamedQuery(name = "Supervisor.all",
            query = "SELECT su, su.appUser FROM Supervisor su")
})


@Entity
public class Supervisor implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @OneToOne
    @JoinColumn(nullable = false)
    @MapsId
    private AppUser appUser;
    
    @Column(nullable = false)
    private String department;
    
    @Column(nullable = false)
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

    public long getId() {
        return id;
    }
}
