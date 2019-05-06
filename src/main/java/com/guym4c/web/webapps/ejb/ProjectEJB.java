package com.guym4c.web.webapps.ejb;

import com.guym4c.web.webapps.entity.Project;
import com.guym4c.web.webapps.entity.ProjectStatus;
import com.guym4c.web.webapps.entity.Student;
import com.guym4c.web.webapps.entity.Supervisor;
import java.util.List;

public class ProjectEJB extends AbstractEntityEJB {
    
    public void create(Project project) {
        this.persist(project).flush();
    }
    
    public void select(Student student, Project project) throws IllegalStateException {
        if (project.getStatus() != ProjectStatus.AVAILABLE) {
            throw new IllegalStateException();
        }
        
        student.setProject(project);
        project.setStatus(ProjectStatus.PROPOSED);
        this.em.flush();
    }
    
    public List<Project> getAllUnassigned() {
        return this.em.createNamedQuery("Project.byStatus", Project.class)
                .setParameter("status", ProjectStatus.AVAILABLE)
                .getResultList();
    }
    
    public void accept(Project project, Supervisor supervisor) {
        project.setStatus(ProjectStatus.ACCEPTED);
        this.em.flush();
    }
    
    public void reject(Project project) {
        project.setStatus(ProjectStatus.AVAILABLE);
        
        if (project.getStudent() != null) {
            project.getStudent().setProject(null);
        }
        
        if (project.getCreator().isStudent()) {
            this.em.remove(project);
        }
        this.em.flush();
    }
    
}
