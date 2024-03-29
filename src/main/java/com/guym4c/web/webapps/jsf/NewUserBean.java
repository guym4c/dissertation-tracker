package com.guym4c.web.webapps.jsf;

import com.guym4c.web.webapps.entity.AppUser;

public abstract class NewUserBean extends AbstractBean {
    
    protected String sussexId;
    
    protected String name;
    
    protected String surname;
    
    protected String email;
    
    protected String password;

    public String getSussexId() {
        return sussexId;
    }

    public void setSussexId(String sussexId) {
        this.sussexId = sussexId;
    }
    
    protected AppUser constructUser() {
        return new AppUser(this.sussexId, this.name, this.surname, this.email, this.password);
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
}
