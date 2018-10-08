package com.models.security.authenticators.defined;

import com.models.security.authenticators.OAuth2ImplicitFlowAuthenticator;

public class ClientApi_authAuthenticator extends OAuth2ImplicitFlowAuthenticator {

    public ClientApi_authAuthenticator(String clientId) {
        super();
        setClientId(clientId);
        setAuthorizationUri("https://idmbsr4.imw.motorolasolutions.com:9041");
        setScope("read:incident read:response read:unit read:userSession write:incidentComment write:request write:unit write:updateUnitStatus write:userSession");
    }
}
