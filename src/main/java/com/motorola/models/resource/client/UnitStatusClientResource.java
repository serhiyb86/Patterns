package com.motorola.models.resource.client;

import com.motorola.models.Config;
import com.motorola.models.representation.ApiResponse;
import com.motorola.models.representation.UpdateUnitStatusParameters;
import com.motorola.models.resource.UnitStatusResource;
import com.motorola.models.security.SecurityRuntimeConfigurator;
import com.motorola.models.security.authenticators.defined.ClientApi_authAuthenticator;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

public class UnitStatusClientResource {

	private final SecurityRuntimeConfigurator securityRuntimeConfigurator;

	private final String absolutePath;

	/**
	 * Constructor.
	 *
	 * @param config
	 *            Gathers configuration of the resource URI and security.
	 */
	public UnitStatusClientResource(Config config) {
		this.securityRuntimeConfigurator = config.getSecurityConfig().getSecurityRuntimeConfigurator();
		this.absolutePath = config.getBasePath() + "/unit/status";
	}

	/**
	 *
	 *
	 * @param bean
	 *            Parameter "bean"
	 * @return {@link ApiResponse}
	 * @throws ResourceException if the call to the API fails
	 */
	public ApiResponse updateUnitStatus(UpdateUnitStatusParameters bean) {
		ClientResource client = new ClientResource(absolutePath);
		securityRuntimeConfigurator.accept(ClientApi_authAuthenticator.class).configure(client);

		return client.wrap(UnitStatusResource.class).updateUnitStatus(bean);
	}

}
