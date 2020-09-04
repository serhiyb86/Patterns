package com.motorola.translation.v2019_1_15_0.mapper;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.motorola.models.representation.Address;
import com.motorola.translation.v2019_1_15_0.mappers.incident.AddressMapper;
import org.junit.Assert;
import org.junit.Test;

public class AddressMapperTest {

	public static final String GEO_VERIFIED_DATA = "{\n"
		+ "\t\"id\":\"240\",\n"
		+ "\t\"recordTitle\":\"100 S MAIN ST, SFD, ND 35630\",\n"
		+ "\t\"streetFullText\":\"100 S MAIN ST\",\n"
		+ "\t\"houseNumber\":\"100\",\n"
		+ "\t\"street\":\"S MAIN ST\",\n"
		+ "\t\"city\":\"SFD\",\n"
		+ "\t\"state\":\"ND\",\n"
		+ "\t\"zip\":\"35630\",\n"
		+ "\t\"crossStreets\":\"Intersection of: S MAIN ST & S MAN ST & HUNTSVILLE RD\",\n"
		+ "\t\"latitude\":34.810899,\n"
		+ "\t\"longitude\":-87.6465,\n"
		+ "\t\"intersection\":\"Intersection of: S MAIN ST & S MAN ST & HUNTSVILLE RD\"\n"
		+ "}";

	public static final String NO_GEO_VERIFIED_DATA_WITHOUT_BUILDING_NUMBER = "{\n"
		+ "\t\"id\":\"NOGEO\",\n"
		+ "\t\"recordTitle\":\"100 S MAIN ST, SFD, ND 35630\",\n"
		+ "\t\"streetFullText\":\"100 S MAIN ST\",\n"
		+ "\t\"city\":\"SFD\",\n"
		+ "\t\"crossStreets\":\"Intersection of: S MAIN ST & S MAN ST & HUNTSVILLE RD\"\n"
		+ "}";

	public static final String NO_GEO_VERIFIED_DATA_WITH_BUILDING_NUMBER = "{\n"
		+ "\t\"id\":\"NOGEO\",\n"
		+ "\t\"recordTitle\":\"100 S MAIN ST, SFD, ND 35630 bldg#100\",\n"
		+ "\t\"streetFullText\":\"100 S MAIN ST bldg#100\",\n"
		+ "\t\"city\":\"SFD\",\n"
		+ "\t\"crossStreets\":\"Intersection of: S MAIN ST & S MAN ST & HUNTSVILLE RD\"\n"
		+ "}";

	public static final String NO_GEO_VERIFIED_DATA_EMPTY_BUILDING_NUMBER = "{\n"
		+ "\t\"id\":\"NOGEO\",\n"
		+ "\t\"recordTitle\":\"100 S MAIN ST, SFD, ND 35630 bldg#100\",\n"
		+ "\t\"streetFullText\":\"100 S MAIN ST bldg#\",\n"
		+ "\t\"city\":\"SFD\",\n"
		+ "\t\"crossStreets\":\"Intersection of: S MAIN ST & S MAN ST & HUNTSVILLE RD\"\n"
		+ "}";

	private AddressMapper addressMapper = new AddressMapper();

	private static final Gson GSON = new Gson();

	@Test
	public void createAndMapToAddress_GeoVerifiedData_Test() {
		JsonObject testData = GSON.fromJson(GEO_VERIFIED_DATA, JsonObject.class);
		Address address = addressMapper.createAndMapToAddress(testData);
		Assert.assertEquals("240", address.getKey());
		Assert.assertEquals("100 S MAIN ST", address.getFullText());
		Assert.assertEquals("100 S MAIN ST", address.getDescription());
		Assert.assertEquals("100", address.getBuilding());
		Assert.assertEquals("Intersection of: S MAIN ST & S MAN ST & HUNTSVILLE RD", address.getCrossStreet());
		Assert.assertEquals("SFD", address.getCity());
		Assert.assertEquals("ND", address.getState());
		Assert.assertEquals("35630", address.getZip());
		Assert.assertEquals("34.810899", address.getLatitude());
		Assert.assertEquals("-87.6465", address.getLongitude());
		Assert.assertEquals(true, address.getIsVerified());
		Assert.assertEquals("100", address.getGeoverificationLevel());
	}

	@Test
	public void createAndMapToAddress_NoGeoVerifiedData_WithoutBuildingNumber_Test() {
		JsonObject testData = GSON.fromJson(NO_GEO_VERIFIED_DATA_WITHOUT_BUILDING_NUMBER, JsonObject.class);
		Address address = addressMapper.createAndMapToAddress(testData);
		Assert.assertEquals("NOGEO", address.getKey());
		Assert.assertEquals("100 S MAIN ST", address.getFullText());
		Assert.assertEquals("100 S MAIN ST", address.getDescription());
		Assert.assertNull(address.getBuilding());
		Assert.assertEquals("Intersection of: S MAIN ST & S MAN ST & HUNTSVILLE RD", address.getCrossStreet());
		Assert.assertEquals("SFD", address.getCity());
		Assert.assertNull(address.getState());
		Assert.assertNull(address.getZip());
		Assert.assertNull(address.getLatitude());
		Assert.assertNull(address.getLongitude());
		Assert.assertEquals(false, address.getIsVerified());
		Assert.assertEquals("0", address.getGeoverificationLevel());
	}

	@Test
	public void createAndMapToAddress_NoGeoVerifiedData_WithBuildingNumber_Test() {
		JsonObject testData = GSON.fromJson(NO_GEO_VERIFIED_DATA_WITH_BUILDING_NUMBER, JsonObject.class);
		Address address = addressMapper.createAndMapToAddress(testData);
		Assert.assertEquals("NOGEO", address.getKey());
		Assert.assertEquals("100 S MAIN ST", address.getFullText());
		Assert.assertEquals("100 S MAIN ST", address.getDescription());
		Assert.assertEquals("100", address.getBuilding());
		Assert.assertEquals("Intersection of: S MAIN ST & S MAN ST & HUNTSVILLE RD", address.getCrossStreet());
		Assert.assertEquals("SFD", address.getCity());
		Assert.assertNull(address.getState());
		Assert.assertNull(address.getZip());
		Assert.assertNull(address.getLatitude());
		Assert.assertNull(address.getLongitude());
		Assert.assertEquals(false, address.getIsVerified());
		Assert.assertEquals("0", address.getGeoverificationLevel());
	}

	@Test
	public void createAndMapToAddress_NoGeoVerifiedData_EmptyBuildingNumber_Test() {
		JsonObject testData = GSON.fromJson(NO_GEO_VERIFIED_DATA_EMPTY_BUILDING_NUMBER, JsonObject.class);
		Address address = addressMapper.createAndMapToAddress(testData);
		Assert.assertEquals("NOGEO", address.getKey());
		Assert.assertEquals("100 S MAIN ST", address.getFullText());
		Assert.assertEquals("100 S MAIN ST", address.getDescription());
		Assert.assertNull("", address.getBuilding());
		Assert.assertEquals("Intersection of: S MAIN ST & S MAN ST & HUNTSVILLE RD", address.getCrossStreet());
		Assert.assertEquals("SFD", address.getCity());
		Assert.assertNull(address.getState());
		Assert.assertNull(address.getZip());
		Assert.assertNull(address.getLatitude());
		Assert.assertNull(address.getLongitude());
		Assert.assertEquals(false, address.getIsVerified());
		Assert.assertEquals("0", address.getGeoverificationLevel());
	}

}
