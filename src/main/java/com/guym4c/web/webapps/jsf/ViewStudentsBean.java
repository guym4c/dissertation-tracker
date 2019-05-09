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
    private StudentEJB studentBean;
    
    @EJB
    private AppUserEJB appUserBean;
    
    @PostConstruct
    public void initialise() {
        this.students = studentBean.getAll();
    }
    
    public void markAsAdministrator(Student student) {
        appUserBean.markAsAdministrator(student.getAppUser());
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }    
}
