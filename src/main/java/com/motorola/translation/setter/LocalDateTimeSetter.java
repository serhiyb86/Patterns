/*
 * Copyright 2019 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.translation.setter;

import com.google.gson.JsonElement;
import com.motorola.utils.CadCloudUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.BiConsumer;

/**
 * Implements {@link Setter} for {@link LocalDateTime} object field
 *
 * @param <T> Target object class
 */
public class LocalDateTimeSetter<T> implements Setter<T> {
	private static final Logger LOGGER = LogManager.getLogger(LocalDateTimeSetter.class);

	private final BiConsumer<T, LocalDateTime> setFunction;
	private final DateTimeFormatter dateTimeFormatter;

	public LocalDateTimeSetter(BiConsumer<T, LocalDateTime> setFunction, String dateTimeFormatter) {
		this.dateTimeFormatter = DateTimeFormatter.ofPattern(dateTimeFormatter);
		this.setFunction = setFunction;
	}


	@Override
	public void accept(T model, Object value) {
		String dateTimeString = CadCloudUtils.getStringFromJsonElement((JsonElement) value);
		try {
			setFunction.accept(model, LocalDateTime.parse(dateTimeString, dateTimeFormatter));
		}
		catch (Exception e) {
			LOGGER.error("Failed to parse incoming date {}", dateTimeString, e);
		}
	}

}
