/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */

package com.motorola.translation.v2019_1_15_0.mappers;

import com.google.gson.JsonElement;
import com.motorola.models.representation.Lookup;

public abstract class AbstractMapper {

	/**
	 * Creates {@link Lookup} instance with incoming data.
	 *
	 * @param value incoming data.
	 * @return {@link Lookup} instance.
	 */
	public static Lookup createLookup(JsonElement value) {
		Lookup lookup = new Lookup();
		if (value != null) {
			lookup.setUid(value.getAsString());
		}
		return lookup;
	}
}
