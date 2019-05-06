package com.guym4c.web.webapps.entity;

import java.util.Date;
import javax.persistence.*;

@NamedQueries({
    
    @NamedQuery(name = "Event.byUser",
            query = "SELECT e FROM Event e WHERE e.user.sussexId = :userId")
})

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
    
    @Column(nullable = false)
    private boolean notification;

    public Event() {
        super();
    }

    public Event(EventType type, Date occurred, boolean notification) {
        this();
        this.type = type;
        this.occurred = occurred;
        this.notification = notification;
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

    public boolean isNotification() {
        return notification;
    }

    public void setNotification(boolean notification) {
        this.notification = notification;
    }          
}
