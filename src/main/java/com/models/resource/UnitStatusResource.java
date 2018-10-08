package com.models.resource;

import com.models.representation.ApiResponse;
import com.models.representation.UpdateUnitStatusParameters;
import org.restlet.resource.Put;
import org.restlet.resource.ResourceException;

public interface UnitStatusResource {

    /**
     * 
     *
     * @return  {@link ApiResponse}
     * @throws ResourceException if the call to the API fails
     */
    @Put
    ApiResponse updateUnitStatus(UpdateUnitStatusParameters bean);

}