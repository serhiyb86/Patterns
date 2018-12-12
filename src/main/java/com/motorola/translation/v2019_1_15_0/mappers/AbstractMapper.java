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
		if (value != null) {
			return createLookup(value.getAsString());
		}
		return new Lookup();
	}
	/**
	 * Creates {@link Lookup} instance with incoming string value.
	 *
	 * @param value incoming data.
	 * @return {@link Lookup} instance.
	 */
	public static Lookup createLookup(String value) {
		Lookup lookup = new Lookup();
		lookup.setUid(value);
		return lookup;

	}
}
