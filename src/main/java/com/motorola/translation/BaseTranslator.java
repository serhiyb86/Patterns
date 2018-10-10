/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.translation;

import com.google.gson.JsonObject;
import com.motorola.models.representation.BookOffParameters;
import com.motorola.models.representation.BookOnParameters;

/**
 * Interface with common translation functions
 */
public interface BaseTranslator {

	BookOnParameters translateBookOnParameters(JsonObject payload);

	BookOffParameters translateBookOffParameters(JsonObject payload);
}
