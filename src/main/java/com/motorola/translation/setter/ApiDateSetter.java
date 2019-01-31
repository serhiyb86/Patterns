/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.translation.setter;

import com.google.gson.JsonElement;
import com.motorola.constants.InterfaceConstants;
import com.motorola.translation.v2019_1_15_0.Translator;
import com.motorola.utils.CadCloudUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.BiConsumer;

/**
 * Implements {@link Setter} of Date string representation for CAD API object field
 * Converting the incoming date with "yyyy-MM-dd" format to api date string format yyyy-MM-dd'T'HH:mm:ss.SSS'Z'
 *
 * @param <T> Target object class
 */
public class ApiDateSetter<T> implements Setter<T> {

	private final BiConsumer<T, String> setFunction;
	private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(InterfaceConstants.GeneralProperties.DATE_FORMAT);
	private DateTimeFormatter apiDateFormatter = DateTimeFormatter.ofPattern(InterfaceConstants.GeneralProperties.ZONED_DATE_TIME_FORMAT);
	private static final Logger LOGGER = LoggerFactory.getLogger(Translator.class);

	public ApiDateSetter(BiConsumer<T, String> setFunction) {
		this.setFunction = setFunction;
	}


	@Override
	public void accept(T model, Object value) {
		String dateString = CadCloudUtils.getStringFromJsonElement((JsonElement) value);
		try {
			LocalDate localDate = LocalDate.parse(dateString, dateFormatter);
			LocalDateTime localDateTime = localDate.atStartOfDay();
			setFunction.accept(model, apiDateFormatter.format(localDateTime));
		}
		catch (Exception e) {
			LOGGER.error("Failed to parse incoming date {}", dateString, e);
		}
	}

}