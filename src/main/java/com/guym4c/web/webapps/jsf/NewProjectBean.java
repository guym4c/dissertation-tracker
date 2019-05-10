package com.guym4c.web.webapps.jsf;

import com.guym4c.web.webapps.ejb.ProjectEJB;
import com.guym4c.web.webapps.ejb.StudentEJB;
import com.guym4c.web.webapps.ejb.SupervisorEJB;
import com.guym4c.web.webapps.ejb.TopicEJB;
import com.guym4c.web.webapps.entity.Project;
import com.guym4c.web.webapps.entity.ProjectStatus;
import com.guym4c.web.webapps.entity.Student;
import com.guym4c.web.webapps.entity.Supervisor;
import com.guym4c.web.webapps.entity.Topic;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class NewProjectBean extends AbstractBean {
    
    @EJB
    private ProjectEJB projectBean;
    
    @EJB
    private TopicEJB topicBean;
    
    @EJB
    private SupervisorEJB supervisorBean;
    
    @EJB
    private StudentEJB studentBean;
    
    private List<Supervisor> supervisors;
    
    private List<Topic> topics;
    
    private String title;
    
    private String description;
    
    private String skills;
    
    private String inputTopics;
    
    private String supervisorId;
    
    @PostConstruct
    public void initialise() {
        this.supervisors = this.supervisorBean.getAll();
        this.topics = this.topicBean.getAll();
    }
    
    public void create() throws IOException {
        
        Project project = new Project(this.title, this.description, ProjectStatus.AVAILABLE);
        project.setTopics(new HashSet<Topic>());
        
        for (String topicId : Arrays.asList(this.inputTopics.split(","))) {
            project.getTopics().add(this.topicBean.get(topicId, Topic.class));
        }

        if (this.session.getUser().isStudent()) {
            project.setStatus(ProjectStatus.PROPOSED);
            project.setSupervisor(this.supervisorBean.get(this.supervisorId));
        } else {
            project.setSupervisor(this.supervisorBean.get(this.session.getUser().getSussexId()));
        }
        
        this.projectBean.create(project);
        
        if (this.session.getUser().isStudent()) {
            this.context.redirect("/projects");
        } else {
            this.context.redirect("/projects/new?created=true");
        }
    }
    
    public boolean created() {
        return this.request.getParameter("created") != null;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getInputTopics() {
        return inputTopics;
    }

    public void setInputTopics(String inputTopics) {
        this.inputTopics = inputTopics;
    }

    public String getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(String supervisorId) {
        this.supervisorId = supervisorId;
    }
    
    public List<Supervisor> getSupervisors() {
        return supervisors;
    }

    public List<Topic> getTopics() {
        return topics;
    }
    
    
    
    
}
