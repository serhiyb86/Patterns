/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.io.IOUtils;
import org.restlet.engine.util.StringUtils;
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
	private static final JsonParser JSON_PARSER = new JsonParser();
	private static final Gson GSON = new Gson();

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
			LOGGER.error("Error has happen during receiving the payload: {}. ", e);
		}
		return StringUtils.isNullOrEmpty(responseWriter.toString()) ? null : JSON_PARSER.parse(responseWriter.toString()).getAsJsonObject();
	}

	/**
	 * Converts any object to json string
	 * @param object to convert
	 * @return json string
	 */
	public static String convertObjectToJsonString(Object object) {
		if (object != null) {
			return GSON.toJson(object).toString();
		}
		return null;
	}

	/**
	 * Get the value of field inside nested json by key
	 * @param rootJson root json object
	 * @param nestedJsonKey - nested json key
	 * @param fieldKey - field key
	 * @return field string value if exist, otherwise - null
	 */
	public String getStringFromNestedJsonByKey(JsonObject rootJson, String nestedJsonKey, String fieldKey) {
		String result = null;
		if (rootJson != null && rootJson.get(nestedJsonKey) != null) {
			JsonObject nestedObject = rootJson.get(nestedJsonKey).getAsJsonObject();
			if (nestedObject.get(fieldKey) != null) {
				result = nestedObject.get(fieldKey).getAsString();
			}
			else {
				LOGGER.debug("the nested json %s does not contain key: %s. ", nestedJsonKey, fieldKey);
			}
		}
		else {
			LOGGER.debug("Root json does not contain key: %s. ", nestedJsonKey);
		}
		return result;
	}

	/**
	 * Gets the String value from incoming json
	 * @param json - json payload object
	 * @param key - the property key
	 * @return - the value if exist, otherwise - null
	 */
	public String getStringByKey(JsonObject json, String key) {
		String result = null;
		if (json != null && json.get(key) != null) {
			result = json.get(key).getAsString();
		}
		else {
			LOGGER.debug("Json does not contain key: %s for string value. ", key);
		}
		return result;
	}

	/**
	 * Gets JSON value from incoming json
	 * @param json - json payload object
	 * @param key - the property key
	 * @return - the value if exist, otherwise - null
	 */
	public JsonObject getJsonByKey(JsonObject json, String key) {
		JsonObject result = null;
		if (json != null && json.get(key) != null) {
			result = json.get(key).getAsJsonObject();
		}
		else {
			LOGGER.debug("Json does not contain key: %s for json value. ", key);
		}
		return result;
	}

	/**
	 * Gets Json array from json object by key
	 * @param json object to get array
	 * @param key of the array object
	 * @return json array
	 */
	public JsonArray getJsonArrayByKey(JsonObject json, String key) {
		JsonArray result = null;
		if (json != null && json.get(key) != null) {
			result = json.get(key).getAsJsonArray();
		}
		else {
			LOGGER.debug("Json does not contain key: %s for json array value. ", key);
		}
		return result;
	}
}
