/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.translation.setter;

import com.google.gson.JsonElement;
import com.motorola.utils.CadCloudUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.function.BiConsumer;

/**
 * Implements {@link Setter} for {@link Long} object field.
 *
 * @param <T> Target object class
 */
public class LongSetter<T> implements Setter<T> {

	private final BiConsumer<T, Long> setFunction;

	public LongSetter(BiConsumer<T, Long> setFunction) {
		this.setFunction = setFunction;
	}

	@Override
	public void accept(T model, Object value) {
		String stringValue = CadCloudUtils.getStringFromJsonElement((JsonElement) value);
		if (StringUtils.isNumeric(stringValue)) {
			setFunction.accept(model, Long.valueOf(stringValue));
		}
	}

}