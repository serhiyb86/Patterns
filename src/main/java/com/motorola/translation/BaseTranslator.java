/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.translation;

import com.google.gson.JsonObject;
import com.motorola.models.representation.ResponseNotification;
import com.motorola.models.representation.EmergencyIncident;
import com.motorola.models.representation.UpdateEmergencyIncident;
import com.motorola.models.representation.UserSessionWrapper;
import com.motorola.validation.ValidationResult;

import java.util.List;

/**
 * Interface with common translation functions
 */
public interface BaseTranslator {

	UserSessionWrapper translateBookOn(JsonObject payload);

	ResponseNotification translateBookOff(JsonObject payload);

	ResponseNotification translateErrorNotification(JsonObject payload);

	EmergencyIncident translateCreateIncident(JsonObject payload);

	UpdateEmergencyIncident translateUpdateIncident(JsonObject payload);

	List<ValidationResult> getValidationResults();
}
