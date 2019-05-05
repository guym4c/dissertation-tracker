package com.guym4c.web.webapps.entity; 

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.*;

@MappedSuperclass
public abstract class AbstractEntity implements Serializable {
	
    private static final long serialVersionUID = 1L;
 
    @Id
    private final String id;

    public AbstractEntity() {
        this.id = UUID.randomUUID().toString();
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof AbstractEntity)) {
            return false;
        }
        AbstractEntity other = (AbstractEntity) obj;
        return getId().equals(other.getId());
    }

    public String getId() {
        return id;
    }
}