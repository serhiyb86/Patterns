package com.motorola.models.resource;

import com.motorola.models.representation.ApiResponse;
import com.motorola.models.representation.MonitorAreaList;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ResourceException;

public interface UserSessionSessionIdMonitorAreasResource {

    /**
     * Get the monitor Areas by sessionId from Cosmos DB API.
     *
     * @return  {@link MonitorAreaList}
     * @throws ResourceException if the call to the API fails
     */
    @Get
    MonitorAreaList getMonitorAreas();

    /**
     * Replace the monitor areas by sessionId to Cosmos DB API.
     *
     * @return  {@link ApiResponse}
     * @throws ResourceException if the call to the API fails
     */
    @Put
    ApiResponse putMonitorAreas(MonitorAreaList bean);

}