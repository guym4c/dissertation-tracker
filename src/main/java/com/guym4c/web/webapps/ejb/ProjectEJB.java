package com.guym4c.web.webapps.ejb;

import com.guym4c.web.webapps.entity.Event;
import com.guym4c.web.webapps.entity.EventType;
import com.guym4c.web.webapps.entity.Project;
import com.guym4c.web.webapps.entity.ProjectStatus;
import com.guym4c.web.webapps.entity.Student;
import java.util.List;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.NOT_SUPPORTED;
import static javax.ejb.TransactionAttributeType.REQUIRED;

@Stateless
@DeclareRoles({"administrator", "supervisor", "student"})
public class ProjectEJB extends AbstractEntityEJB {
    
    @EJB
    private StudentEJB studentBean;
    
    @RolesAllowed({"supervisor", "student"})
    @TransactionAttribute(REQUIRED)
    public void create(final Project project) {
        project.setCreator(this.session.getUser());
        this.persist(project);
        
        if (project.getCreator().isStudent()) {
            Event event = new Event(EventType.PROJECT_PROPOSED, true);
            event.setProject(project);
            event.setTargetUser(project.getSupervisor().getAppUser());
            this.log.create(event);
            
            Student student = this.studentBean.get(project.getCreator().getSussexId());
            student.setProject(project);
        }
        
        this.em.flush();
    }
    
    @RolesAllowed({"student"})
    @TransactionAttribute(REQUIRED)
    public void select(final Student student, final Project project) throws IllegalStateException {
        if (project.getStatus() != ProjectStatus.AVAILABLE) {
            throw new IllegalStateException();
        }
        student.setProject(project);
        project.setStatus(ProjectStatus.PROPOSED);
        
        this.log.create(new Event(EventType.PROJECT_SELECTED, true) {{
            setTargetUser(project.getCreator());
            setProject(project);
        }});
        
        this.em.flush();
    }
    
    @RolesAllowed({"student"})
    @TransactionAttribute(NOT_SUPPORTED)
    public List<Project> getAllUnassigned() {
        return this.em.createNamedQuery("Project.byStatus", Project.class)
                .setParameter("status", ProjectStatus.AVAILABLE)
                .getResultList();
    }
    
    @RolesAllowed({"supervisor"})
    @TransactionAttribute(REQUIRED)
    public void accept(Project inputProject) {
        
        Project project = this.em.merge(inputProject);
        
        project.setStatus(ProjectStatus.ACCEPTED);
        
        Event event = new Event(EventType.PROJECT_ACCEPTED);
        event.setProject(project);
        this.log.create(event);
        
        this.em.flush();
    }
    
    @RolesAllowed({"supervisor", "administrator"})
    @TransactionAttribute(REQUIRED)
    public void reject(Project inputProject) {
        
        Project project = this.em.merge(inputProject);
        
        project.setStatus(ProjectStatus.AVAILABLE);
        
        if (project.getStudent() != null) {
            project.getStudent().setProject(null);
        }
        
        Event event = new Event(EventType.PROJECT_REJECTED);
        event.setProject(project);
        event.setTargetUser(project.getCreator());
        this.log.create(event);
        
        if (project.getCreator().isStudent()) {
            this.em.remove(project);
        }
        
        this.em.flush();
    }
    
}
