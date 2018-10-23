package com.motorola.models.resource.client;

import com.motorola.models.Config;
import com.motorola.models.representation.ApiResponse;
import com.motorola.models.representation.ResponseNotification;
import com.motorola.models.resource.ResponseNotificationResource;
import com.motorola.models.security.SecurityRuntimeConfigurator;
import com.motorola.models.security.authenticators.defined.Api_keyAuthenticator;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

public class ResponseNotificationClientResource {

    private final SecurityRuntimeConfigurator securityRuntimeConfigurator;

    private final String absolutePath;

    /**
     * Constructor.
     * 
     * @param config
     *            Gathers configuration of the resource URI and security. 
     */
    public ResponseNotificationClientResource(Config config) {
        this.securityRuntimeConfigurator = config.getSecurityConfig().getSecurityRuntimeConfigurator();
        this.absolutePath = config.getBasePath() + "/response/notification";
    }

    /**
     * On Prem CAD Services to post a generic response notification to cloud CAD2Mobile Cloud On Prem API service. This notification only goes to Service Bus.
     * 
     * @param bean
     *            Parameter "bean"
     * @return {@link ApiResponse}
     * @throws ResourceException if the call to the API fails
     */
    public ApiResponse responseNotification(ResponseNotification bean) {
        ClientResource client = new ClientResource(absolutePath);
        securityRuntimeConfigurator.accept(Api_keyAuthenticator.class).configure(client);

        return client.wrap(ResponseNotificationResource.class).responseNotification(bean);
    }

}
