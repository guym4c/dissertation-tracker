package com.guym4c.web.webapps.ejb;

import com.guym4c.web.webapps.entity.Student;
import com.guym4c.web.webapps.entity.Supervisor;
import java.util.List;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.NOT_SUPPORTED;

@Stateless
@TransactionAttribute(NOT_SUPPORTED)

@DeclareRoles({"administrator", "supervisor"})
public class StudentEJB extends AbstractUserEJB {
    
    @RolesAllowed({"administrator"})
    public List<Student> getAll() {
        return this.em.createNamedQuery("Student.all")
                .getResultList();
    }
    
    @RolesAllowed({"supervisor"})
    public List<Student> getAll(Supervisor supervisor) {
        return this.em.createNamedQuery("Student.bySupervisor")
                .setParameter("supervisorId", supervisor.getAppUser().getSussexId())
                .getResultList();
    }
    
}
