package com.motorola.models.resource.client;

import com.motorola.models.Config;
import com.motorola.models.representation.IncidentHandleList;
import com.motorola.models.resource.IncidentKeysFindByCoverageAreasResource;
import com.motorola.models.security.SecurityRuntimeConfigurator;
import com.motorola.models.security.authenticators.defined.Api_keyAuthenticator;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

public class IncidentKeysFindByCoverageAreasClientResource {

	private final SecurityRuntimeConfigurator securityRuntimeConfigurator;

	private final String absolutePath;

	/**
	 * Constructor.
	 *
	 * @param config
	 *            Gathers configuration of the resource URI and security.
	 */
	public IncidentKeysFindByCoverageAreasClientResource(Config config) {
		this.securityRuntimeConfigurator = config.getSecurityConfig().getSecurityRuntimeConfigurator();
		this.absolutePath = config.getBasePath() + "/incidentKeys/findByCoverageAreas";
	}

	/**
	 * One or more coverage areas can be provided with comma separated string during login by Mobile Gateway to get list of Incident keys.
	 *
	 * @param coverageAreas
	 *            coverageAreas to filter by
	 *            Required parameter.
	 * @return {@link IncidentHandleList}
	 * @throws ResourceException if the call to the API fails
	 */
	public IncidentHandleList findByCoverageAreas(String coverageAreas) {
		ClientResource client = new ClientResource(absolutePath);
		QueryParameterHelper.addQueryParameter(client, "coverageAreas", coverageAreas);
		securityRuntimeConfigurator.accept(Api_keyAuthenticator.class).configure(client);

		return client.wrap(IncidentKeysFindByCoverageAreasResource.class).findByCoverageAreas();
	}

}
