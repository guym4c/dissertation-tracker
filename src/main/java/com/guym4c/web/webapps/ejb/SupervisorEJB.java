package com.guym4c.web.webapps.ejb;

import com.guym4c.web.webapps.entity.Supervisor;
import java.util.List;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.NOT_SUPPORTED;

@Stateless
@DeclareRoles({"student", "supervisor", "administrator"})
public class SupervisorEJB extends AbstractUserEJB {
    
    @TransactionAttribute(NOT_SUPPORTED)
    @RolesAllowed({"administrator"})
    public List<Supervisor> getAll() {
        return this.em.createNamedQuery("Supervisor.all")
                .getResultList();
    }
    
    @PermitAll
    public Supervisor get(String sussexId) {
        return this.em.createNamedQuery("Supervisor.get", Supervisor.class)
                .setParameter("sussexId", sussexId)
                .getResultList()
                .get(0);
    }
}
