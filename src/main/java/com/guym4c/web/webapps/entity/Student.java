package com.guym4c.web.webapps.entity;

import javax.persistence.*;

@NamedQueries({
    
    @NamedQuery(name = "Student.bySupervisor",
            query = "SELECT st FROM Student st WHERE st.project.supervisor.appUser.sussexId = :supervisorId"),
    
    @NamedQuery(name = "Student.all",
            query = "SELECT st FROM Student st")
})

@Entity
public class Student extends AbstractEntity {
    
    @OneToOne
    @JoinColumn(nullable = false)
    private AppUser appUser;
    
    @Column(nullable = false)
    private String course;
    
    @JoinColumn
    @OneToOne
    private Project project;
    
    public Student() {
        super();
    }

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
