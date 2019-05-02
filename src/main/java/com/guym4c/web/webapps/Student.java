package com.guym4c.web.webapps;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="students")
public class Student implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name="sussex_id", unique = true)
    private String sussexId;
    
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(nullable = false)
    private AppUser appUser;
    
    @Column
    private String course;

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
}
