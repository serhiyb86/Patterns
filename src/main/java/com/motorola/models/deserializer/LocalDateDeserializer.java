/*
 * Copyright 2020 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.models.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.motorola.models.Config;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Uses for Jackson deserialization of LocalDate fields.
 */
public class LocalDateDeserializer extends StdDeserializer<LocalDate> {

	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(Config.LOCAL_DATE_FORMAT);

	protected LocalDateDeserializer() {
		super(LocalDate.class);
	}

	@Override
	public LocalDate deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
		return LocalDate.parse(jsonParser.getText(), dateTimeFormatter);
	}
}
