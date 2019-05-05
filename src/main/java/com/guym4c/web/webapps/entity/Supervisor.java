package com.guym4c.web.webapps.entity;

import java.util.List;
import javax.persistence.*;

@NamedQueries({
    
    @NamedQuery(name = "Supervisor.byStudent",
            query = "SELECT su FROM Student st INNER JOIN st.project p INNER JOIN p.supervisor su WHERE st.appUser.sussexId = :studentId"),
    
    @NamedQuery(name = "Supervisor.all",
            query = "SELECT su FROM Supervisor su")
})

@Entity
public class Supervisor extends AbstractEntity {
    
    @OneToOne
    @JoinColumn(nullable = false)
    private AppUser appUser;
    
    @Column(nullable = false)
    private String department;
    
    @Column(nullable = false)
    private String telephoneNumber;
    
    @OneToMany(mappedBy = "supervisor")
    private List<Project> projects;

    public Supervisor() {
        super();
    }

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

    public List<Project> getProjects() {
        return projects;
    }
}
