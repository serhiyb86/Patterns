package com.motorola.models.resource;

import com.motorola.models.representation.ApiResponse;
import com.motorola.models.representation.UserSession;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.ResourceException;

public interface UserSessionSessionIdResource {

    /**
     * Get a user session book on response from Cosmos DB API.
     *
     * @return  {@link UserSession}
     * @throws ResourceException if the call to the API fails
     */
    @Get
    UserSession userSession();

    /**
     * Perform user book off CAD function.
     *
     * @return  {@link ApiResponse}
     * @throws ResourceException if the call to the API fails
     */
    @Delete
    ApiResponse bookOff();

}