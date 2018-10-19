package com.motorola.models.resource;

import com.motorola.models.representation.ApiResponse;
import com.motorola.models.representation.ResponseNotification;
import org.restlet.resource.Post;
import org.restlet.resource.ResourceException;

public interface ResponseNotificationResource {

    /**
     * On Prem CAD Services to post a generic response notification to cloud CAD2Mobile Cloud On Prem API service. This notification only goes to Service Bus.
     *
     * @return  {@link ApiResponse}
     * @throws ResourceException if the call to the API fails
     */
    @Post
    ApiResponse responseNotification(ResponseNotification bean);

}