package com.guym4c.web.webapps.jsf;

import com.guym4c.web.webapps.ejb.StudentEJB;
import com.guym4c.web.webapps.entity.Student;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityExistsException;

@Named
@RequestScoped
public class NewStudentBean extends NewUserBean {
    
    @EJB
    private StudentEJB studentEJB;
    
    private String course;
    
    public void create() throws EntityExistsException, UnsupportedEncodingException, UnsupportedEncodingException, NoSuchAlgorithmException, IOException {
        this.studentEJB.create(new Student(
            this.constructUser(),
            this.course));
        this.context.redirect("/students/new?created=true");
    }
    
    public boolean created() {
        return this.request.getParameter("created") != null;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}
