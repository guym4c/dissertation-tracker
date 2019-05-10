package com.guym4c.web.webapps.jsf;

import com.guym4c.web.webapps.ejb.AppUserEJB;
import com.guym4c.web.webapps.ejb.StudentEJB;
import com.guym4c.web.webapps.entity.Student;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class ViewStudentsBean extends AbstractBean {
    
    private List<Student> students;
    
    @EJB
    private StudentEJB studentEJB;
    
    @EJB
    private AppUserEJB appUserEJB;
    
    @PostConstruct
    public void initialise() {
        this.students = studentEJB.getAll();
    }
    
    public void markAsAdministrator(Student student) {
        appUserEJB.markAsAdministrator(student.getAppUser());
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }    
}
