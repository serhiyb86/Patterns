/*
 * Copyright 2019 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.translation.setter;

import com.google.gson.JsonElement;
import com.motorola.utils.CadCloudUtils;
import java.util.function.ObjDoubleConsumer;

/**
 * Implements {@link Setter} for {@link Double} object field
 *
 * @param <T> Target object class
 */
public class DoubleSetter<T> implements Setter<T> {

	private final ObjDoubleConsumer<T> setFunction;

	public DoubleSetter(ObjDoubleConsumer<T> setFunction) {
		this.setFunction = setFunction;
	}

	@Override
	public void accept(T model, Object value) {
		Double doubleValue = CadCloudUtils.getDoubleFromJsonElement((JsonElement) value);
		if (doubleValue != null) {
			setFunction.accept(model, doubleValue);
		}
	}
}
