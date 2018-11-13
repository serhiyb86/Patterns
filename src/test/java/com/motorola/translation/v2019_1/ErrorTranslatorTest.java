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
 * Contains unit tests related to the Error translation.
 */
public class ErrorTranslatorTest extends TranslatorTest {

	private JsonObject errorJson;
	private String errorInputName = "error.json";

	@Before
	public void setup() {
		errorJson = initInputPayload(errorInputName);
	}

	@Test
	public void translateErrorNotification_validData_Test() {
		ResponseNotification notification = getTranslator().translateErrorNotification(errorJson);
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

}
