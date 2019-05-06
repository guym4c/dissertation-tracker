package com.guym4c.web.webapps.ejb;

import com.guym4c.web.webapps.entity.AppUser;
import com.guym4c.web.webapps.entity.Supervisor;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
public class StartupEJB {
    
    private static final String DEFAULT_ADMIN_USERNAME = "admin1";
    private static final String DEFAULT_ADMIN_PASSWORD = "admin1";
    
    public StartupEJB() {}
    
    @PostConstruct
    public void initialise() {
        
        (new SupervisorEJB()).create(
                new Supervisor(
                    new AppUser(DEFAULT_ADMIN_USERNAME, "admin", "admin@admin", DEFAULT_ADMIN_PASSWORD),
                    "admin",
                    "admin"));
    }
    
}
