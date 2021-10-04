/*
 * Copyright 2021 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.api.utils;

public class AuthenticationException extends RuntimeException {

	public AuthenticationException(Throwable throwable) {
		super(throwable);
	}

	public AuthenticationException(String message) {
		super(message);
	}
}
