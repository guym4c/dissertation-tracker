package com.guym4c.web.webapps.ejb;

import com.guym4c.web.webapps.entity.Event;
import com.guym4c.web.webapps.entity.EventType;
import com.guym4c.web.webapps.entity.Project;
import com.guym4c.web.webapps.entity.ProjectStatus;
import com.guym4c.web.webapps.entity.Student;
import java.util.List;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.REQUIRED;

@Stateless
@DeclareRoles({"administrator", "supervisor", "student"})
public class ProjectEJB extends AbstractEntityEJB {    
    
    @RolesAllowed({"supervisor", "student"})
    @TransactionAttribute(REQUIRED)
    public void create(final Project project) {        
        this.persist(project);
        
        if (project.getCreator().isStudent()) {
            this.log.create(new Event(EventType.PROJECT_PROPOSED, true) {{
                setProject(project);
                setTargetUser(project.getSupervisor().getAppUser());
            }});
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
    public List<Project> getAllUnassigned() {
        return this.em.createNamedQuery("Project.byStatus", Project.class)
                .setParameter("status", ProjectStatus.AVAILABLE)
                .getResultList();
    }
    
    @RolesAllowed({"supervisor"})
    public void accept(final Project project) {
        project.setStatus(ProjectStatus.ACCEPTED);
        
        this.log.create(new Event(EventType.PROJECT_ACCEPTED) {{
            setProject(project);
        }});
        
        this.em.flush();
    }
    
    @RolesAllowed({"supervisor"})
    public void reject(final Project project) {
        project.setStatus(ProjectStatus.AVAILABLE);
        
        if (project.getStudent() != null) {
            project.getStudent().setProject(null);
        }
        
        this.log.create(new Event(EventType.PROJECT_REJECTED) {{
            setProject(project);
            setTargetUser(project.getCreator());
        }});
        
        if (project.getCreator().isStudent()) {
            this.em.remove(project);
        }
        
        this.em.flush();
    }
    
}
