/*
 * Copyright 2019 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.models.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.motorola.models.Config;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Uses for Jackson serialization of LocalDate fields.
 */
public class LocalDateSerializer extends StdSerializer<LocalDate> {

	private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(Config.DATETIME_FORMAT);

	protected LocalDateSerializer() {
		super(LocalDate.class);
	}

	@Override
	public void serialize(LocalDate localDate, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
		ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneOffset.UTC);
		jsonGenerator.writeString(zonedDateTime.format(dateTimeFormatter));
	}
}
