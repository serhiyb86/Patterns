/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.translation.setter;

import com.google.gson.JsonElement;
import com.motorola.utils.CadCloudUtils;

import java.util.function.BiConsumer;

/**
 * Implements {@link Setter} for {@link Boolean} object field
 *
 * @param <T> Target object class
 */
public class BooleanSetter<T> implements Setter<T> {

	private final BiConsumer<T, Boolean> setFunction;

	public BooleanSetter(BiConsumer<T, Boolean> setFunction) {
		this.setFunction = setFunction;
	}

	@Override
	public void accept(T model, Object value) {
		Boolean booleanValue = CadCloudUtils.getBooleanFromJsonElement((JsonElement) value);
		if (booleanValue != null) {
			setFunction.accept(model, Boolean.valueOf(booleanValue));
		}
	}
}
