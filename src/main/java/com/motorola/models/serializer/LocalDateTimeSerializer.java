/*
 * Copyright 2019 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.models.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.motorola.models.Config;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Uses for Jackson serialization of LocalDateTime fields.
 */
public class LocalDateTimeSerializer extends StdSerializer<LocalDateTime> {

	private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(Config.DATETIME_FORMAT);

	protected LocalDateTimeSerializer() {
		super(LocalDateTime.class);
	}

	@Override public void serialize(LocalDateTime localDateTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
		ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneOffset.UTC);
		jsonGenerator.writeString(zonedDateTime.format(dateTimeFormatter));
	}
}
