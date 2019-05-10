package com.guym4c.web.webapps.jsf;

import com.guym4c.web.webapps.ejb.EventEJB;
import com.guym4c.web.webapps.entity.Event;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class EventsBean extends AbstractBean {
    
    @EJB
    private EventEJB eventEJB;
    
    private List<Event> events;
    
    @PostConstruct
    public void initialise() {
        this.events = this.eventEJB.getAll();
    }

    public List<Event> getEvents() {
        return events;
    }
}
