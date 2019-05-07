package com.guym4c.web.webapps.jsf;

import com.guym4c.web.webapps.ejb.AppUserEJB;
import com.guym4c.web.webapps.ejb.SessionEJB;
import com.guym4c.web.webapps.entity.AppUser;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Named("authBean")
@RequestScoped
public class AuthBean {
    
    private String userId;
    
    private String password;
    
    @EJB
    AppUserEJB appUserBean;
    
    @EJB
    SessionEJB session;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public void login() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            request.login(this.userId, this.password);
        } catch (ServletException e) {
            //TODO handle failed login
        }
        
        this.session.login(appUserBean.get(this.userId, AppUser.class));
    }
    
    public void logout() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        try {
            request.logout();
            this.session.logout();
        } catch (ServletException e) {
            //TODO handle failed logout
        }
    }
    
}
