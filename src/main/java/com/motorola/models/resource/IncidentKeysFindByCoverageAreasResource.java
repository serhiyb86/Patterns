package com.motorola.models.resource;

import com.motorola.models.representation.IncidentHandleList;
import org.restlet.resource.Get;
import org.restlet.resource.ResourceException;

public interface IncidentKeysFindByCoverageAreasResource {

	/**
	 * One or more coverage areas can be provided with comma separated string during login by Mobile Gateway to get list of Incident keys.
	 *
	 * @return  {@link IncidentHandleList}
	 * @throws ResourceException if the call to the API fails
	 */
	@Get
	IncidentHandleList findByCoverageAreas();

}