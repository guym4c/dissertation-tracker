package com.guym4c.web.webapps.jsf;

import com.guym4c.web.webapps.entity.Project;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;

@Named
@RequestScoped
@FacesValidator("ProjectDescriptionValidator")
public class ProjectDescriptionValidator extends AbstractDynamicLengthValidator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        this.validateLength(value.toString(), Project.DESCRIPTION_LENGTH);
    }
}
