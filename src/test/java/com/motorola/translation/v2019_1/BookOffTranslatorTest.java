/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.translation.v2019_1;

import com.google.gson.JsonObject;
import com.motorola.models.representation.ResponseNotification;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Contains unit tests related to the BookOff translation.
 */
public class BookOffTranslatorTest extends TranslatorTest {

	private JsonObject bookOffJson;
	private String bookOffInputName = "bookOffInput.json";

	@Before
	public void setup() {
		bookOffJson = initInputPayload(bookOffInputName);
	}

	@Test
	public void translateBookOff_validData_Test() {
		ResponseNotification notification = getTranslator().translateBookOff(bookOffJson);
		Assert.assertEquals("Service Id translation failed", "bookOFF_SERVICE_ID", notification.getServiceId());
		Assert.assertEquals("Customer Id translation failed", "testedCustomerId", notification.getCustomerId());
		Assert.assertEquals("Correlation Id translation failed", "myCorrelationId", notification.getCorrelationId());
		Assert.assertEquals("Notification type Id translation failed", "Response", notification.getNotificationType());
		Assert.assertEquals("When submitted translation failed", "2018-10-31T18:05:58.203Z", notification.getWhenSubmitted());
		Assert.assertEquals("Session Id translation failed", "testedSession", notification.getSessionId());
		Assert.assertEquals("Result nature translation failed", "Success", notification.getResultNature());
	}

}
