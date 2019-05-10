package com.guym4c.web.webapps.api;

import com.guym4c.web.webapps.entity.Student;

public class StudentOutputType extends AbstractUserOutputType {
    
    private final String course;
    
    public StudentOutputType(Student student) {
        super(student);
        this.course = student.getCourse();
    }

    public String getCourse() {
        return course;
    }
    
}
