package com.guym4c.web.webapps.ejb;

import com.guym4c.web.webapps.entity.Student;
import com.guym4c.web.webapps.entity.Supervisor;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.NOT_SUPPORTED;

@Stateless
@TransactionAttribute(NOT_SUPPORTED)
public class StudentEJB extends AbstractEntityEJB {
    
    public void create(Student student) {
        this.em.persist(student);
    }
    
    public List<Student> getAll() {
        return this.em.createNamedQuery("Student.all")
                .getResultList();
    }
    
    public List<Student> getAll(Supervisor supervisor) {
        return this.em.createNamedQuery("Student.bySupervisor")
                .setParameter("supervisorId", supervisor.getAppUser().getSussexId())
                .getResultList();
    }
    
}
