package com.guym4c.web.webapps.entity;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class AppUser implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    private String sussexId;
    
    @JoinColumn(nullable = false)
    private String name;
    
    @JoinColumn(nullable = false)
    private String email;
    
    @JoinColumn(nullable = false)
    private String password;

    public AppUser() {}

    public AppUser(String sussexId, String name, String email, String password) {
        this.sussexId = sussexId;
        this.name = name;
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
}
