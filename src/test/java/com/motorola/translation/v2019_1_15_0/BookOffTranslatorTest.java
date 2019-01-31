/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.translation.v2019_1_15_0;

import com.google.gson.JsonObject;
import com.motorola.constants.InterfaceConstants;
import com.motorola.models.representation.ResponseNotification;
import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Contains unit tests related to the BookOff translation.
 */
public class BookOffTranslatorTest extends TranslatorTest {

	private final SimpleDateFormat zonedDateTimeFormat = new SimpleDateFormat(InterfaceConstants.GeneralProperties.ZONED_DATE_TIME_FORMAT);

	@Test
	public void translateBookOff_validData_Test() throws ParseException {
		JsonObject bookOffJson = initInputPayload("bookOffInput.json");
		ResponseNotification notification = getTranslator().translateResponseNotification(bookOffJson);
		Assert.assertEquals("Service Id translation failed", "bookOFF_SERVICE_ID", notification.getServiceId());
		Assert.assertEquals("Customer Id translation failed", "testedCustomerId", notification.getCustomerId());
		Assert.assertEquals("Correlation Id translation failed", "myCorrelationId", notification.getCorrelationId());
		Assert.assertEquals("Notification type Id translation failed", "Response", notification.getNotificationType());
		Assert.assertEquals("When submitted translation failed", zonedDateTimeFormat.parse("2018-10-31T18:05:58.203Z"), notification.getWhenSubmitted());
		Assert.assertEquals("Session Id translation failed", "testedSession", notification.getSessionId());
		Assert.assertEquals("Result nature translation failed", "Success", notification.getResultNature());
	}

}
