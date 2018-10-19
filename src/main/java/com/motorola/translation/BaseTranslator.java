/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.translation;

import com.google.gson.JsonObject;
import com.motorola.models.representation.BookOffParameters;
import com.motorola.models.representation.UserSession;

/**
 * Interface with common translation functions
 */
public interface BaseTranslator {

	UserSession translateBookOn(JsonObject payload);

	BookOffParameters translateBookOffParameters(JsonObject payload);
}
