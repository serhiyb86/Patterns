package com.models.resource.client;

import com.models.Config;
import com.models.representation.ApiResponse;
import com.models.representation.UserSession;
import com.models.resource.UserSessionSessionIdResource;
import com.models.security.SecurityRuntimeConfigurator;
import com.models.security.authenticators.defined.ClientApi_authAuthenticator;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

public class UserSessionSessionIdClientResource {

	private final SecurityRuntimeConfigurator securityRuntimeConfigurator;

	private String sessionId;

	private final String absolutePath;

	/**
	 * Constructor.
	 *
	 * @param config
	 *            Gathers configuration of the resource URI and security.
	 * @param sessionId
	 *            SessionID to retrieve a posted response data
	 */
	public UserSessionSessionIdClientResource(Config config, String sessionId) {
		this.securityRuntimeConfigurator = config.getSecurityConfig().getSecurityRuntimeConfigurator();
		this.sessionId = sessionId;
		this.absolutePath = config.getBasePath() + "/userSession/{sessionId}";
	}

	/**
	 * Get a user session book on response from Cosmos DB API.
	 *
	 * @return {@link UserSession}
	 * @throws ResourceException if the call to the API fails
	 */
	public UserSession userSession() {
		ClientResource client = new ClientResource(absolutePath);
		client.setAttribute("sessionId", this.sessionId);
		securityRuntimeConfigurator.accept(ClientApi_authAuthenticator.class).configure(client);

		return client.wrap(UserSessionSessionIdResource.class).userSession();
	}

	/**
	 * Perform user book off CAD function.
	 *
	 * @return {@link ApiResponse}
	 * @throws ResourceException if the call to the API fails
	 */
	public ApiResponse bookOff() {
		ClientResource client = new ClientResource(absolutePath);
		client.setAttribute("sessionId", this.sessionId);
		securityRuntimeConfigurator.accept(ClientApi_authAuthenticator.class).configure(client);

		return client.wrap(UserSessionSessionIdResource.class).bookOff();
	}

}
