package com.motorola.models.resource;

import com.motorola.models.representation.Unit;
import org.restlet.resource.Get;
import org.restlet.resource.ResourceException;

public interface UnitUnitKeyResource {

	/**
	 * Retreives an unit by Key from Azure CosmosDB.
	 *
	 * @return  {@link Unit}
	 * @throws ResourceException if the call to the API fails
	 */
	@Get
	Unit getUnitByKey();

}