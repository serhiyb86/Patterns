/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.translation.v2019_1;

import com.google.gson.JsonObject;
import com.motorola.models.representation.DispatchableIncident;
import com.motorola.models.representation.Disposition;
import com.motorola.models.representation.EmergencyIncident;
import com.motorola.models.representation.IncidentComment;
import com.motorola.models.representation.InvolvedVehicle;
import com.motorola.models.representation.Person;
import com.motorola.models.representation.Subject;
import com.motorola.models.representation.UpdateEmergencyIncident;
import com.motorola.models.representation.Vehicle;
import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.util.List;

/**
 * Contains unit tests related to the EmergencyIncident translation.
 */
public class EmergencyIncidentTranslationTest extends TranslatorTest {

	@Test
	public void translateCreateIncident_validData_Test() throws ParseException {
		String insertIncidentFileName = "insertIncident.json";
		JsonObject insertIncidentObject = initInputPayload(insertIncidentFileName);
		EmergencyIncident emergencyIncident = getTranslator().translateCreateIncident(insertIncidentObject);
		Assert.assertEquals("C6002", emergencyIncident.getId());
		Assert.assertEquals("C6002", emergencyIncident.getKey());
		Subject subject = emergencyIncident.getSubjects().get(0);
		Assert.assertEquals("Complainant", subject.getRole().get(0));
		Person person = subject.getPerson();
		Assert.assertEquals("first_c", person.getFirstName());
		Assert.assertEquals(getDateFormatter().parse("1950-01-01"), person.getDateOfBirth());
		Assert.assertEquals("last11", person.getLastName());
		Assert.assertEquals("mm", person.getMiddleName());
		Assert.assertEquals("ss", person.getSuffix());
		Assert.assertEquals(Long.valueOf(68), person.getAge());
		Assert.assertEquals("B", person.getRace().getUid());
		Assert.assertEquals("MAL", person.getGender().getUid());
		Assert.assertEquals("71", person.getHeight());
		Assert.assertEquals(Long.valueOf(200), person.getWeight());
		Assert.assertEquals("MCLR", person.getBuild().getUid());
		Assert.assertEquals("TAN", person.getEyeColor().getUid());
		Assert.assertEquals("BLN", person.getHairColor().getUid());
		Assert.assertEquals("112qweqwe1", person.getDriverLicenseNumber());
		Assert.assertEquals("AD", person.getDriverLicenseState());
		//Involved vehicle
		List<InvolvedVehicle> involvedVehicleList = emergencyIncident.getVehicles();
		Assert.assertEquals(1, involvedVehicleList.size());
		InvolvedVehicle involvedVehicle = involvedVehicleList.get(0);
		Assert.assertNotNull(involvedVehicle);
		Assert.assertEquals("custom_relship-100", involvedVehicle.getKey());
		Assert.assertEquals("custom_relship", involvedVehicle.getRole().get(0));
		Vehicle vehicle = involvedVehicle.getVehicle();
		Assert.assertNotNull(vehicle);
		Assert.assertEquals("ABC999", vehicle.getLicensePlate());
		Assert.assertEquals("WV", vehicle.getLicenseState());
		Assert.assertEquals("PC", vehicle.getLicenseType().getUid());
		Assert.assertEquals("2018-11-30T00:00:00.000Z", vehicle.getLicenseExpirationDate());
		Assert.assertEquals("2000", String.valueOf(vehicle.getYear()));
		Assert.assertEquals("PONT", vehicle.getMake().getUid());
		Assert.assertEquals("6000", vehicle.getModel().getUid());
		Assert.assertEquals("BLK", vehicle.getPrimaryColor().getUid());
		Assert.assertEquals("BLU", vehicle.getSecondaryColor().getUid());
		Assert.assertEquals("ABC111", vehicle.getVin());
		Assert.assertEquals("1", vehicle.getOwner());
		Assert.assertEquals("comment_data", vehicle.getComment());
	}

	@Test
	public void translateCreateIncident_invalidRole_Test() {
		String insertIncidentInvalidRoleFileName = "insertIncidentInvalidRole.json";
		JsonObject insertIncidentInvalidRoleObject = initInputPayload(insertIncidentInvalidRoleFileName);
		EmergencyIncident emergencyIncident = getTranslator().translateCreateIncident(insertIncidentInvalidRoleObject);
		Assert.assertEquals("C6002", emergencyIncident.getId());
		Assert.assertEquals("C6002", emergencyIncident.getKey());
		Assert.assertTrue(emergencyIncident.getSubjects().isEmpty());
	}

	@Test
	public void translateCreateIncident_emptyFields_Test() {
		String insertIncidentEmptyValuesFileName = "insertIncidentEmptyValues.json";
		JsonObject insertIncidentEmptyValuesObject = initInputPayload(insertIncidentEmptyValuesFileName);
		EmergencyIncident emergencyIncident = getTranslator().translateCreateIncident(insertIncidentEmptyValuesObject);
		Assert.assertNull(emergencyIncident.getId());
		Assert.assertNull(emergencyIncident.getKey());
		Assert.assertTrue(emergencyIncident.getSubjects().isEmpty());
	}

	@Test
	public void translateUpdateIncident_validData_Test() throws ParseException {
		String updateIncidentFileName = "updateIncident.json";
		JsonObject updateIncidentObject = initInputPayload(updateIncidentFileName);
		UpdateEmergencyIncident updateEmergencyIncident = getTranslator().translateUpdateIncident(updateIncidentObject);
		EmergencyIncident newIncident = updateEmergencyIncident.get__new();
		Assert.assertEquals("C6002", newIncident.getId());
		Assert.assertEquals("C6002", newIncident.getKey());
		Subject newSubject = newIncident.getSubjects().get(0);
		Assert.assertEquals("Complainant", newSubject.getRole().get(0));
		Person newPersonValue = newSubject.getPerson();
		Assert.assertEquals("first_new", newPersonValue.getFirstName());
		Assert.assertEquals(getDateFormatter().parse("1950-01-01"), newPersonValue.getDateOfBirth());
		Assert.assertEquals("last11_new", newPersonValue.getLastName());
		Assert.assertEquals("mm_new", newPersonValue.getMiddleName());
		Assert.assertEquals("ss_new", newPersonValue.getSuffix());
		Assert.assertEquals(Long.valueOf(68), newPersonValue.getAge());
		Assert.assertEquals("B", newPersonValue.getRace().getUid());
		Assert.assertEquals("MAL", newPersonValue.getGender().getUid());
		Assert.assertEquals("71", newPersonValue.getHeight());
		Assert.assertEquals(Long.valueOf(200), newPersonValue.getWeight());
		Assert.assertEquals("MCLR", newPersonValue.getBuild().getUid());
		Assert.assertEquals("TAN", newPersonValue.getEyeColor().getUid());
		Assert.assertEquals("BLN", newPersonValue.getHairColor().getUid());
		Assert.assertEquals("112qweqwe1_new", newPersonValue.getDriverLicenseNumber());
		Assert.assertEquals("AD", newPersonValue.getDriverLicenseState());

		EmergencyIncident oldIncident = updateEmergencyIncident.getOld();
		Assert.assertEquals("C6002", newIncident.getId());
		Assert.assertEquals("C6002", newIncident.getKey());
		Subject oldSubject = oldIncident.getSubjects().get(0);
		Assert.assertEquals("Complainant", oldSubject.getRole().get(0));
		Person oldPersonValue = oldSubject.getPerson();
		Assert.assertEquals("first_old", oldPersonValue.getFirstName());
		Assert.assertEquals(getDateFormatter().parse("1950-01-01"), oldPersonValue.getDateOfBirth());
		Assert.assertEquals("last11_old", oldPersonValue.getLastName());
		Assert.assertEquals("mm_old", oldPersonValue.getMiddleName());
		Assert.assertEquals("ss_old", oldPersonValue.getSuffix());
		Assert.assertEquals(Long.valueOf(68), oldPersonValue.getAge());
		Assert.assertEquals("B", oldPersonValue.getRace().getUid());
		Assert.assertEquals("MAL", oldPersonValue.getGender().getUid());
		Assert.assertEquals("71", oldPersonValue.getHeight());
		Assert.assertEquals(Long.valueOf(200), oldPersonValue.getWeight());
		Assert.assertEquals("MCLR", oldPersonValue.getBuild().getUid());
		Assert.assertEquals("TAN", oldPersonValue.getEyeColor().getUid());
		Assert.assertEquals("BLN", oldPersonValue.getHairColor().getUid());
		Assert.assertEquals("112qweqwe1_old", oldPersonValue.getDriverLicenseNumber());
		Assert.assertEquals("AD", oldPersonValue.getDriverLicenseState());
	}

	@Test
	public void testDispatchIncidentDataTransformation() {
		String insertIncidentFileName = "dispatch_incident_test.json";
		JsonObject insertIncidentObject = initInputPayload(insertIncidentFileName);
		EmergencyIncident emergencyIncident = getTranslator().translateCreateIncident(insertIncidentObject);
		DispatchableIncident dispatchIncident = emergencyIncident.getDispatches().get(0);

		Assert.assertEquals("24e", dispatchIncident.getAlias());
		// TODO:Key
		Assert.assertEquals("e", dispatchIncident.getDiscipline().getUid());
		Assert.assertEquals("Abdominal", dispatchIncident.getNature().getNature().getUid());
		Assert.assertEquals("1", dispatchIncident.getPriority());
		Assert.assertEquals("SPD", dispatchIncident.getAgency().getUid());
		Assert.assertEquals("RCVD", dispatchIncident.getStatus().getUid());
		Assert.assertEquals("2018-11-21T05:57:41-07:00", dispatchIncident.getWhenStatusDeclared());
		Assert.assertEquals("01A01", dispatchIncident.getProqaDeterminant().getUid());
		//Disposition
		Assert.assertNotNull(dispatchIncident.getDispositions());
		Assert.assertFalse(dispatchIncident.getDispositions().isEmpty());
		Assert.assertEquals(1, dispatchIncident.getDispositions().size());
		Disposition disposition = dispatchIncident.getDispositions().get(0);
		Assert.assertNotNull(disposition.getCadDisposition());
		Assert.assertEquals("NA", disposition.getCadDisposition().getUid());
		Assert.assertNotNull(disposition.getReportDisposition());
		Assert.assertEquals("ACT", disposition.getReportDisposition().getUid());
		Assert.assertNotNull(disposition.getObservedOffense());
		Assert.assertEquals("11", disposition.getObservedOffense().getUid());
		// TODO: isSchedule, type
		// TODO: check in json with key "responsibleUnitId"
		Assert.assertEquals("007", dispatchIncident.getPrimaryUnit().getKey());
		//Dispatch - Comments
		Assert.assertNotNull(dispatchIncident.getComments());
		Assert.assertFalse(dispatchIncident.getComments().isEmpty());
		Assert.assertEquals(1, dispatchIncident.getComments().size());
		IncidentComment comment = dispatchIncident.getComments().get(0);
		Assert.assertEquals("2dc19f42-590a-4d04-8481-573a21f4bdf7", comment.getKey());
		Assert.assertEquals("comment1", comment.getComments());
		Assert.assertEquals("2018-03-12T08:00:51-06:00", comment.getWhenEntered());
		Assert.assertNotNull(comment.getSource());
		Assert.assertEquals("User", comment.getSource().getUid());
		Assert.assertEquals("All", comment.getAudience());
		Assert.assertNotNull(comment.getOnBehalfOfUnit());
		Assert.assertEquals("101", comment.getOnBehalfOfUnit().getKey());
		Assert.assertNotNull(comment.getOnBehalfOfUser());
		Assert.assertEquals("XML User", comment.getOnBehalfOfUser().getNameCode());
		Assert.assertNotNull(comment.getEnteredBy());
		Assert.assertEquals("Spillman", comment.getEnteredBy().getNameCode());
		Assert.assertNull(comment.getDevice());
		Assert.assertEquals("urgent", comment.getIsPriority());
	}

}
