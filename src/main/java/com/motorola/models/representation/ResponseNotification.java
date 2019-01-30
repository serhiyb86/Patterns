package com.motorola.models.representation;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.motorola.models.Config;

import java.io.Serializable;
import java.util.Date;

/**
 * Response notification SB message
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class ResponseNotification implements Serializable {

	/** Default serial version ID. */
	private static final long serialVersionUID = 1L;

	private String serviceId;

	private String customerId;

	private String correlationId;

	private String notificationType;

	private Date whenSubmitted;

	private String sessionId;

	private String resultNature;

	private String responseType;

	private String key;

	private String keyType;

	private ApiError error;

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCorrelationId() {
		return correlationId;
	}

	public void setCorrelationId(String correlationId) {
		this.correlationId = correlationId;
	}

	public String getNotificationType() {
		return notificationType;
	}

	public void setNotificationType(String notificationType) {
		this.notificationType = notificationType;
	}

	/**
	 * Returns the value of property "whenSubmitted".
	 * The time stamp when the book on response notification is submitted
	 */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Config.DATETIME_FORMAT)
	public Date getWhenSubmitted() {
		return whenSubmitted;
	}

	/**
	 * Updates the value of property "whenSubmitted".
	 */
	public void setWhenSubmitted(Date whenSubmitted) {
		this.whenSubmitted = whenSubmitted;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getResultNature() {
		return resultNature;
	}

	public void setResultNature(String resultNature) {
		this.resultNature = resultNature;
	}

	public String getResponseType() {
		return responseType;
	}

	public void setResponseType(String responseType) {
		this.responseType = responseType;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getKeyType() {
		return keyType;
	}

	public void setKeyType(String keyType) {
		this.keyType = keyType;
	}

	public ApiError getError() {
		return error;
	}

	public void setError(ApiError error) {
		this.error = error;
	}

}
