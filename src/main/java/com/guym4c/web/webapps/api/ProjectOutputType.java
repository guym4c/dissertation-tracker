package com.guym4c.web.webapps.api;

import com.guym4c.web.webapps.entity.Project;
import com.guym4c.web.webapps.entity.Topic;
import java.util.ArrayList;
import java.util.List;

/**
 * A Project without its relations, for output.
 * Avoids circular references caused by bidirectional JPA relations.
 */
public class ProjectOutputType {
    
    private final String id;
    
    private final String title;
    
    private final String description;
    
    private final String creator;
    
    private final String supervisor;
    
    private final String student;
    
    private final String status;
    
    private final String skills;
    
    private final List<String> topics;
    
    public ProjectOutputType(Project project) {
        this.id = project.getId();
        this.title = project.getTitle();
        this.description = project.getDescription();
        this.creator = project.getCreator().getSussexId();
        this.supervisor = project.getSupervisor() == null
                ? null
                : project.getSupervisor().getAppUser().getSussexId();
        
        this.student = project.getStudent() == null
                ? null
                : project.getStudent().getAppUser().getSussexId();
        
        this.status = project.getStatus().toString();
        this.skills = project.getSkills();
        
        if (project.getTopics() == null) {
            this.topics = null;
        } else {
            this.topics = new ArrayList<>();
            for (Topic topic : project.getTopics()) {
                topics.add(topic.getTitle());
            }
        }
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getCreator() {
        return creator;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public String getStudent() {
        return student;
    }

    public String getStatus() {
        return status;
    }

    public String getSkills() {
        return skills;
    }

    public List<String> getTopics() {
        return topics;
    }    
}
