package com.guym4c.web.webapps.ejb;

import com.guym4c.web.webapps.entity.Student;
import com.guym4c.web.webapps.entity.Supervisor;
import java.util.List;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.NOT_SUPPORTED;

@Stateless
@DeclareRoles({"student", "administrator", "supervisor"})
public class StudentEJB extends AbstractUserEJB {
    
    /**
     * @return A list of all students
     */
    @RolesAllowed({"administrator"})
    @TransactionAttribute(NOT_SUPPORTED)
    public List<Student> getAll() {
        return this.em.createNamedQuery("Student.all")
                .getResultList();
    }
    
    /**
     * @param supervisor
     * @return A list of all students supervised by $supervisor
     */
    @RolesAllowed({"supervisor"})
    @TransactionAttribute(NOT_SUPPORTED)
    public List<Student> getAll(Supervisor supervisor) {
        return this.em.createNamedQuery("Student.bySupervisor")
                .setParameter("supervisorId", supervisor.getAppUser().getSussexId())
                .getResultList();
    }
    
    /**
     * @param sussexId
     * @return The student associated with the AppUser with Sussex ID $sussexID
     */
    @PermitAll
    public Student get(String sussexId) {
        return this.em.createNamedQuery("Student.get", Student.class)
                .setParameter("sussexId", sussexId)
                .getResultList()
                .get(0);
    }
    
}
