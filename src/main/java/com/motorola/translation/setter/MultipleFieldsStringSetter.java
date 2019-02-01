/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.translation.setter;

import com.google.gson.JsonElement;
import com.motorola.utils.CadCloudUtils;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;

/**
 * Implements {@link Setter} for array with {@link String} object fields.
 *
 * @param <T> Target object class
 */
public class MultipleFieldsStringSetter<T> implements Setter<T> {

	private final List<BiConsumer<T, String>> setters;

	@SafeVarargs
	public MultipleFieldsStringSetter(BiConsumer<T, String>... setters) {
		this.setters = Arrays.asList(setters);
	}

	@Override public void accept(T model, Object value) {
		setters.forEach(setter -> setter.accept(model, CadCloudUtils.getStringFromJsonElement((JsonElement) value)));
	}
}
