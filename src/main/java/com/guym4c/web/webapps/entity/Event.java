package com.guym4c.web.webapps.entity;

import java.util.Date;
import javax.persistence.*;

@NamedQueries({
    
    @NamedQuery(name = "Event.byUser",
            query = "SELECT e FROM Event e WHERE e.actingUser.sussexId = :userId"),
    
    @NamedQuery(name = "Event.all",
            query = "SELECT e FROM Event e")
})

@Entity
public class Event extends AbstractEntity {
    
    @Column(nullable = false)
    private EventType type;
    
    @JoinColumn
    @ManyToOne
    private AppUser targetUser;
    
    @JoinColumn
    @ManyToOne
    private AppUser actingUser;
    
    @JoinColumn
    @ManyToOne
    private Project project;
    
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date occurred;
    
    @Column(nullable = false)
    private boolean notification = false;
    
    // Utility field, when we want to associate the event with something that there isn't an existing relation with
    private String eventData;

    public Event() {
        super();
        this.occurred = new Date();
    }

    public Event(EventType type, boolean notification) {
        this(type);
        this.notification = notification;
    }
    
    public Event(EventType type) {
        this();
        this.type = type;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public AppUser getTargetUser() {
        return targetUser;
    }

    public void setTargetUser(AppUser targetUser) {
        this.targetUser = targetUser;
    }

    public AppUser getActingUser() {
        return actingUser;
    }

    public void setActingUser(AppUser actingUser) {
        this.actingUser = actingUser;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getEventData() {
        return eventData;
    }

    public void setEventData(String eventData) {
        this.eventData = eventData;
    }

    public Date getOccurred() {
        return occurred;
    }

    public boolean isNotification() {
        return notification;
    }

    public void setAsNotification() {
        this.notification = true;
    }          
}
