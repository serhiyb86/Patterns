/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.translation.setter;

import com.google.gson.JsonElement;
import com.motorola.utils.CadCloudUtils;

import java.util.function.BiConsumer;

/**
 * Implements {@link Setter} for String object field
 *
 * @param <T> Target object class
 */
public class StringSetter<T> implements Setter<T> {

	private final BiConsumer<T, String> setFunction;

	public StringSetter(BiConsumer<T, String> setFunction) {
		this.setFunction = setFunction;
	}

	@Override
	public void accept(T model, Object value) {
		setFunction.accept(model, CadCloudUtils.getStringFromJsonElement((JsonElement) value));
	}

}