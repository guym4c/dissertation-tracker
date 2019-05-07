package com.guym4c.web.webapps.ejb;

import com.guym4c.web.webapps.entity.AppUser;
import com.guym4c.web.webapps.entity.AppUserGroup;
import com.guym4c.web.webapps.entity.Supervisor;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import javax.annotation.PostConstruct;
import javax.annotation.security.RunAs;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.REQUIRED;
import javax.persistence.EntityExistsException;

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
    @TransactionAttribute(REQUIRED)
    public void initialise() {
        
        if (appUserBean.getAll(AppUserGroup.ADMINISTRATOR).isEmpty()) {
            
            Supervisor admin =  new Supervisor(
                new AppUser(DEFAULT_ADMIN_USERNAME, "Admin", "Admin", "admin@admin", DEFAULT_ADMIN_PASSWORD),
                "admin",
                "admin");
            
            try {
                this.supervisorBean.create(admin);
            } catch (EntityExistsException | NoSuchAlgorithmException | UnsupportedEncodingException e) {
                // deploy setup failed
            }
            this.appUserBean.markAsAdministrator(admin.getAppUser(), false);
        }
    }
}
