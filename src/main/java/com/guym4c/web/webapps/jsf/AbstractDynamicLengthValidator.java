package com.guym4c.web.webapps.jsf;

import javax.faces.application.FacesMessage;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public abstract class AbstractDynamicLengthValidator implements Validator {
    
    protected void validateLength(String s, int length) {
        if (s.length() > length) {
            throw new ValidatorException(
                    new FacesMessage("The length must be " + length + " characters or less"));
        }   
    }
}
