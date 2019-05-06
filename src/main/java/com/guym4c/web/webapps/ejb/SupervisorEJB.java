package com.guym4c.web.webapps.ejb;

import com.guym4c.web.webapps.entity.Supervisor;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.NOT_SUPPORTED;

@Stateless
@TransactionAttribute(NOT_SUPPORTED)
@RolesAllowed({"administrator"})
public class SupervisorEJB extends AbstractUserEJB {
    
    public List<Supervisor> getAll() {
        return this.em.createNamedQuery("Supervisor.all")
                .getResultList();
    }
}
