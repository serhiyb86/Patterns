/*
 * Copyright 2019 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.translation.setter;

import com.google.gson.JsonElement;
import com.motorola.utils.CadCloudUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.BiConsumer;

/**
 * Implements {@link Setter} for {@link ZonedDateTimeSetter} object field
 *
 * @param <T> Target object class
 */
public class ZonedDateTimeSetter<T> implements Setter<T> {
	private static final Logger LOGGER = LoggerFactory.getLogger(ZonedDateTimeSetter.class);

	private final BiConsumer<T, ZonedDateTime> setFunction;
	private final DateTimeFormatter dateTimeFormatter;

	public ZonedDateTimeSetter(BiConsumer<T, ZonedDateTime> setFunction, String formatter) {
		this.dateTimeFormatter = DateTimeFormatter.ofPattern(formatter);
		this.setFunction = setFunction;
	}

	@Override
	public void accept(T model, Object value) {
		String zonedDateTimeString = CadCloudUtils.getStringFromJsonElement((JsonElement) value);
		try {
			setFunction.accept(model, ZonedDateTime.parse(zonedDateTimeString, dateTimeFormatter));
		}
		catch (Exception e) {
			LOGGER.error("Failed to parse incoming date {}", zonedDateTimeString, e);
		}
	}

}
