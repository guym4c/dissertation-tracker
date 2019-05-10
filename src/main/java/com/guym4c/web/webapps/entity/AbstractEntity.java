package com.guym4c.web.webapps.entity; 

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.*;

/**
 * Superclass for entities identified with a UUID.
 */
@MappedSuperclass
public abstract class AbstractEntity implements Serializable {
	
    protected static final long serialVersionUID = 1L;
 
    @Id
    protected final String id;

    public AbstractEntity() {
        this.id = UUID.randomUUID().toString();
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    /**
     * 
     * @param obj
     * @return $obj compared to this by type then ID
     */
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