package com.motorola.models.resource.client;

import com.motorola.models.Config;
import com.motorola.models.representation.Unit;
import com.motorola.models.resource.UnitUnitKeyResource;
import com.motorola.models.security.SecurityRuntimeConfigurator;
import com.motorola.models.security.authenticators.defined.ClientApi_authAuthenticator;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

public class UnitUnitKeyClientResource {

	private final SecurityRuntimeConfigurator securityRuntimeConfigurator;

	private String unitKey;

	private final String absolutePath;

	/**
	 * Constructor.
	 *
	 * @param config
	 *            Gathers configuration of the resource URI and security.
	 * @param unitKey
	 *            The Key for retrieval
	 */
	public UnitUnitKeyClientResource(Config config, String unitKey) {
		this.securityRuntimeConfigurator = config.getSecurityConfig().getSecurityRuntimeConfigurator();
		this.unitKey = unitKey;
		this.absolutePath = config.getBasePath() + "/unit/{unitKey}";
	}

	/**
	 * Retreives an unit by Key from Azure CosmosDB.
	 *
	 * @return {@link Unit}
	 * @throws ResourceException if the call to the API fails
	 */
	public Unit getUnitByKey() {
		ClientResource client = new ClientResource(absolutePath);
		client.setAttribute("unitKey", this.unitKey);
		securityRuntimeConfigurator.accept(ClientApi_authAuthenticator.class).configure(client);

		return client.wrap(UnitUnitKeyResource.class).getUnitByKey();
	}

}
