/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.translation.v2019_1;

import com.google.gson.JsonObject;
import com.motorola.models.representation.EmergencyIncident;
import com.motorola.models.representation.Person;
import com.motorola.models.representation.Subject;
import com.motorola.models.representation.UpdateEmergencyIncident;
import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
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
		Subject subject = emergencyIncident.getSubjects().get(0);
		Assert.assertEquals("Complainant", subject.getRole().get(0));
		Person person = subject.getPerson();
		Assert.assertNull(person);
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

}
