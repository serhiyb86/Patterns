/*
 * Copyright 2019 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.translation.setter;

import com.google.gson.JsonObject;
import com.motorola.translation.v2019_1_15_0.mappers.GenericMapper;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

/**
 * {@link Setter} implementation for setting inner models
 * @param <T> outer model
 * @param <U> inner model
 */
public class ObjectSetter<T, U> implements Setter<T> {

	private final BiConsumer<T, U> setFunction;
	private final GenericMapper<U> mapper;
	private final Supplier<U> objectSupplier;

	public ObjectSetter(BiConsumer<T, U> setFunction, GenericMapper<U> mapper, Supplier<U> objectSupplier) {
		this.setFunction = setFunction;
		this.mapper = mapper;
		this.objectSupplier = objectSupplier;
	}

	@Override
	public void accept(T model, Object value) {
		if (value == null) {
			setFunction.accept(model, null);
			return;
		}
		U innerModel = objectSupplier.get();
		mapper.mapToModel((JsonObject) value, innerModel);
		setFunction.accept(model, innerModel);
	}

}
