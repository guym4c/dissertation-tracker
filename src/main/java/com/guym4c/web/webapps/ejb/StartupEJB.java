package com.guym4c.web.webapps.ejb;

import com.guym4c.web.webapps.entity.AppUser;
import com.guym4c.web.webapps.entity.AppUserGroupType;
import com.guym4c.web.webapps.entity.Supervisor;
import javax.annotation.PostConstruct;
import javax.annotation.security.RunAs;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Startup
@Singleton
@RunAs("administrator")
public class StartupEJB {
    
    private static final String DEFAULT_ADMIN_USERNAME = "admin1";
    private static final String DEFAULT_ADMIN_PASSWORD = "admin1";
    
    @EJB
    private SupervisorEJB supervisorBean;
    
    @EJB
    private AppUserEJB appUserBean;
    
    @PostConstruct
    public void initialise()  {
        
        if (appUserBean.getAll(AppUserGroupType.ADMIN).isEmpty()) {
            
            Supervisor admin =  new Supervisor(
                new AppUser(DEFAULT_ADMIN_USERNAME, "admin", "admin@admin", DEFAULT_ADMIN_PASSWORD),
                "admin",
                "admin");
        
            this.supervisorBean.create(admin);        
            this.appUserBean.markAsAdministrator(admin.getAppUser());
        }
    }
}
