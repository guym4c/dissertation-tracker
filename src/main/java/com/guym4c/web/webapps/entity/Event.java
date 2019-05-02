package com.guym4c.web.webapps.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity
public class Event implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @Column(nullable = false)
    private EventType type;
    
    @JoinColumn
    private AppUser user;
    
    @JoinColumn
    private Project project;
    
    @Column(nullable = false)
    private Date timestamp;

    public Event() {}

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public long getId() {
        return id;
    }

    public Date getTimestamp() {
        return timestamp;
    }
          
}
