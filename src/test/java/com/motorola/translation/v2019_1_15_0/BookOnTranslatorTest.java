/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.translation.v2019_1_15_0;

import com.google.gson.JsonObject;
import com.motorola.models.Config;
import com.motorola.models.representation.DeviceHandle;
import com.motorola.models.representation.PersonnelHandle;
import com.motorola.models.representation.RoleHandle;
import com.motorola.models.representation.UserSession;
import com.motorola.models.representation.UserSessionWrapper;
import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Contains unit tests related to the BookOn translation.
 */
public class BookOnTranslatorTest extends TranslatorTest {

	private final DateTimeFormatter format = DateTimeFormatter.ofPattern(Config.DATETIME_FORMAT);

	@Test
	public void translateBookOn_validData_Test() throws ParseException {
		JsonObject bookOnJson = initInputPayload("bookOnInput.json");
		UserSessionWrapper wrapper = getTranslator().translateBookOn(bookOnJson);
		UserSession model = wrapper.getModel();
		Assert.assertEquals("Correlation id translation failed", "myCorrelationId", wrapper.getCorrelationId());
		Assert.assertEquals("Customer id translation failed", "myCustomerId", model.getCustomerId());
		Assert.assertEquals("Session id translation failed", "mySessionID", model.getSessionId());
		Assert.assertEquals("Service id translation failed", "test_serviceId", model.getServiceId());
		Assert.assertEquals("Api access list size translation failed", 1, model.getApiAccessList().size());
		Assert.assertEquals("Wrong create date after translation", ZonedDateTime.parse("2019-01-30T12:26:23.771Z", format), model.getWhenSessionCreated());
		Assert.assertEquals("Wrong update date after translation", ZonedDateTime.parse("2019-01-30T12:26:23.771Z", format), model.getWhenSessionUpdated());
		RoleHandle role = model.getRole();
		Assert.assertNotNull(role);
		Assert.assertEquals("role.key translation failed", "user", role.getKey());
		Assert.assertEquals("role.agencyAlias translation failed", "roleAgency", role.getAgencyAlias());
		Assert.assertEquals("role.nameCode translation failed", "roleNameCode", role.getNameCode());
		DeviceHandle device = model.getDevice();
		Assert.assertNotNull(device);
		Assert.assertEquals("device.key translation failed", "deviceKey", device.getKey());
		Assert.assertEquals("device.agencyAlias translation failed", "deviceAgency", device.getAgencyAlias());
		Assert.assertEquals("device.nameCode translation failed", "deviceName", device.getName());
		PersonnelHandle user = model.getUser();
		Assert.assertNotNull(user);
		Assert.assertEquals("user.key translation failed", "userKey", user.getKey());
		Assert.assertEquals("user.agencyAlias translation failed", "userAgency", user.getAgencyAlias());
		Assert.assertEquals("user.nameCode translation failed", "userNameCode", user.getNameCode());
	}
}
