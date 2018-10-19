package com.motorola.models.resource.client;

import com.motorola.models.Config;
import com.motorola.models.representation.EmergencyIncident;
import com.motorola.models.representation.UpdateEmergencyIncident;
import com.motorola.models.resource.PushIncidentResource;

import com.motorola.models.security.SecurityRuntimeConfigurator;
import com.motorola.models.security.authenticators.defined.Api_keyAuthenticator;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

public class PushIncidentClientResource {

    private final SecurityRuntimeConfigurator securityRuntimeConfigurator;

    private final String absolutePath;

    /**
     * Constructor.
     * 
     * @param config
     *            Gathers configuration of the resource URI and security. 
     */
    public PushIncidentClientResource(Config config) {
        this.securityRuntimeConfigurator = config.getSecurityConfig().getSecurityRuntimeConfigurator();
        this.absolutePath = config.getBasePath() + "/push/incident";
    }

    /**
     * Receives incident updates /unsolicited message.
     * 
     * @param bean
     *            Parameter "bean"
     * @param incidentUpdateMsgType
     *            Incident update Message type
     *            Required parameter.
     * @throws ResourceException if the call to the API fails
     */
    public void updateIncident(UpdateEmergencyIncident bean, String incidentUpdateMsgType) {
        ClientResource client = new ClientResource(absolutePath);
        QueryParameterHelper.addQueryParameter(client, "incidentUpdateMsgType", incidentUpdateMsgType);
        securityRuntimeConfigurator.accept(Api_keyAuthenticator.class).configure(client);

        client.wrap(PushIncidentResource.class).updateIncident(bean);
    }

    /**
     * Receives new incident.
     * 
     * @param bean
     *            Parameter "bean"
     * @throws ResourceException if the call to the API fails
     */
    public void createIncident(EmergencyIncident bean) {
        ClientResource client = new ClientResource(absolutePath);
        securityRuntimeConfigurator.accept(Api_keyAuthenticator.class).configure(client);

        client.wrap(PushIncidentResource.class).createIncident(bean);
    }

}
