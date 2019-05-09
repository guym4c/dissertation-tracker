/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guym4c.web.webapps.jsf;

import com.guym4c.web.webapps.ejb.TopicEJB;
import com.guym4c.web.webapps.entity.Topic;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class NewTopicBean {
    
    @EJB
    private TopicEJB topicBean;
    
    private String title;
    
    private String description;
    
    public void create() {
        topicBean.create(new Topic(this.title, this.description));
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
