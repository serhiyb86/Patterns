package com.motorola.models.resource.client;

import com.motorola.models.Config;
import com.motorola.models.representation.ApiResponse;
import com.motorola.models.representation.UserSession;
import com.motorola.models.resource.ResponseUserSessionCorrelationIdResource;
import com.motorola.models.security.SecurityRuntimeConfigurator;
import com.motorola.models.security.authenticators.defined.Api_keyAuthenticator;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

public class ResponseUserSessionCorrelationIdClientResource {

    private final SecurityRuntimeConfigurator securityRuntimeConfigurator;

    private String correlationId;

    private final String absolutePath;

    /**
     * Constructor.
     * 
     * @param config
     *            Gathers configuration of the resource URI and security. 
     * @param correlationId
     *            Correlation ID for the book on response
     */
    public ResponseUserSessionCorrelationIdClientResource(Config config, String correlationId) {
        this.securityRuntimeConfigurator = config.getSecurityConfig().getSecurityRuntimeConfigurator();
        this.correlationId = correlationId;
        this.absolutePath = config.getBasePath() + "/response/userSession/{correlationId}";
    }

    /**
     * On Prem CAD Services to post a book on response to cloud CAD2Mobile Cloud On Prem API service.
     * 
     * @param bean
     *            Parameter "bean"
     * @return {@link ApiResponse}
     * @throws ResourceException if the call to the API fails
     */
    public ApiResponse bookOnResponse(UserSession bean) {
        ClientResource client = new ClientResource(absolutePath);
        client.setAttribute("correlationId", this.correlationId);
        securityRuntimeConfigurator.accept(Api_keyAuthenticator.class).configure(client);

        return client.wrap(ResponseUserSessionCorrelationIdResource.class).bookOnResponse(bean);
    }

}
