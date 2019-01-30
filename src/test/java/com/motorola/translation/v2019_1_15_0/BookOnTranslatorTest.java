/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.translation.v2019_1_15_0;

import com.google.gson.JsonObject;
import com.motorola.constants.InterfaceConstants;
import com.motorola.models.representation.UserSession;
import com.motorola.models.representation.UserSessionWrapper;
import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Contains unit tests related to the BookOn translation.
 */
public class BookOnTranslatorTest extends TranslatorTest {

	private final SimpleDateFormat zonedDateTimeFormat = new SimpleDateFormat(InterfaceConstants.GeneralProperties.ZONED_DATE_TIME_FORMAT);

	@Test
	public void translateBookOn_validData_Test() throws ParseException {
		JsonObject bookOnJson = initInputPayload("bookOnInput.json");
		UserSessionWrapper wrapper = getTranslator().translateBookOn(bookOnJson);
		UserSession model = wrapper.getModel();
		Assert.assertEquals("Correlation id translation failed", "myCorrelationId", wrapper.getCorrelationId());
		Assert.assertEquals("Customer id translation failed", "myCustomerId", model.getCustomerId());
		Assert.assertEquals("Session id translation failed", "mySessionID", model.getSessionId());
		Assert.assertEquals("User id translation failed", "myUserId", model.getUser().getKey());
		Assert.assertEquals("Device id translation failed", "myDeviceID", model.getDevice().getKey());
		Assert.assertEquals("Role translation failed", "user", model.getRole().getKey());
		Assert.assertEquals("Api access list size translation failed", 2, model.getApiAccessList().size());
		Assert.assertEquals("Wrong create date after translation", zonedDateTimeFormat.parse("2018-10-18T10:16:04.663Z"), model.getWhenSessionCreated());
		Assert.assertEquals("Wrong update date after translation", zonedDateTimeFormat.parse("2018-10-18T10:16:04.663Z"), model.getWhenSessionUpdated());
	}
}
