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

/**
 * Ensures at least one administrator is present upon deploy.
 */
@Startup
@Singleton
@RunAs("administrator")
public class StartupEJB {
    
    // default credentials
    public static final String DEFAULT_ADMIN_USERNAME = "admin1";
    private static final String DEFAULT_ADMIN_PASSWORD = "admin1";
    
    @EJB
    private SupervisorEJB supervisorEJB;
    
    @EJB
    private AppUserEJB appUserEJB;
    
    /**
     * Set up default admin
     */
    @PostConstruct
    @TransactionAttribute(REQUIRED)
    public void initialise() {
        
        // check if other admins are already persisted
        if (appUserEJB.getAll(AppUserGroup.ADMINISTRATOR).isEmpty()) {
            
            Supervisor admin =  new Supervisor(
                new AppUser(DEFAULT_ADMIN_USERNAME, "Admin", "Admin", "admin@admin", DEFAULT_ADMIN_PASSWORD),
                "admin",
                "admin");
            
            try {
                this.supervisorEJB.create(admin);
            } catch (EntityExistsException | NoSuchAlgorithmException | UnsupportedEncodingException e) {
                // deploy setup failed
            }
            this.appUserEJB.markAsAdministrator(admin.getAppUser(), false);
        }
    }
}
