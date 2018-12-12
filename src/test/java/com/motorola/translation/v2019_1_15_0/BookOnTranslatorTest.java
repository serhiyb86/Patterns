/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.translation.v2019_1_15_0;

import com.google.gson.JsonObject;
import com.motorola.models.representation.UserSession;
import com.motorola.models.representation.UserSessionWrapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Contains unit tests related to the BookOn translation.
 */
public class BookOnTranslatorTest extends TranslatorTest {

	private JsonObject bookOnJson;
	private String bookOnInputName = "bookOnInput.json";

	@Before
	public void setup() {
		bookOnJson = initInputPayload(bookOnInputName);
	}

	@Test
	public void translateBookOn_validData_Test() {
		UserSessionWrapper wrapper = getTranslator().translateBookOn(bookOnJson);
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
}
