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
    
    private AppUser user;
    
    @
    private Project project;
    
    @Column(nullable = false)
    private Date timestamp;
    
    
    
}
