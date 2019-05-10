package com.guym4c.web.webapps.entity;

import java.util.Set;
import javax.persistence.*;

@NamedQueries({
    
    @NamedQuery(name = "Topic.byTitle",
            query = "SELECT t FROM Topic t WHERE t.title = :title"),
    
    @NamedQuery(name = "Topic.all",
            query = "SELECT t FROM Topic t")
})

@Entity
public class Topic extends AbstractEntity {

    public static int TITLE_LENGTH = 100;
    @Column(length = 100, nullable = false)
    private String title;
    
    @Column(nullable = false)
    private String description;
    
    @ManyToMany(mappedBy = "topics")
    private Set<Project> projects;

    public Topic() {
        super();
    }

    public Topic(String title, String description) {
        this.title = title;
        this.description = description;
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

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }
    
}
