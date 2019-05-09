package com.guym4c.web.webapps.jsf;

import com.guym4c.web.webapps.ejb.TopicEJB;
import com.guym4c.web.webapps.entity.Topic;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;

@Named
@RequestScoped
@FacesValidator("TopicTitleValidator")
public class TopicTitleValidator implements Validator {
    
    @EJB
    private TopicEJB topicBean;

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String s = value.toString();
        if (!topicBean.exists(s)) {
            throw new ValidatorException(
                    new FacesMessage("That topic title is taken"));
        }
        if (s.length() > Topic.TITLE_LENGTH) {
            throw new ValidatorException(
                    new FacesMessage("Your topic title must be 100 characters or less"));
        }   
    }
}
