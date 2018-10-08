package com.models.resource.client;

import com.models.Config;
import com.models.representation.ApiResponse;
import com.models.representation.MonitorAreaList;
import com.models.resource.UserSessionSessionIdMonitorAreasResource;
import com.models.security.SecurityRuntimeConfigurator;
import com.models.security.authenticators.defined.ClientApi_authAuthenticator;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

public class UserSessionSessionIdMonitorAreasClientResource {

	private final SecurityRuntimeConfigurator securityRuntimeConfigurator;

	private String sessionId;

	private final String absolutePath;

	/**
	 * Constructor.
	 *
	 * @param config
	 *            Gathers configuration of the resource URI and security.
	 * @param sessionId
	 *            SessionID to retrieve the monitor areas for the session
	 */
	public UserSessionSessionIdMonitorAreasClientResource(Config config, String sessionId) {
		this.securityRuntimeConfigurator = config.getSecurityConfig().getSecurityRuntimeConfigurator();
		this.sessionId = sessionId;
		this.absolutePath = config.getBasePath() + "/userSession/{sessionId}/monitorAreas";
	}

	/**
	 * Get the monitor Areas by sessionId from Cosmos DB API.
	 *
	 * @return {@link MonitorAreaList}
	 * @throws ResourceException if the call to the API fails
	 */
	public MonitorAreaList getMonitorAreas() {
		ClientResource client = new ClientResource(absolutePath);
		client.setAttribute("sessionId", this.sessionId);
		securityRuntimeConfigurator.accept(ClientApi_authAuthenticator.class).configure(client);

		return client.wrap(UserSessionSessionIdMonitorAreasResource.class).getMonitorAreas();
	}

	/**
	 * Replace the monitor areas by sessionId to Cosmos DB API.
	 *
	 * @param bean
	 *            Parameter "bean"
	 * @return {@link ApiResponse}
	 * @throws ResourceException if the call to the API fails
	 */
	public ApiResponse putMonitorAreas(MonitorAreaList bean) {
		ClientResource client = new ClientResource(absolutePath);
		client.setAttribute("sessionId", this.sessionId);
		securityRuntimeConfigurator.accept(ClientApi_authAuthenticator.class).configure(client);

		return client.wrap(UserSessionSessionIdMonitorAreasResource.class).putMonitorAreas(bean);
	}

}
