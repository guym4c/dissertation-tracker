package com.guym4c.web.webapps.jsf;

import javax.faces.application.FacesMessage;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Validates a field to a length defined in code, without piping the length value through the EJB to the JSF
 */
public abstract class AbstractDynamicLengthValidator implements Validator {
    
    protected void validateLength(String s, int length) {
        if (s.length() > length) {
            throw new ValidatorException(
                    new FacesMessage("The length must be " + length + " characters or less"));
        }   
    }
}
