package com.guym4c.web.webapps.ejb;

import com.guym4c.web.webapps.entity.AbstractUserType;
import com.guym4c.web.webapps.entity.AppUser;
import com.guym4c.web.webapps.entity.AppUserGroup;
import com.guym4c.web.webapps.entity.Student;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.ejb.EJB;
import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.NOT_SUPPORTED;
import static javax.ejb.TransactionAttributeType.REQUIRED;
import javax.persistence.EntityExistsException;
import javax.xml.bind.DatatypeConverter;

public abstract class AbstractUserEJB extends AbstractEntityEJB {
    
    @EJB
    private AppUserGroupEJB appUserGroupBean;
    
    @TransactionAttribute(NOT_SUPPORTED)
    private boolean exists(AppUser user) {
        return !this.em.createNamedQuery("AppUser.find", AppUser.class)
                .setParameter("sussexId", user.getSussexId())
                .getResultList()
                .isEmpty();
        }
        
    @TransactionAttribute(REQUIRED)
    public void create(AbstractUserType user) throws EntityExistsException, 
            UnsupportedEncodingException, 
            NoSuchAlgorithmException {
        
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
	digest.update(user.getAppUser().getPassword().getBytes("UTF-8"));
        user.getAppUser().setPassword(
                DatatypeConverter.printBase64Binary(digest.digest()));
        
        if (!this.exists(user.getAppUser())) {
            this.persist(user).flush();
        } else {
            throw new EntityExistsException();
        }
        
        if (user instanceof Student) {
            this.addToGroup(AppUserGroup.STUDENT, user.getAppUser());
        } else {
            this.addToGroup(AppUserGroup.SUPERVISOR, user.getAppUser());
        }
    }
    
    @TransactionAttribute(REQUIRED)
    protected void addToGroup(String group, AppUser user) {
        appUserGroupBean.create(new AppUserGroup(group, user));
    }
}
