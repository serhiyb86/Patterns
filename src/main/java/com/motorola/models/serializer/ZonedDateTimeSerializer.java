/*
 * Copyright 2019 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.models.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.motorola.models.Config;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Uses for Jackson serialization of ZonedDateTime fields.
 */
public class ZonedDateTimeSerializer extends StdSerializer<ZonedDateTime> {

	private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(Config.DATETIME_FORMAT);

	protected ZonedDateTimeSerializer() {
		super(ZonedDateTime.class);
	}

	@Override
	public void serialize(ZonedDateTime zonedDateTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
		jsonGenerator.writeString(zonedDateTime.format(dateTimeFormatter));
	}
}
