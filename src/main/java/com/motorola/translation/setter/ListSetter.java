/*
 * Copyright 2019 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.translation.setter;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.motorola.translation.v2019_1_15_0.mappers.GenericMapper;

import java.util.Collections;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * {@link Setter} implementation for setting inner models {@link List}
 * @param <T> outer model
 * @param <U> inner model
 */
public class ListSetter<T, U> implements Setter<T> {

	private final BiConsumer<T, List<U>> setFunction;
	private final GenericMapper<U> mapper;
	private final Supplier<U> objectSupplier;

	public ListSetter(BiConsumer<T, List<U>> setFunction, GenericMapper<U> mapper, Supplier<U> objectSupplier) {
		this.setFunction = setFunction;
		this.mapper = mapper;
		this.objectSupplier = objectSupplier;
	}

	@Override
	public void accept(T model, Object value) {
		if (value == null) {
			setFunction.accept(model, Collections.emptyList());
			return;
		}
		List<U> result = StreamSupport.stream(((JsonArray) value).spliterator(), false)
			.map(JsonElement::getAsJsonObject)
			.map(jsonObject -> mapper.mapToModel(jsonObject, objectSupplier.get()))
			.collect(Collectors.toList());
		setFunction.accept(model, result);
	}

}
