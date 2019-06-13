package com.motorola.models.security.authenticators.defined;

import com.motorola.models.security.authenticators.HeaderApiKeyAuthenticator;

public class Api_keyAuthenticator extends HeaderApiKeyAuthenticator {

    public Api_keyAuthenticator(String token) {
        super("Authorization", "Bearer " + token);
    }

}
