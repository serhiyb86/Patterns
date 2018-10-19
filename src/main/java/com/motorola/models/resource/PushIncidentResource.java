package com.motorola.models.resource;

import com.motorola.models.representation.EmergencyIncident;
import com.motorola.models.representation.UpdateEmergencyIncident;
import org.restlet.resource.Post;
import org.restlet.resource.Put;
import org.restlet.resource.ResourceException;

public interface PushIncidentResource {

    /**
     * Receives incident updates /unsolicited message.
     *
     * @throws ResourceException if the call to the API fails
     */
    @Put
    void updateIncident(UpdateEmergencyIncident bean);

    /**
     * Receives new incident.
     *
     * @throws ResourceException if the call to the API fails
     */
    @Post
    void createIncident(EmergencyIncident bean);

}