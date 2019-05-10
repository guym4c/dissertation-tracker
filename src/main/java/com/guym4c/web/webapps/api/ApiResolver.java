
package com.guym4c.web.webapps.api;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.core.Response;

public abstract class ApiResolver {
    
    @PersistenceContext
    protected EntityManager em;
    
    public abstract Response get(String id);
    
    /**
     * 
     * @param <T>
     * @param query
     * @param type
     * @param paramKey
     * @param paramValue
     * @return The results of $query (a list of $type), executed with the supplied parameters
     */
    protected <T> List<T> retrieve(String query, Class<T> type, String paramKey, String paramValue) {
        return em.createNamedQuery(query, type)
                .setParameter(paramKey, paramValue)
                .getResultList();
    }
    
    /**
     * 
     * @param <T>
     * @param query
     * @param returns
     * @return The results of $query as a list of $returns
     */
    protected <T> List<T> retrieve(String query, Class<T> returns) {
        return em.createNamedQuery(query, returns).getResultList();
    }
    
    /**
     * Build a JSON response from a list of objects
     * 
     * @param results
     * @return A JavaX-RS 200 Response (404 if $results is empty)
     */
    protected Response createResponse(List results) {
        return results.isEmpty() 
                ? Response.status(Response.Status.NOT_FOUND).build()
                : Response.ok(results).build();
    }
    
}
