package com.motorola.models.resource;

import com.motorola.models.representation.Unit;
import com.motorola.models.representation.UpdateUnit;
import org.restlet.resource.Delete;
import org.restlet.resource.Post;
import org.restlet.resource.Put;
import org.restlet.resource.ResourceException;

public interface PushUnitResource {

    /**
     * Receives unit status update request.
     *
     * @throws ResourceException if the call to the API fails
     */
    @Put
    void unitStatusUpdates(UpdateUnit bean);

    /**
     * Receives new incident.
     *
     * @throws ResourceException if the call to the API fails
     */
    @Post
    void onDutyUnit(Unit bean);

    /**
     * Receives Off-duty unit status update request.
     *
     * @throws ResourceException if the call to the API fails
     */
    @Delete
    void offDutyUnit();

}