/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guym4c.web.webapps.jsf;

import com.guym4c.web.webapps.ejb.TopicEJB;
import com.guym4c.web.webapps.entity.Topic;
import java.io.IOException;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class NewTopicBean extends AbstractBean {
    
    @EJB
    private TopicEJB topicBean;
    
    private String title;
    
    private String description;
    
    public void create() throws IOException {
        topicBean.create(new Topic(this.title, this.description));
        this.context.redirect("/projects/topics/new?created=true");
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
}
