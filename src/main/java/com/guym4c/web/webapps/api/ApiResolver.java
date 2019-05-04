
package com.guym4c.web.webapps.api;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.core.Response;

public abstract class ApiResolver {
    
    @PersistenceContext
    protected EntityManager em;
    
    public abstract Response get(String id);
    
    protected <T> List<T> retrieve(String query, Class<T> type, String paramKey, String paramValue) {
        return em.createNamedQuery(query, type)
                .setParameter(paramKey, paramValue)
                .getResultList();
    }
    
    protected <T> List<T> retrieve(String query, Class<T> returns) {
        return em.createNamedQuery(query, returns).getResultList();
    }
    
    protected <T> Response respond(String query, String paramKey, String paramValue, Class<T> type) {        
        return this.createResponse(
                this.retrieve(query, type, paramKey, paramValue));
    }
    
    protected <T> Response respond(String query, Class<T> type) {
        return this.createResponse(
                this.retrieve(query, type));
    }
    
    private Response createResponse(List results) {
        return results.isEmpty() 
                ? Response.status(Response.Status.NOT_FOUND).build()
                : Response.ok(results).build();
    }
    
}
