package com.motorola.models.resource.client;

import com.motorola.models.Config;
import com.motorola.models.representation.ApiResponse;
import com.motorola.models.representation.UnitHandle;
import com.motorola.models.resource.IncidentIncidentKeyDispatchesDispatchKeyUnitResource;
import com.motorola.models.security.SecurityRuntimeConfigurator;
import com.motorola.models.security.authenticators.defined.ClientApi_authAuthenticator;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

public class IncidentIncidentKeyDispatchesDispatchKeyUnitClientResource {

    private final SecurityRuntimeConfigurator securityRuntimeConfigurator;

    private String incidentKey;

    private String dispatchKey;

    private final String absolutePath;

    /**
     * Constructor.
     * 
     * @param config
     *            Gathers configuration of the resource URI and security. 
     * @param incidentKey
     *            The incident to be dispatched
     * @param dispatchKey
     *            The dispatch that will have the units modified
     */
    public IncidentIncidentKeyDispatchesDispatchKeyUnitClientResource(Config config, String incidentKey, String dispatchKey) {
        this.securityRuntimeConfigurator = config.getSecurityConfig().getSecurityRuntimeConfigurator();
        this.incidentKey = incidentKey;
        this.dispatchKey = dispatchKey;
        this.absolutePath = config.getBasePath() + "/incident/{incidentKey}/dispatches/{dispatchKey}/unit";
    }

    /**
     * Dispatches a unit.
     * 
     * @param bean
     *            Parameter "bean"
     * @return {@link ApiResponse}
     * @throws ResourceException if the call to the API fails
     */
    public ApiResponse dispatchUnit(UnitHandle bean) {
        ClientResource client = new ClientResource(absolutePath);
        client.setAttribute("incidentKey", this.incidentKey);
        client.setAttribute("dispatchKey", this.dispatchKey);
        securityRuntimeConfigurator.accept(ClientApi_authAuthenticator.class).configure(client);

        return client.wrap(IncidentIncidentKeyDispatchesDispatchKeyUnitResource.class).dispatchUnit(bean);
    }

}
