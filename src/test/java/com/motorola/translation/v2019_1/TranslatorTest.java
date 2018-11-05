/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.translation.v2019_1;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.motorola.constants.InterfaceConstants;
import com.motorola.models.representation.ResponseNotification;
import com.motorola.models.representation.UserSession;
import com.motorola.models.representation.UserSessionWrapper;
import com.motorola.translation.BaseTranslator;
import com.motorola.translation.TranslatorsFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;

/**
 * Unit test for verify translator.
 */
public class TranslatorTest {

	private BaseTranslator translator;
	private JsonObject bookOnJson;
	private String bookOnInputName = "bookOnInput.json";
	private JsonObject bookOffJson;
	private String bookOffInputName = "bookOffInput.json";
	private JsonObject errorJson;
	private String errorInputName = "error.json";

	@Before
	public void setup() {
		// init transformation
		translator = TranslatorsFactory.getTranslator(InterfaceConstants.VERSION_2019_1);
		//init input json object
		bookOnJson = initInputPayload(bookOnInputName);
		bookOffJson = initInputPayload(bookOffInputName);
		errorJson = initInputPayload(errorInputName);
	}

	@Test
	public void transformationBookOnTest() {
		UserSessionWrapper wrapper = translator.translateBookOn(bookOnJson);
		UserSession model = wrapper.getModel();
		Assert.assertEquals("Correlation id translation failed", "myCorrelationId", wrapper.getCorrelationId());
		Assert.assertEquals("Customer id translation failed", "myCustomerId", model.getCustomerId());
		Assert.assertEquals("Session id translation failed", "mySessionID", model.getSessionId());
		Assert.assertEquals("User id translation failed", "myUserId", model.getUserId());
		Assert.assertEquals("Device id translation failed", "myDeviceID", model.getDeviceId());
		Assert.assertEquals("Device id translation failed", "user", model.getRoleKey());
		Assert.assertEquals("Api access list size translation failed", 2, model.getApiAccessList().size());
		Assert.assertNotNull("Empty create date after translation", model.getWhenSessionCreated());
		Assert.assertNotNull("Empty updat date after translation", model.getWhenSessionUpdated());
	}

	@Test
	public void transformationBookOffTest() {
		ResponseNotification notification = translator.translateBookOff(bookOffJson);
		Assert.assertEquals("Service Id translation failed", "bookOFF_SERVICE_ID", notification.getServiceId());
		Assert.assertEquals("Customer Id translation failed", "testedCustomerId", notification.getCustomerId());
		Assert.assertEquals("Correlation Id translation failed", "myCorrelationId", notification.getCorrelationId());
		Assert.assertEquals("Notification type Id translation failed", "Response", notification.getNotificationType());
		Assert.assertEquals("When submitted translation failed", "2018-10-31T18:05:58.203Z", notification.getWhenSubmitted());
		Assert.assertEquals("Session Id translation failed", "testedSession", notification.getSessionId());
		Assert.assertEquals("Result nature translation failed", "Success", notification.getResultNature());
	}

	@Test
	public void transformationErrorTest() {
		ResponseNotification notification = translator.translateErrorNotification(errorJson);
		Assert.assertEquals("Service Id translation failed", "475IdSer", notification.getServiceId());
		Assert.assertEquals("Customer Id translation failed", "Cust1488SR", notification.getCustomerId());
		Assert.assertEquals("Correlation Id translation failed", "88844-859-53", notification.getCorrelationId());
		Assert.assertEquals("Notification type Id translation failed", "Response", notification.getNotificationType());
		Assert.assertEquals("When submitted translation failed", "2018-10-31T18:05:58.203Z", notification.getWhenSubmitted());
		Assert.assertEquals("Session Id translation failed", "string", notification.getSessionId());
		Assert.assertEquals("Result nature translation failed", "Failure", notification.getResultNature());
		Assert.assertNotNull("Empty error, failed translate error object", notification.getError());
		Assert.assertEquals("Error code translation failed", "HTTP", notification.getError().getErrorCode());
		Assert.assertEquals("Error message translation failed", "connect failed", notification.getError().getMessage());
	}

	private JsonObject initInputPayload(String inputName) {
		JsonObject result = null;
		//  read the file input for current translator version (2019.1)
		String path = InterfaceConstants.VERSION_2019_1 + "/" + inputName;
		File file = getResourceFile(path);
		JsonParser parser = new JsonParser();
		try {
			result = (JsonObject) parser.parse(new FileReader(file));
		}
		catch (FileNotFoundException ex) {
			System.err.println("Parsing input json file error.");
		}

		return result;
	}

	public final File getResourceFile(String name) {
		URL resource = getClass().getClassLoader().getResource(name);
		if (resource == null) {
			return null;
		}

		return new File(resource.getFile());
	}
}
