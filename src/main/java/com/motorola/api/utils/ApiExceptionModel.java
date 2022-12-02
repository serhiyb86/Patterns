/*
 * Copyright 2020 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.api.utils;

/**
 * Represents ApiException data that should be included to the CadCloudAdapter Response
 */
public class ApiExceptionModel extends ExceptionModel {

	private int code;
	private String responseBody;

	public ApiExceptionModel(ApiException exception) {
		super(exception.getMessage());
		this.code = exception.getCode();
		this.responseBody = exception.getResponseBody();
	}

	public int getCode() {
		return code;
	}

	public String getResponseBody() {
		return responseBody;
	}

}
