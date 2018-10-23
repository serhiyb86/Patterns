package com.motorola.models.resource.client;

import com.motorola.models.Config;
import com.motorola.models.representation.Unit;
import com.motorola.models.representation.UpdateUnit;
import com.motorola.models.resource.PushUnitResource;
import com.motorola.models.security.SecurityRuntimeConfigurator;
import com.motorola.models.security.authenticators.defined.Api_keyAuthenticator;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

public class PushUnitClientResource {

    private final SecurityRuntimeConfigurator securityRuntimeConfigurator;

    private final String absolutePath;

    /**
     * Constructor.
     * 
     * @param config
     *            Gathers configuration of the resource URI and security. 
     */
    public PushUnitClientResource(Config config) {
        this.securityRuntimeConfigurator = config.getSecurityConfig().getSecurityRuntimeConfigurator();
        this.absolutePath = config.getBasePath() + "/push/unit";
    }

    /**
     * Receives unit status update request.
     * 
     * @param bean
     *            Parameter "bean"
     * @throws ResourceException if the call to the API fails
     */
    public void unitStatusUpdates(UpdateUnit bean) {
        ClientResource client = new ClientResource(absolutePath);
        securityRuntimeConfigurator.accept(Api_keyAuthenticator.class).configure(client);

        client.wrap(PushUnitResource.class).unitStatusUpdates(bean);
    }

    /**
     * Receives new incident.
     * 
     * @param bean
     *            Parameter "bean"
     * @throws ResourceException if the call to the API fails
     */
    public void onDutyUnit(Unit bean) {
        ClientResource client = new ClientResource(absolutePath);
        securityRuntimeConfigurator.accept(Api_keyAuthenticator.class).configure(client);

        client.wrap(PushUnitResource.class).onDutyUnit(bean);
    }

    /**
     * Receives Off-duty unit status update request.
     * 
     * @throws ResourceException if the call to the API fails
     */
    public void offDutyUnit() {
        ClientResource client = new ClientResource(absolutePath);
        securityRuntimeConfigurator.accept(Api_keyAuthenticator.class).configure(client);

        client.wrap(PushUnitResource.class).offDutyUnit();
    }

}
