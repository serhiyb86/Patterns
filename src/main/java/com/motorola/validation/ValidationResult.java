package com.motorola.validation;

public class ValidationResult {

	private String message;
	private ValidationType type;

	public ValidationResult(String message, ValidationType type) {
		this.message = message;
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public ValidationType getType() {
		return type;
	}
}
