package com.guym4c.web.webapps.jsf;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

/**
 * Bean superclass providing utilities such as request context and logged in user to its children.
 */
public abstract class AbstractBean {
    
    protected final ExternalContext context;
    
    protected HttpServletRequest request;
    
    @Inject
    protected AuthBean session;
    
    public AbstractBean() {
        this.context = FacesContext.getCurrentInstance().getExternalContext();
        this.request = (HttpServletRequest) this.context.getRequest();
    }
    
}
