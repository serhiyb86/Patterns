/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.StringWriter;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Utility class for the Cad Cloud Interface
 */
public class CadCloudUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger(CadCloudUtils.class);
	private static final JsonParser jsonParser = new JsonParser();

	/**
	 * Method that extracts json payload from the http request
	 * @param request request to extract
	 * @return json object of the payload
	 */
	public static final JsonObject extractPayloadFromHttpRequest(HttpServletRequest request) {
		StringWriter responseWriter = new StringWriter();
		try {
			IOUtils.copy(request.getInputStream(), responseWriter, UTF_8);
		}
		catch (IOException e) {
			LOGGER.error("Error has happen during receiving the payload: {}", e);
		}
		return jsonParser.parse(responseWriter.toString()).getAsJsonObject();
	}
}
