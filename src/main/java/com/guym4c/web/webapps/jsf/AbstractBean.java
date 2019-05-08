package com.guym4c.web.webapps.jsf;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

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
