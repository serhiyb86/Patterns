/*
 * Copyright 2020 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.api.utils;

/**
 * Represents Exception data that should be included to the CadCloudAdapter Response
 */
public class ExceptionModel {

	private String message;

	public ExceptionModel(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
