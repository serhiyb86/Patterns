package com.motorola.models.resource;

import com.motorola.models.representation.ApiResponse;
import com.motorola.models.representation.IncidentComment;
import org.restlet.resource.Post;
import org.restlet.resource.ResourceException;

public interface IncidentIncidentKeyCommentResource {

    /**
     * 
     *
     * @return  {@link ApiResponse}
     * @throws ResourceException if the call to the API fails
     */
    @Post
    ApiResponse addIncidentComment(IncidentComment bean);

}