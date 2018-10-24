/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.validation;

public class ValidationResult {

	private String message;
	private ValidationErrorType type;

	public ValidationResult(String message, ValidationErrorType type) {
		this.message = message;
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public ValidationErrorType getType() {
		return type;
	}
}
