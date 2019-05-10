package com.guym4c.web.webapps.jsf;

import com.guym4c.web.webapps.ejb.TopicEJB;
import com.guym4c.web.webapps.entity.Topic;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;

@Named
@RequestScoped
@FacesValidator("TopicTitleValidator")
public class TopicTitleValidator extends AbstractDynamicLengthValidator {
    
    @EJB
    private TopicEJB topicBean;

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String s = value.toString();
        if (topicBean.exists(s)) {
            throw new ValidatorException(
                    new FacesMessage("That topic title is taken"));
        }
        this.validateLength(s, Topic.TITLE_LENGTH);
    }
}
