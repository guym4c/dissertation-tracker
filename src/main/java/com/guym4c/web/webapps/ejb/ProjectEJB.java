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
    private StudentEJB studentEJB;
    
    /**
     * Persists $project, and creates a creator (the logged-in user) relation if applicable.
     * 
     * @param project The
     */
    @RolesAllowed({"supervisor", "student"})
    @TransactionAttribute(REQUIRED)
    public void create(Project project) {
        project.setCreator(this.session.getUser());
        this.persist(project);
        
        // if the student is creating the project, log this and associate them with the project
        if (project.getCreator().isStudent()) {
            Event event = new Event(EventType.PROJECT_PROPOSED, true);
            event.setProject(project);
            event.setTargetUser(project.getSupervisor().getAppUser());
            this.log.create(event);
            
            Student student = this.studentEJB.get(project.getCreator().getSussexId());
            student.setProject(project);
        }
        
        this.em.flush();
    }
    
    /**
     * Allows a $student to choose a $project. This must be approved by the supervisor.
     * 
     * @param inputStudent
     * @param inputProject
     * @throws IllegalStateException 
     */
    @RolesAllowed({"student"})
    @TransactionAttribute(REQUIRED)
    public void select(Student inputStudent, Project inputProject) throws IllegalStateException {
        
        // manage entities
        Project project = this.em.merge(inputProject);
        Student student = this.em.merge(inputStudent);
        
        // ensure project is still available
        if (project.getStatus() != ProjectStatus.AVAILABLE) {
            throw new IllegalStateException();
        }
        student.setProject(project);
        project.setStatus(ProjectStatus.PROPOSED);
        
        // Log
        Event event = new Event(EventType.PROJECT_SELECTED, true);
        event.setTargetUser(project.getCreator());
        event.setProject(project);
        this.log.create(event);
        
        this.em.flush();
    }
    
    /**
     * @return A list of all available projects
     */
    @RolesAllowed({"student"})
    @TransactionAttribute(NOT_SUPPORTED)
    public List<Project> getAllUnassigned() {
        return this.em.createNamedQuery("Project.byStatus", Project.class)
                .setParameter("status", ProjectStatus.AVAILABLE)
                .getResultList();
    }
    
    /**
     * Accept the project $inputProject.
     * 
     * @param inputProject 
     */
    @RolesAllowed({"supervisor"})
    @TransactionAttribute(REQUIRED)
    public void accept(Project inputProject) {
        
        // manage entity
        Project project = this.em.merge(inputProject);
        
        project.setStatus(ProjectStatus.ACCEPTED);
        
        // Log
        Event event = new Event(EventType.PROJECT_ACCEPTED);
        event.setProject(project);
        this.log.create(event);
        
        this.em.flush();
    }
    
    /**
     * Rejects or unselects $inputProject
     * @param inputProject 
     */
    @RolesAllowed({"supervisor", "administrator"})
    @TransactionAttribute(REQUIRED)
    public void reject(Project inputProject) {
        
        // manage object
        Project project = this.em.merge(inputProject);
        
        project.setStatus(ProjectStatus.AVAILABLE);
        
        // remove from student relationship if applicable
        if (project.getStudent() != null) {
            project.getStudent().setProject(null);
        }
        
        Event event = new Event(EventType.PROJECT_REJECTED);
        event.setProject(project);
        event.setTargetUser(project.getCreator());
        this.log.create(event);
        
        // if the project is owned by the student, then it can be discarded
        if (project.getCreator().isStudent()) {
            this.em.remove(project);
        }
        
        this.em.flush();
    }
    
}
