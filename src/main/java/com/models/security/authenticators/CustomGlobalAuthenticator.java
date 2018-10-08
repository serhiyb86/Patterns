package com.models.security.authenticators;

import com.models.security.SecurityConfig;
import org.restlet.resource.ClientResource;

/**
 * A marker class that serves as the id of the Authenticator that can be configured through
 * {@link SecurityConfig#configureCustomGlobalAuth}.
 */
public class CustomGlobalAuthenticator implements Authenticator {

    @Override
    public void configure(ClientResource clientResource) {
        // nothing
    }

}