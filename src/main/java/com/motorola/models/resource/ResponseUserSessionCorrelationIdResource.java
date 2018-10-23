package com.motorola.models.resource;

import com.motorola.models.representation.ApiResponse;
import com.motorola.models.representation.UserSession;
import org.restlet.resource.Post;
import org.restlet.resource.ResourceException;

public interface ResponseUserSessionCorrelationIdResource {

    /**
     * On Prem CAD Services to post a book on response to cloud CAD2Mobile Cloud On Prem API service.
     *
     * @return  {@link ApiResponse}
     * @throws ResourceException if the call to the API fails
     */
    @Post
    ApiResponse bookOnResponse(UserSession bean);

}