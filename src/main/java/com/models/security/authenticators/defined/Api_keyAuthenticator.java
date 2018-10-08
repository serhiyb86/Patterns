package com.models.security.authenticators.defined;

import com.models.security.authenticators.HeaderApiKeyAuthenticator;

public class Api_keyAuthenticator extends HeaderApiKeyAuthenticator {

    public Api_keyAuthenticator(String token) {
        super("api_key", token);
    }

}
