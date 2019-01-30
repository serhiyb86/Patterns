/*
 * Copyright 2019 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.translation.setter;

import com.google.gson.JsonElement;
import com.motorola.constants.InterfaceConstants;
import com.motorola.utils.CadCloudUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.BiConsumer;

/**
 * Implements {@link Setter} for {@link Date} object field
 *
 * @param <T> Target object class
 */
public class DateSetter<T> implements Setter<T> {

	private static final Logger LOGGER = LoggerFactory.getLogger(DateSetter.class);

	private final BiConsumer<T, Date> setFunction;
	private SimpleDateFormat dateFormat;

	public DateSetter(BiConsumer<T, Date> setFunction, String dateFormatter) {
		this.dateFormat = new SimpleDateFormat(dateFormatter);
		this.setFunction = setFunction;
	}


	@Override
	public void accept(T model, Object value) {
		String dateString = CadCloudUtils.getStringFromJsonElement((JsonElement) value);
		try {
			setFunction.accept(model, dateFormat.parse(dateString));
		}
		catch (ParseException e) {
			LOGGER.error("Failed to parse incoming date {}", dateString, e);
		}
	}

}