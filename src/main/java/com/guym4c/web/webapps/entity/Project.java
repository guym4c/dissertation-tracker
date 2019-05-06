package com.guym4c.web.webapps.entity;

import java.util.List;
import java.util.Set;
import javax.persistence.*;

@NamedQueries({
    
    @NamedQuery(name = "Project.bySupervisor", 
            query = "SELECT p FROM Project p WHERE p.supervisor.appUser.sussexId = :supervisorId"),
    
    @NamedQuery(name = "Project.all",
            query = "SELECT p FROM Project p"),
    
    @NamedQuery(name = "Project.byStatus",
            query = "SELECT p FROM Project p WHERE p.status = :status")
})

@Entity
public class Project extends AbstractEntity {

    @ManyToOne
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
    private Student student;
    
    @OneToMany(mappedBy = "project")
    private List<Event> events;
    
    @OneToOne
    @JoinColumn(nullable = false)
    private AppUser creator;
    
    public Project() {
        super();
    }

    public Project(Supervisor supervisor, String title, String description, ProjectStatus status) {
        this();
        this.supervisor = supervisor;
        this.title = title;
        this.description = description;
        this.status = status;
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

    public AppUser getCreator() {
        return creator;
    }

    public void setCreator(AppUser creator) {
        this.creator = creator;
    }

    public Student getStudent() {
        return student;
    }

    public List<Event> getEvents() {
        return events;
    }
}