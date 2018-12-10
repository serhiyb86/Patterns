package com.motorola.models.resource.client;

import com.motorola.models.Config;
import com.motorola.models.representation.ProvisionItemList;
import com.motorola.models.resource.PushProvisionResource;
import com.motorola.models.security.SecurityRuntimeConfigurator;
import com.motorola.models.security.authenticators.defined.Api_keyAuthenticator;

public class PushProvisionClientResource {

	private final SecurityRuntimeConfigurator securityRuntimeConfigurator;

	private final java.lang.String absolutePath;

	/**
	 * Constructor.
	 *
	 * @param config
	 *            Gathers configuration of the resource URI and security.
	 */
	public PushProvisionClientResource(Config config) {
		this.securityRuntimeConfigurator = config.getSecurityConfig().getSecurityRuntimeConfigurator();
		this.absolutePath = config.getBasePath() + "/push/provision";
	}

	/**
	 * Receives provision lookup item update request - insert or update.
	 *
	 * @param bean
	 *            Parameter "bean"
	 * @throws org.restlet.resource.ResourceException if the call to the API fails
	 */
	public void provisionItemUpdate(ProvisionItemList bean) {
		org.restlet.resource.ClientResource client = new org.restlet.resource.ClientResource(absolutePath);
		securityRuntimeConfigurator.accept(Api_keyAuthenticator.class).configure(client);

		client.wrap(PushProvisionResource.class).provisionItemUpdate(bean);
	}

}
