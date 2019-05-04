package com.guym4c.web.webapps.entity;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;

@NamedQueries({
    
    @NamedQuery(name = "Project.bySupervisor", 
            query = "SELECT p FROM Project p WHERE p.supervisor.appUser.sussexId = :supervisorId"),
    
    @NamedQuery(name = "Project.all",
            query = "SELECT p FROM Project p")
})


@Entity
public class Project implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToMany
    @JoinColumn(nullable = false)
    private Supervisor supervisor;
    
    @Column(length = 199, nullable = false)
    private String title;
    
    @Column(length = 999, nullable = false)
    private String description;
    
    @ManyToMany
    private Set<Topic> topics;
    
    @Column(nullable = false)
    private ProjectStatus status;
    
    private String skills;
    
    @OneToOne(mappedBy = "project")
    @JoinColumn
    private Student student;
    
    public Project() {}

    public long getId() {
        return id;
    }

    public Supervisor getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Supervisor supervisor) {
        this.supervisor = supervisor;
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

    public Set<Topic> getTopics() {
        return topics;
    }

    public void setTopics(Set<Topic> topics) {
        this.topics = topics;
    }

    public ProjectStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }
}