package com.models.resource;

import com.models.representation.ApiResponse;
import com.models.representation.UnitHandle;
import org.restlet.resource.Post;
import org.restlet.resource.ResourceException;

public interface IncidentIncidentKeyDispatchesDispatchKeyUnitResource {

    /**
     * Dispatches a unit.
     *
     * @return  {@link ApiResponse}
     * @throws ResourceException if the call to the API fails
     */
    @Post
    ApiResponse dispatchUnit(UnitHandle bean);

}