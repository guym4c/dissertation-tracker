package com.guym4c.web.webapps.api;

import com.guym4c.web.webapps.entity.Supervisor;

/**
 * A student without its relations, for output.
 * Avoids circular references caused by bidirectional JPA relations.
 */
public class SupervisorOutputType extends AbstractUserOutputType {
    
    private final String department;
    
    private final String telephoneNumber;
    
    public SupervisorOutputType(Supervisor supervisor) {
        super(supervisor);
        this.department = supervisor.getDepartment();
        this.telephoneNumber = supervisor.getTelephoneNumber();
    }

    public String getDepartment() {
        return department;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }    
}
