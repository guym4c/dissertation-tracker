package com.guym4c.web.webapps.entity;

import java.io.Serializable;
import javax.persistence.*;

@NamedQuery(name = "Student.bySupervisor",
        query = "SELECT st, st.appUser FROM Student st WHERE st.project.supervisor.sussexId = :supervisorId")

@Entity
public class Student implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    private String sussexId;
    
    @OneToOne
    @JoinColumn(nullable = false)
    @MapsId
    private AppUser appUser;
    
    @Column(nullable = false)
    private String course;
    
    @JoinColumn
    @OneToOne
    private Project project;

    public Student() {}

    public Student(AppUser appUser, String course) {
        this.appUser = appUser;
        this.course = course;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
