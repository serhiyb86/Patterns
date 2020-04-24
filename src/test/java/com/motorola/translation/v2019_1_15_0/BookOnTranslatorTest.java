/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.translation.v2019_1_15_0;

import com.google.gson.JsonObject;
import com.motorola.models.Config;
import com.motorola.models.representation.AccessScope;
import com.motorola.models.representation.DeviceHandle;
import com.motorola.models.representation.PersonnelHandle;
import com.motorola.models.representation.RoleHandle;
import com.motorola.models.representation.UnitHandle;
import com.motorola.models.representation.UserSession;
import com.motorola.models.representation.UserSessionWrapper;
import com.motorola.utils.OneRmsHashUtils;
import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

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
		Assert.assertEquals("Correlation id translation failed", "correlation_id", wrapper.getCorrelationId());
		Assert.assertEquals("Customer id translation failed", "customer_id", model.getCustomerId());
		Assert.assertEquals("Session id translation failed", "97e11e0c-0e23-4a69-928c-a92ffe33b9f1", model.getSessionId());
		Assert.assertEquals("Service id translation failed", "serviceId_value", model.getServiceId());
		Assert.assertEquals("CadUserAgencyKey translation failed", OneRmsHashUtils.convertCodeToOneRmsFormat("SPD"), model.getCadUserAgencyKey());
		Assert.assertEquals("DeviceAgencyKey translation failed", OneRmsHashUtils.convertCodeToOneRmsFormat("DDD"), model.getDeviceAgencyKey());
		Assert.assertTrue("AreaKeys translation failed", model.getMonitorAreas().getAreaKeys().contains(OneRmsHashUtils.convertCodeToOneRmsFormat("SPNLA")));
		Assert.assertTrue("AreaKeys translation failed", model.getMonitorAreas().getAreaKeys().contains(OneRmsHashUtils.convertCodeToOneRmsFormat("SPNLR")));
		Assert.assertEquals("AreaKeys translation failed", 13, model.getMonitorAreas().getAreaKeys().size());
		List<AccessScope> apiAccessScopeList = model.getApiAccessScope();
		Assert.assertEquals("ApiAccessScope list size translation failed", 1, apiAccessScopeList.size());
		Assert.assertEquals("AgencyKey translation failed", OneRmsHashUtils.convertCodeToOneRmsFormat("SPD"), apiAccessScopeList.get(0).getAgencyKey());
		Assert.assertEquals("ApiAccessList size translation failed", 2, apiAccessScopeList.get(0).getApiAccessList().size());

		Assert.assertEquals("Wrong create date after translation", ZonedDateTime.parse("2019-01-30T12:26:23.771Z", format), model.getWhenSessionCreated());
		Assert.assertEquals("Wrong update date after translation", ZonedDateTime.parse("2019-01-30T12:26:23.771Z", format), model.getWhenSessionUpdated());

		RoleHandle role = model.getRole();
		Assert.assertNotNull(role);
		Assert.assertEquals("role.key translation failed", "user", role.getKey());

		DeviceHandle device = model.getDevice();
		Assert.assertNotNull(device);
		Assert.assertEquals("device.key translation failed", "104", device.getKey());
		Assert.assertEquals("device.agencyAlias translation failed", "DDD", device.getAgencyAlias());

		PersonnelHandle cadUser = model.getCadUser();
		Assert.assertNotNull(cadUser);
		Assert.assertEquals("cadUser.key translation failed", OneRmsHashUtils.convertCodeToOneRmsFormat("SDS"), cadUser.getKey());
		Assert.assertEquals("cadUser.agencyAlias translation failed", "SPD", cadUser.getAgencyAlias());
		Assert.assertEquals("cadUser.alias translation failed", "sds", cadUser.getAlias());

		UnitHandle unit = model.getUnit();
		Assert.assertNotNull(unit);
		Assert.assertEquals("unit.key translation failed", "104", unit.getKey());
		Assert.assertEquals("unit.agencyAlias translation failed", "PPD", unit.getAgencyAlias());
		Assert.assertEquals("unit.callSign translation failed", "104", unit.getCallSign());
	}
}
