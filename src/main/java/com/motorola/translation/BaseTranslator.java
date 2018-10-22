/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.translation;

import com.google.gson.JsonObject;
import com.motorola.models.representation.BookOffParameters;
import com.motorola.models.representation.ResponseNotification;
import com.motorola.models.representation.UserSessionWrapper;

/**
 * Interface with common translation functions
 */
public interface BaseTranslator {

	UserSessionWrapper translateBookOn(JsonObject payload);

	ResponseNotification translateBookOff(JsonObject payload);
}
