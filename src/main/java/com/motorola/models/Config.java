package com.motorola.models;

import java.text.DateFormat;

public class Config {

	/**
	 * Pattern used for serialization and deserialization of values of type "datetime" in payloads and query parameters.
	 * Note that timestamps are still accepted as input for deserialization.
	 *
	 * @see DateFormat
	 */
	public static final String DATETIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
}
