package com.guym4c.web.webapps.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

@NamedQueries({
    
    @NamedQuery(name = "AppUser.find",
            query = "SELECT u FROM AppUser u WHERE u.sussexId = :sussexId"),
    
    @NamedQuery(name = "AppUser.byGroup",
            query = "SELECT u FROM AppUserGroup g INNER JOIN g.appUser u WHERE g.group = :group")
})

@Entity(name = "AppUser")
public class AppUser implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "sussexId")
    private String sussexId;
    
    @JoinColumn(nullable = false)
    private String name;
    
    @JoinColumn(nullable = false)
    private String surname;
    
    @JoinColumn(nullable = false)
    private String email;
    
    @Column(name = "password", nullable = false)
    private String password;
    
    @OneToMany(mappedBy = "targetUser")
    private List<Event> events;
    
    @OneToMany(mappedBy = "actingUser")
    private List<Event> eventsCreated;
    
    @OneToOne(mappedBy = "appUser")
    private Supervisor supervisor;
    
    @OneToOne(mappedBy = "appUser")
    private Student student;
    
    @OneToMany(mappedBy = "appUser")
    private List<AppUserGroup> groups;

    public AppUser() {}

    public AppUser(String sussexId, String name, String surname, String email, String password) {
        this.sussexId = sussexId;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
    }

    public String getSussexId() {
        return sussexId;
    }

    public void setSussexId(String sussexId) {
        this.sussexId = sussexId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Event> getEvents() {
        return events;
    }

    public List<Event> getEventsCreated() {
        return eventsCreated;
    }
    
    public boolean isSupervisor() {
        return supervisor != null;
    }
    
    public boolean isStudent() {
        return student != null;
    }

    public Supervisor getSupervisor() {
        return supervisor;
    }

    public Student getStudent() {
        return student;
    }

    public List<AppUserGroup> getGroups() {
        return groups;
    }
}
