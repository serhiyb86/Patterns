package com.motorola.models.representation;

/*
The implementation for use call bookOn endpoint
 */
public class UserSessionWrapper {

	private String correlationId;

	private UserSession model;

	public String getCorrelationId() {
		return correlationId;
	}

	public void setCorrelationId(String correlationId) {
		this.correlationId = correlationId;
	}

	public UserSession getModel() {
		return model;
	}

	public void setModel(UserSession model) {
		this.model = model;
	}

}
