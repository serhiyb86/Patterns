package com.models.resource.client;

import com.models.Config;
import com.models.representation.ApiResponse;
import com.models.representation.IncidentComment;
import com.models.resource.IncidentIncidentKeyCommentResource;
import com.models.security.SecurityRuntimeConfigurator;
import com.models.security.authenticators.defined.ClientApi_authAuthenticator;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

public class IncidentIncidentKeyCommentClientResource {

	private final SecurityRuntimeConfigurator securityRuntimeConfigurator;

	private String incidentKey;

	private final String absolutePath;

	/**
	 * Constructor.
	 *
	 * @param config
	 *            Gathers configuration of the resource URI and security.
	 * @param incidentKey
	 *            The incident to be dispatched
	 */
	public IncidentIncidentKeyCommentClientResource(Config config, String incidentKey) {
		this.securityRuntimeConfigurator = config.getSecurityConfig().getSecurityRuntimeConfigurator();
		this.incidentKey = incidentKey;
		this.absolutePath = config.getBasePath() + "/incident/{incidentKey}/comment";
	}

	/**
	 *
	 *
	 * @param bean
	 *            Parameter "bean"
	 * @return {@link ApiResponse}
	 * @throws ResourceException if the call to the API fails
	 */
	public ApiResponse addIncidentComment(IncidentComment bean) {
		ClientResource client = new ClientResource(absolutePath);
		client.setAttribute("incidentKey", this.incidentKey);
		securityRuntimeConfigurator.accept(ClientApi_authAuthenticator.class).configure(client);

		return client.wrap(IncidentIncidentKeyCommentResource.class).addIncidentComment(bean);
	}

}
