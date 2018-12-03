/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.translation.v2019_1.mappers;

import com.google.gson.JsonObject;
import com.motorola.translation.setter.Setter;

import java.util.Map;

/**
 * Mapper for setting the incoming data to the <T> model.
 *
 * @param <T> target object class.
 */
public class GenericMapper<T> {

	private Map<String, Setter<T>> setters;

	public GenericMapper(Map<String, Setter<T>> setters) {
		this.setters = setters;
	}

	/**
	 * Fills passed <T> model with mapped data from the incoming json data.
	 *
	 * @param data json data.
	 * @param model model to fill.
	 * @return filled <T> model.
	 */
	public T mapToModel(JsonObject data, T model) {
		if (data != null) {
			data.entrySet().forEach(entry -> {
				Setter<T> consumer = setters.get(entry.getKey());
				if (consumer != null) {
					consumer.accept(model, entry.getValue());
				}
			});
		}
		return model;
	}

}
