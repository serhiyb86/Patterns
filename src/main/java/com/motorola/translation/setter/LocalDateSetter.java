/*
 * Copyright 2019 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.translation.setter;

import com.google.gson.JsonElement;
import com.motorola.utils.CadCloudUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.function.BiConsumer;

/**
 * Implements {@link Setter} for {@link LocalDate} object field
 *
 * @param <T> Target object class
 */
public class LocalDateSetter<T> implements Setter<T> {
	private static final Logger LOGGER = LoggerFactory.getLogger(LocalDateSetter.class);

	private final BiConsumer<T, LocalDate> setFunction;
	private DateTimeFormatter dateFormat;

	public LocalDateSetter(BiConsumer<T, LocalDate> setFunction, String dateFormatter) {
		this.dateFormat = DateTimeFormatter.ofPattern(dateFormatter);
		this.setFunction = setFunction;
	}


	@Override
	public void accept(T model, Object value) {
		String dateString = CadCloudUtils.getStringFromJsonElement((JsonElement) value);
		try {
			setFunction.accept(model, LocalDate.parse(dateString, dateFormat));
		}
		catch (Exception e) {
			LOGGER.error("Failed to parse incoming date {}", dateString, e);
		}
	}

}
