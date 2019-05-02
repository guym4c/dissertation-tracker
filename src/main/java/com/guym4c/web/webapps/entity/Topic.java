package com.guym4c.web.webapps.entity;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;

@Entity
public class Topic implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(length = 100, nullable = false)
    private String title;
    
    @Column(nullable = false)
    private String description;
    
    @ManyToMany(mappedBy = "topics")
    private Set<Project> projects;

    public Topic() {}

    public Topic(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getId() {
        return id;
    }  

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }
    
}
