/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.translation.v2019_1_15_0;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.JsonObject;
import com.motorola.constants.InterfaceConstants;
import com.motorola.models.representation.Address;
import com.motorola.models.representation.DispatchableIncident;
import com.motorola.models.representation.Disposition;
import com.motorola.models.representation.EmergencyIncident;
import com.motorola.models.representation.IncidentComment;
import com.motorola.models.representation.InvolvedVehicle;
import com.motorola.models.representation.Jurisdiction;
import com.motorola.models.representation.Location;
import com.motorola.models.representation.Person;
import com.motorola.models.representation.ReportNumber;
import com.motorola.models.representation.Subject;
import com.motorola.models.representation.UnitHandle;
import com.motorola.models.representation.UpdateEmergencyIncident;
import com.motorola.models.representation.Vehicle;
import com.motorola.utils.OneRmsHashUtils;
import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Contains unit tests related to the EmergencyIncident translation.
 */
public class EmergencyIncidentTranslationTest extends TranslatorTest {

	private final DateTimeFormatter zonedDateTimeFormat = DateTimeFormatter.ofPattern(InterfaceConstants.GeneralProperties.ZONED_DATE_TIME_FORMAT);
	private final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(InterfaceConstants.GeneralProperties.DATE_FORMAT);

	@Test
	public void translateCreateIncident_validData_Test() throws ParseException, JsonProcessingException {
		String insertIncidentFileName = "insertIncident.json";
		JsonObject insertIncidentObject = initInputPayload(insertIncidentFileName);
		EmergencyIncident emergencyIncident = getTranslator().translateCreateIncident(insertIncidentObject);
		Assert.assertEquals("C6002", emergencyIncident.getAlias());
		Assert.assertEquals("C6002", emergencyIncident.getKey());
		Assert.assertEquals("2018-11-12T05:25:45-07:00", emergencyIncident.getWhenCreated());
		Subject subject = emergencyIncident.getSubjects().get(0);
		Assert.assertEquals("Complainant", subject.getRole().get(0));
		Person person = subject.getPerson();
		Assert.assertEquals("first_c", person.getFirstName());
		Assert.assertEquals(LocalDate.parse("1950-01-01", dateFormat), person.getDateOfBirth());
		Assert.assertEquals("last11", person.getLastName());
		Assert.assertEquals("mm", person.getMiddleName());
		Assert.assertEquals("ss", person.getSuffix());
		Assert.assertEquals(Long.valueOf(68), person.getAge());
		Assert.assertEquals(OneRmsHashUtils.convertCodeToOneRmsFormat("B"), person.getRaceKey());
		Assert.assertEquals(OneRmsHashUtils.convertCodeToOneRmsFormat("MAL"), person.getGenderKey());
		Assert.assertEquals(Long.valueOf(71), person.getHeight());
		Assert.assertEquals(Long.valueOf(200), person.getWeight());
		Assert.assertEquals(OneRmsHashUtils.convertCodeToOneRmsFormat("MCLR"), person.getBuildKey());
		Assert.assertEquals(OneRmsHashUtils.convertCodeToOneRmsFormat("TAN"), person.getEyeColorKey());
		Assert.assertEquals(OneRmsHashUtils.convertCodeToOneRmsFormat("BLN"), person.getHairColorKey());
		Assert.assertEquals("112qweqwe1", person.getDriverLicenseNumber());
		Assert.assertEquals("AD", person.getDriverLicenseState());
		Assert.assertEquals("customerId", emergencyIncident.getCustomerId());
		Assert.assertEquals("(234)555-4433", person.getPhone());
		//person's address
		List<Location> personLocations = person.getAddress();
		Assert.assertEquals(1, personLocations.size());
		Location personLocation = personLocations.get(0);
		Assert.assertEquals("3060-primary", personLocation.getKey());
		Assert.assertEquals("Home", personLocation.getType());
		Address personAddress = personLocation.getAddress();
		Assert.assertNotNull(personAddress);
		Assert.assertEquals("comp addr 11", personAddress.getFullText());
		Assert.assertEquals("Anderson", personAddress.getCity());
		Assert.assertEquals("AF", personAddress.getState());
		Assert.assertEquals("75610", personAddress.getZip());
		Assert.assertEquals("comp addr 11", personAddress.getDescription());
		Assert.assertFalse(personAddress.getIsVerified());
		Assert.assertEquals("0", personAddress.getGeoverificationLevel());
		//Involved vehicle
		List<InvolvedVehicle> involvedVehicleList = emergencyIncident.getVehicles();
		Assert.assertEquals(1, involvedVehicleList.size());
		InvolvedVehicle involvedVehicle = involvedVehicleList.get(0);
		Assert.assertNotNull(involvedVehicle);
		Assert.assertEquals("custom_relship-100", involvedVehicle.getKey());
		Assert.assertEquals("custom_relship", involvedVehicle.getRoleKeys().get(0));
		Vehicle vehicle = involvedVehicle.getVehicle();
		Assert.assertNotNull(vehicle);
		Assert.assertEquals("ABC999", vehicle.getLicensePlate());
		Assert.assertEquals("WV", vehicle.getLicenseState());
		Assert.assertEquals(OneRmsHashUtils.convertCodeToOneRmsFormat("PC"), vehicle.getLicenseTypeKey());
		Assert.assertEquals(LocalDate.parse("2018-11-30", dateFormat), vehicle.getLicenseExpirationDate());
		Assert.assertEquals("2000", String.valueOf(vehicle.getYear()));
		Assert.assertEquals(OneRmsHashUtils.convertCodeToOneRmsFormat("PONT"), vehicle.getMakeKey());
		Assert.assertEquals(OneRmsHashUtils.convertCodeToOneRmsFormat("6000"), vehicle.getModelKey());
		Assert.assertEquals(OneRmsHashUtils.convertCodeToOneRmsFormat("BLK"), vehicle.getPrimaryColorKey());
		Assert.assertEquals(OneRmsHashUtils.convertCodeToOneRmsFormat("BLU"), vehicle.getSecondaryColorKey());
		Assert.assertEquals("ABC111", vehicle.getVin());
		Assert.assertEquals("1", vehicle.getOwner());
		Assert.assertEquals("comment_data", vehicle.getComment());
		Assert.assertEquals(OneRmsHashUtils.convertCodeToOneRmsFormat("PCAR"), vehicle.getStyleKey());
		// location address alerts test
		List<DispatchableIncident> dispatches = emergencyIncident.getDispatches();
		DispatchableIncident dispatchableIncident = dispatches.get(0);
		Address address = dispatchableIncident.getLocation().getAddress();

		//it can be used in the future during the implementation of PremiseHazards endpoint
		/*Assert.assertEquals(2, address.getAlerts().size());
		// verification for alert fields
		Alert alert = address.getAlerts().get(0);
		Assert.assertEquals("Address", alert.getTypeKey());
		Assert.assertEquals("DRUG", alert.getCategoryKey());
		Assert.assertEquals("Possible Drugs on Premises", alert.getTitle());
		Assert.assertEquals("Possible Drugs on Premises", alert.getComment());
		Assert.assertEquals("0", alert.getPriority());
		Assert.assertEquals("2003-10-24", alert.getWhenExpired());
		Assert.assertEquals(LocalDate.parse("2001-10-24", dateFormat), alert.getWhenCreated());*/
	}

	@Test
	public void translateCreateIncident_invalidRole_Test() {
		String insertIncidentInvalidRoleFileName = "insertIncidentInvalidRole.json";
		JsonObject insertIncidentInvalidRoleObject = initInputPayload(insertIncidentInvalidRoleFileName);
		EmergencyIncident emergencyIncident = getTranslator().translateCreateIncident(insertIncidentInvalidRoleObject);
		Assert.assertEquals("C6002", emergencyIncident.getAlias());
		Assert.assertEquals("C6002", emergencyIncident.getKey());
		Assert.assertTrue(emergencyIncident.getSubjects().isEmpty());
	}

	@Test
	public void translateCreateIncident_emptyFields_Test() {
		String insertIncidentEmptyValuesFileName = "insertIncidentEmptyValues.json";
		JsonObject insertIncidentEmptyValuesObject = initInputPayload(insertIncidentEmptyValuesFileName);
		EmergencyIncident emergencyIncident = getTranslator().translateCreateIncident(insertIncidentEmptyValuesObject);
		Assert.assertNull(emergencyIncident.getAlias());
		Assert.assertNull(emergencyIncident.getKey());
		Assert.assertTrue(emergencyIncident.getSubjects().isEmpty());
	}

	@Test
	public void translateUpdateIncident_validData_Test() throws ParseException {
		String updateIncidentFileName = "updateIncident.json";
		JsonObject updateIncidentObject = initInputPayload(updateIncidentFileName);
		UpdateEmergencyIncident updateEmergencyIncident = getTranslator().translateUpdateIncident(updateIncidentObject);
		EmergencyIncident newIncident = updateEmergencyIncident.get__new();
		Assert.assertEquals("C6002", newIncident.getAlias());
		Assert.assertEquals("C6002", newIncident.getKey());
		Assert.assertEquals("customerId", newIncident.getCustomerId());
		Subject newSubject = newIncident.getSubjects().get(0);
		Assert.assertEquals("Complainant", newSubject.getRole().get(0));
		Person newPersonValue = newSubject.getPerson();
		Assert.assertEquals("first_new", newPersonValue.getFirstName());
		Assert.assertEquals(LocalDate.parse("1950-01-01", dateFormat), newPersonValue.getDateOfBirth());
		Assert.assertEquals("last11_new", newPersonValue.getLastName());
		Assert.assertEquals("mm_new", newPersonValue.getMiddleName());
		Assert.assertEquals("ss_new", newPersonValue.getSuffix());
		Assert.assertEquals(Long.valueOf(68), newPersonValue.getAge());
		Assert.assertEquals(OneRmsHashUtils.convertCodeToOneRmsFormat("B"), newPersonValue.getRaceKey());
		Assert.assertEquals(OneRmsHashUtils.convertCodeToOneRmsFormat("MAL"), newPersonValue.getGenderKey());
		Assert.assertEquals(Long.valueOf(71), newPersonValue.getHeight());
		Assert.assertEquals(Long.valueOf(200), newPersonValue.getWeight());
		Assert.assertEquals(OneRmsHashUtils.convertCodeToOneRmsFormat("MCLR"), newPersonValue.getBuildKey());
		Assert.assertEquals(OneRmsHashUtils.convertCodeToOneRmsFormat("TAN"), newPersonValue.getEyeColorKey());
		Assert.assertEquals(OneRmsHashUtils.convertCodeToOneRmsFormat("BLN"), newPersonValue.getHairColorKey());
		Assert.assertEquals("112qweqwe1_new", newPersonValue.getDriverLicenseNumber());
		Assert.assertEquals("AD", newPersonValue.getDriverLicenseState());
		Assert.assertEquals("(234)555-4444", newPersonValue.getPhone());
		//newPerson's address
		List<Location> newPersonLocations = newPersonValue.getAddress();
		Assert.assertEquals(1, newPersonLocations.size());
		Location newPersonLocation = newPersonLocations.get(0);
		Assert.assertEquals("3060-primary", newPersonLocation.getKey());
		Assert.assertEquals("Home", newPersonLocation.getType());
		Address newPersonAddress = newPersonLocation.getAddress();
		Assert.assertNotNull(newPersonAddress);
		Assert.assertEquals("comp addr 11_new", newPersonAddress.getFullText());
		Assert.assertEquals("Anderson", newPersonAddress.getCity());
		Assert.assertEquals("AF", newPersonAddress.getState());
		Assert.assertEquals("75610", newPersonAddress.getZip());
		Assert.assertEquals("comp addr 11_new", newPersonAddress.getDescription());
		Assert.assertFalse(newPersonAddress.getIsVerified());
		Assert.assertEquals("0", newPersonAddress.getGeoverificationLevel());

		EmergencyIncident oldIncident = updateEmergencyIncident.getOld();
		Assert.assertEquals("C6002", newIncident.getAlias());
		Assert.assertEquals("C6002", newIncident.getKey());
		Subject oldSubject = oldIncident.getSubjects().get(0);
		Assert.assertEquals("Complainant", oldSubject.getRole().get(0));
		Person oldPersonValue = oldSubject.getPerson();
		Assert.assertEquals("first_old", oldPersonValue.getFirstName());
		Assert.assertEquals(LocalDate.parse("1950-01-01"), oldPersonValue.getDateOfBirth());
		Assert.assertEquals("last11_old", oldPersonValue.getLastName());
		Assert.assertEquals("mm_old", oldPersonValue.getMiddleName());
		Assert.assertEquals("ss_old", oldPersonValue.getSuffix());
		Assert.assertEquals(Long.valueOf(68), oldPersonValue.getAge());
		Assert.assertEquals(OneRmsHashUtils.convertCodeToOneRmsFormat("B"), oldPersonValue.getRaceKey());
		Assert.assertEquals(OneRmsHashUtils.convertCodeToOneRmsFormat("MAL"), oldPersonValue.getGenderKey());
		Assert.assertEquals(Long.valueOf(71), oldPersonValue.getHeight());
		Assert.assertEquals(Long.valueOf(200), oldPersonValue.getWeight());
		Assert.assertEquals(OneRmsHashUtils.convertCodeToOneRmsFormat("MCLR"), oldPersonValue.getBuildKey());
		Assert.assertEquals(OneRmsHashUtils.convertCodeToOneRmsFormat("TAN"), oldPersonValue.getEyeColorKey());
		Assert.assertEquals(OneRmsHashUtils.convertCodeToOneRmsFormat("BLN"), oldPersonValue.getHairColorKey());
		Assert.assertEquals("112qweqwe1_old", oldPersonValue.getDriverLicenseNumber());
		Assert.assertEquals("AD", oldPersonValue.getDriverLicenseState());
		Assert.assertEquals("(234)555-4433", oldPersonValue.getPhone());
		//oldPerson's address
		List<Location> oldPersonLocations = oldPersonValue.getAddress();
		Assert.assertEquals(1, oldPersonLocations.size());
		Location oldPersonLocation = oldPersonLocations.get(0);
		Assert.assertEquals("3060-primary", oldPersonLocation.getKey());
		Assert.assertEquals("Home", oldPersonLocation.getType());
		Address oldPersonAddress = oldPersonLocation.getAddress();
		Assert.assertNotNull(oldPersonAddress);
		Assert.assertEquals("comp addr 11_old", oldPersonAddress.getFullText());
		Assert.assertEquals("Anderson", oldPersonAddress.getCity());
		Assert.assertEquals("AF", oldPersonAddress.getState());
		Assert.assertEquals("75610", oldPersonAddress.getZip());
		Assert.assertEquals("comp addr 11_old", oldPersonAddress.getDescription());
		Assert.assertFalse(oldPersonAddress.getIsVerified());
		Assert.assertEquals("0", oldPersonAddress.getGeoverificationLevel());
	}

	@Test
	public void testDispatchIncidentDataTransformation() throws ParseException {
		String insertIncidentFileName = "dispatch_incident_test.json";
		JsonObject insertIncidentObject = initInputPayload(insertIncidentFileName);
		EmergencyIncident emergencyIncident = getTranslator().translateCreateIncident(insertIncidentObject);
		Assert.assertNotNull(emergencyIncident);
		DispatchableIncident dispatchIncident = emergencyIncident.getDispatches().get(0);
		Assert.assertNotNull(dispatchIncident);
		Assert.assertEquals("24e", dispatchIncident.getAlias());
		// TODO:Key
		Assert.assertEquals(OneRmsHashUtils.convertCodeToOneRmsFormat("e"), dispatchIncident.getDisciplineKey());
		Assert.assertEquals(OneRmsHashUtils.convertCodeToOneRmsFormat("Abdominal"), dispatchIncident.getNature().getNatureKey());
		Assert.assertEquals("1", dispatchIncident.getPriority());
		Assert.assertEquals(OneRmsHashUtils.convertCodeToOneRmsFormat("SPD"), dispatchIncident.getAgencyKey());
		Assert.assertEquals(OneRmsHashUtils.convertCodeToOneRmsFormat("RCVD"), dispatchIncident.getStatusKey());
		Assert.assertEquals("2018-11-21T05:57:41-07:00", dispatchIncident.getWhenStatusDeclared());
		Assert.assertEquals(OneRmsHashUtils.convertCodeToOneRmsFormat("01A01"), dispatchIncident.getProqaDeterminantKey());
		//Disposition
		Assert.assertNotNull(dispatchIncident.getDispositions());
		Assert.assertFalse(dispatchIncident.getDispositions().isEmpty());
		Assert.assertEquals(1, dispatchIncident.getDispositions().size());
		Disposition disposition = dispatchIncident.getDispositions().get(0);
		Assert.assertNotNull(disposition.getCadDispositionKey());
		Assert.assertEquals(OneRmsHashUtils.convertCodeToOneRmsFormat("ACT"), disposition.getCadDispositionKey());
		Assert.assertNotNull(disposition.getReportDispositionKey());
		Assert.assertEquals(OneRmsHashUtils.convertCodeToOneRmsFormat("NA"), disposition.getReportDispositionKey());
		Assert.assertNotNull(disposition.getObservedOffenseKey());
		Assert.assertEquals(OneRmsHashUtils.convertCodeToOneRmsFormat("11"), disposition.getObservedOffenseKey());
		Assert.assertEquals(OneRmsHashUtils.convertCodeToOneRmsFormat("T"), dispatchIncident.getIncidentSourceKey());
		Assert.assertEquals(true, dispatchIncident.getIsScheduled());
		// TODO: check in json with key "responsibleUnitId"
		Assert.assertEquals("007", dispatchIncident.getPrimaryUnit().getKey());
		//Dispatch - Comments
		Assert.assertNotNull(dispatchIncident.getComments());
		Assert.assertFalse(dispatchIncident.getComments().isEmpty());
		Assert.assertEquals(1, dispatchIncident.getComments().size());
		IncidentComment comment = dispatchIncident.getComments().get(0);
		Assert.assertEquals("2dc19f42-590a-4d04-8481-573a21f4bdf7", comment.getKey());
		Assert.assertEquals("comment1", comment.getComments());
		Assert.assertEquals(ZonedDateTime.parse("2018-03-12T08:00:51-06:00", zonedDateTimeFormat), comment.getWhenEntered());
		Assert.assertNotNull(comment.getSourceKey());
		Assert.assertEquals("User", comment.getSourceKey());
		Assert.assertEquals("All", comment.getAudience());
		Assert.assertNotNull(comment.getOnBehalfOfUnit());
		Assert.assertEquals("101", comment.getOnBehalfOfUnit().getKey());
		Assert.assertNotNull(comment.getOnBehalfOfUser());
		Assert.assertEquals("XML User", comment.getOnBehalfOfUser().getAlias());
		Assert.assertNotNull(comment.getEnteredBy());
		Assert.assertEquals("Spillman", comment.getEnteredBy().getAlias());
		Assert.assertNull(comment.getDevice());
		Assert.assertTrue(comment.getIsPriority());
		// Dispatch - ReportNumbers
		ReportNumber reportNumber = dispatchIncident.getReportNumbers().get(0);
		Assert.assertNotNull(reportNumber);
		Assert.assertEquals("1109-0013-SPDF", reportNumber.getKey());
		Assert.assertEquals("1109-0013", reportNumber.getAlias());
		Assert.assertEquals("SPDF", reportNumber.getAgencyAlias());
		Assert.assertNull(reportNumber.getUnitHandle());
		// Dispatch - Location
		Location location = dispatchIncident.getLocation();
		Assert.assertNotNull(location);
		Assert.assertEquals("1", location.getKey());

		Address address = location.getAddress();
		Assert.assertNotNull(address);
		Assert.assertEquals("100 S MAIN ST", address.getFullText());
		Assert.assertEquals("Intersection of: S MAIN ST & S MAN ST & HUNTSVILLE RD", address.getCrossStreet());
		Assert.assertEquals("SFD", address.getCity());
		Assert.assertEquals("ND", address.getState());
		Assert.assertEquals("35630", address.getZip());
		Assert.assertEquals("34.8109", address.getLatitude());
		Assert.assertEquals("-87.6465", address.getLongitude());
		Assert.assertEquals("100 S MAIN ST", address.getDescription());
		Assert.assertEquals("100", address.getGeoverificationLevel());
		Assert.assertTrue(address.getIsVerified());

		Jurisdiction jurisdiction = address.getJurisdiction();
		Assert.assertNotNull(jurisdiction);
		Assert.assertEquals(OneRmsHashUtils.convertCodeToOneRmsFormat("LSW"), jurisdiction.getAreaKey());
		// Assigned Unit
		List<UnitHandle> assignedUnits = dispatchIncident.getAssignedUnits( );
		Assert.assertNotNull(assignedUnits);
		Assert.assertEquals(2, assignedUnits.size( ));

		UnitHandle assignedUnit1 = assignedUnits.get(0);
		Assert.assertNotNull(assignedUnit1);
		Assert.assertEquals("F14", assignedUnit1.getKey( ));
		Assert.assertNull(assignedUnit1.getAgencyAlias());
		Assert.assertNull(assignedUnit1.getCallSign( ));
		Assert.assertNull(assignedUnit1.getShiftId( ));

		UnitHandle assignedUnit2 = assignedUnits.get(1);
		Assert.assertNotNull(assignedUnit2);
		Assert.assertEquals("F16", assignedUnit2.getKey( ));
		Assert.assertNull(assignedUnit2.getAgencyAlias());
		Assert.assertNull(assignedUnit2.getCallSign( ));
		Assert.assertNull(assignedUnit2.getShiftId( ));



	}

}
