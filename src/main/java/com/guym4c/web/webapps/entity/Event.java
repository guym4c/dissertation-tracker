package com.guym4c.web.webapps.entity;

import java.util.Date;
import javax.persistence.*;

@Entity
public class Event extends AbstractEntity {
    
    @Column(nullable = false)
    private EventType type;
    
    @JoinColumn
    @ManyToOne
    private AppUser user;
    
    @JoinColumn
    @ManyToOne
    private Project project;
    
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date occurred;

    public Event() {
        super();
    }

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

    public Date getOccurred() {
        return occurred;
    }
          
}
