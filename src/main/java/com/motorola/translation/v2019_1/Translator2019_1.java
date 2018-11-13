/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.translation.v2019_1;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.motorola.constants.InterfaceConstants;
import com.motorola.models.representation.AdditionalInfo;
import com.motorola.models.representation.ApiError;
import com.motorola.models.representation.EmergencyIncident;
import com.motorola.models.representation.Lookup;
import com.motorola.models.representation.MonitorAreas;
import com.motorola.models.representation.Person;
import com.motorola.models.representation.ResponseNotification;
import com.motorola.models.representation.Subject;
import com.motorola.models.representation.UnitHandle;
import com.motorola.models.representation.UpdateEmergencyIncident;
import com.motorola.models.representation.UserSession;
import com.motorola.models.representation.UserSessionWrapper;
import com.motorola.translation.BaseTranslator;
import com.motorola.utils.CadCloudUtils;
import com.motorola.validation.ValidationResult;
import com.motorola.validation.ValidationErrorType;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Translator class for Spillman version 2019.1
 */
public class Translator2019_1 implements BaseTranslator {

	private static final Logger LOGGER = LoggerFactory.getLogger(Translator2019_1.class);
	private List<ValidationResult> validationResults = new ArrayList<>();
	private final CadCloudUtils utils = new CadCloudUtils();
	private final SimpleDateFormat zonedDateTimeFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
	private final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	public UserSessionWrapper translateBookOn(JsonObject payload) {
		clearValidationResults();
		UserSessionWrapper result = new UserSessionWrapper();
		UserSession userSession = new UserSession();
		String correlationId = utils.getStringByKey(payload, InterfaceConstants.BookOnProperties.CORRELATION_ID);
		validateRequiredStringField(correlationId, InterfaceConstants.BookOnProperties.CORRELATION_ID);
		result.setCorrelationId(correlationId);
		userSession.setCustomerId(utils.getStringByKey(payload, InterfaceConstants.BookOnProperties.CUSTOMER_ID));
		userSession.setSessionId(utils.getStringByKey(payload, InterfaceConstants.BookOnProperties.SESSION_ID));
		userSession.setDeviceId(utils.getStringByKey(payload, InterfaceConstants.BookOnProperties.DEVICE_ID));
		userSession.setUserId(utils.getStringByKey(payload, InterfaceConstants.BookOnProperties.USER_ID));
		userSession.setWhenSessionCreated(getDateByKey(payload, InterfaceConstants.BookOnProperties.WHEN_SESSION_CREATED, zonedDateTimeFormatter));
		userSession.setWhenSessionUpdated(getDateByKey(payload, InterfaceConstants.BookOnProperties.WHEN_SESSION_UPDATED, zonedDateTimeFormatter));
		userSession.setRoleKey(InterfaceConstants.BookOnProperties.ROLE_KEY_VAL);
		if (validateRequiredObjectField(payload, InterfaceConstants.BookOnProperties.API_ACCESS_LIST)) {
			JsonArray apiAccessList = utils.getJsonArrayByKey(payload, InterfaceConstants.BookOnProperties.API_ACCESS_LIST);
			userSession.setApiAccessList(getAccessList(apiAccessList));
		}
		if (validateRequiredObjectField(payload, InterfaceConstants.BookOnProperties.MONITOR_AREAS)) {
			JsonObject monitorArea = utils.getJsonByKey(payload, InterfaceConstants.BookOnProperties.MONITOR_AREAS);
			userSession.setMonitorAreas(getMonitorArea(monitorArea));
		}

		if (validateRequiredObjectField(payload, InterfaceConstants.BookOnProperties.ADDITIONAL_INFO_JSON_KEY)) {
			JsonObject additionInfoJSON = utils.getJsonByKey(payload, InterfaceConstants.BookOnProperties.ADDITIONAL_INFO_JSON_KEY);
			userSession.setAdditionalInfo(getAdditionInfo(additionInfoJSON));
		}

		result.setModel(userSession);
		return result;
	}

	/**
	 * Gets the additional information from incoming json
	 * @param additionInfoJSON - incoming json payload
	 * @return - addition information object
	 */
	private AdditionalInfo getAdditionInfo(JsonObject additionInfoJSON) {
		AdditionalInfo result = new AdditionalInfo();
		JsonObject unitJSONObject = utils.getJsonByKey(additionInfoJSON, InterfaceConstants.BookOnProperties.UNIT_JSON_KEY);
		UnitHandle unitHandler = new UnitHandle();
		unitHandler.setKey(utils.getStringByKey(unitJSONObject, InterfaceConstants.BookOnProperties.KEY_JSON_KEY));
		unitHandler.setAgency(utils.getStringByKey(unitJSONObject, InterfaceConstants.BookOnProperties.AGENCY_JSON_KEY));
		result.setUnit(unitHandler);
		return result;
	}

	/**
	 * Gets the monitor areas information
	 * @param monitorArea - json payload with monitor area information
	 * @return
	 */
	private MonitorAreas getMonitorArea(JsonObject monitorArea) {
		MonitorAreas result = new MonitorAreas();
		result.setAreaKeys(createLookupList(utils.getJsonArrayByKey(monitorArea, InterfaceConstants.BookOnProperties.AREAS),
			InterfaceConstants.GeneralProperties.UID_JSON_KEY));
		return result;
	}

	private List<String> getAccessList(JsonArray apiAccessList) {
		List<String> result = new ArrayList<>();
		for (JsonElement element : apiAccessList) {
			JsonObject permissionJSON = element.getAsJsonObject();
			result.add(utils.getStringByKey(permissionJSON, InterfaceConstants.BookOnProperties.PERMISSION_ID));
		}

		return result;
	}

	/**
	 * Appends errors part to the response notification object
	 * @param notification response notification object
	 * @param payload json payload
	 * @return response notification object with error information
	 */
	private ResponseNotification addResponseErrorPart(ResponseNotification notification, JsonObject payload) {
		ApiError error = new ApiError();
		JsonObject errorJson = utils.getJsonByKey(payload, InterfaceConstants.NotificationProperties.ERROR);
		error.setErrorCode(utils.getStringByKey(errorJson, InterfaceConstants.NotificationProperties.ERROR_CODE));
		error.setMessage(utils.getStringByKey(errorJson, InterfaceConstants.NotificationProperties.MESSAGE));
		notification.setError(error);
		return notification;
	}

	/**
	 * Creates response notification object without error part.
	 *
	 * @param payload {@link JsonObject} object represents {@link ResponseNotification} data.
	 * @return response notification object.
	 */
	private ResponseNotification translateResponseSuccessPart(JsonObject payload) {
		clearValidationResults();
		ResponseNotification result = new ResponseNotification();
		String correlationId = utils.getStringByKey(payload, InterfaceConstants.NotificationProperties.CORRELATION_ID);
		validateRequiredStringField(correlationId, InterfaceConstants.NotificationProperties.CORRELATION_ID);
		result.setCorrelationId(correlationId);
		result.setServiceId(utils.getStringByKey(payload, InterfaceConstants.NotificationProperties.SERVICE_ID));
		result.setCustomerId(utils.getStringByKey(payload, InterfaceConstants.NotificationProperties.CUSTOMER_ID));
		result.setNotificationType(utils.getStringByKey(payload, InterfaceConstants.NotificationProperties.NOTIFICATION_TYPE));
		result.setWhenSubmitted(utils.getStringByKey(payload, InterfaceConstants.NotificationProperties.WHEN_SUBMITTED));
		result.setSessionId(utils.getStringByKey(payload, InterfaceConstants.NotificationProperties.SESSION_ID));
		result.setResultNature(utils.getStringByKey(payload, InterfaceConstants.NotificationProperties.RESULT_NATURE));

		return result;
	}

	@Override
	public ResponseNotification translateBookOff(JsonObject payload) {
		return translateResponseSuccessPart(payload);
	}

	@Override
	public ResponseNotification translateErrorNotification(JsonObject payload) {
		ResponseNotification result = translateResponseSuccessPart(payload);
		return addResponseErrorPart(result, payload);
	}

	@Override
	public EmergencyIncident translateCreateIncident(JsonObject payload) {
		clearValidationResults();
		EmergencyIncident emergencyIncident = null;
		JsonArray data = utils.getJsonArrayByKey(payload, InterfaceConstants.GeneralProperties.DATA_JSON_KEY);
		if (validateRequiredObjectField(data, InterfaceConstants.GeneralProperties.DATA_JSON_KEY) && validateRequiredObjectField(data.get(0), InterfaceConstants.GeneralProperties.DATA_JSON_KEY)) {
			JsonObject incident = data.get(0).getAsJsonObject();
			emergencyIncident = createEmergencyIncident(incident);
		}
		return emergencyIncident;
	}

	/**
	 * Creates {@link EmergencyIncident} instance from the {@link JsonObject}
	 *
	 * @param incident data from the Spillman Api.
	 * @return {@link EmergencyIncident} instance.
	 */
	private EmergencyIncident createEmergencyIncident(JsonObject incident) {
		EmergencyIncident emergencyIncident = new EmergencyIncident();
		String id = utils.getStringByKey(incident, InterfaceConstants.EmergencyIncidentProperties.ID_JSON_KEY);
		if (validateRequiredStringField(id, InterfaceConstants.EmergencyIncidentProperties.ID_JSON_KEY)) {
			emergencyIncident.setId(id);
			emergencyIncident.setKey(id);
		}
		List<Subject> subjects = new ArrayList<>();
		JsonArray involvedCADSubjects = utils.getJsonArrayByKey(incident, InterfaceConstants.EmergencyIncidentProperties.INVOLVED_CAD_SUBJECTS);
		for (JsonElement involvedCadSubjectElement : involvedCADSubjects) {
			JsonObject involvedCadSubject = involvedCadSubjectElement.getAsJsonObject();
			JsonObject jsonSubject = utils.getJsonByKey(involvedCadSubject, InterfaceConstants.EmergencyIncidentProperties.SUBJECT);
			if (jsonSubject != null) {
				Subject subject = new Subject();
				String role = utils.getStringByKey(involvedCadSubject, "role");
				if ("Complainant".equals(role) || "ReportingParty".equals(role)) {
					JsonObject jsonPerson = utils.getJsonByKey(jsonSubject, InterfaceConstants.EmergencyIncidentProperties.PERSON);
					Person emergencyIncidentPerson = createEmergencyIncidentPerson(jsonPerson);
					subject.setPerson(emergencyIncidentPerson);
					subject.setRole(Collections.singletonList(role));
				}
				subjects.add(subject);
			}
			emergencyIncident.setSubjects(subjects);
		}

		return emergencyIncident;
	}

	@Override
	public UpdateEmergencyIncident translateUpdateIncident(JsonObject payload) {
		clearValidationResults();
		UpdateEmergencyIncident updateIncident = new UpdateEmergencyIncident();
		JsonArray data = utils.getJsonArrayByKey(payload, InterfaceConstants.GeneralProperties.DATA_JSON_KEY);
		if (validateRequiredObjectField(data, InterfaceConstants.GeneralProperties.DATA_JSON_KEY)
			&& validateRequiredObjectField(data.get(0), InterfaceConstants.GeneralProperties.DATA_JSON_KEY)) {

			JsonObject updateIncidentJson = data.get(0).getAsJsonObject();
			JsonObject old = utils.getJsonByKey(updateIncidentJson, InterfaceConstants.EmergencyIncidentProperties.OLD_JSON_KEY);
			JsonObject __new = utils.getJsonByKey(updateIncidentJson, InterfaceConstants.EmergencyIncidentProperties.NEW_JSON_KEY);
			if (validateRequiredObjectField(old, InterfaceConstants.EmergencyIncidentProperties.OLD_JSON_KEY)
				&& validateRequiredObjectField(__new, InterfaceConstants.EmergencyIncidentProperties.NEW_JSON_KEY)
				&& validateRequiredStringField(utils.getStringByKey(old, InterfaceConstants.EmergencyIncidentProperties.ID_JSON_KEY), InterfaceConstants.EmergencyIncidentProperties.ID_JSON_KEY)
				&& validateRequiredStringField(utils.getStringByKey(__new, InterfaceConstants.EmergencyIncidentProperties.ID_JSON_KEY), InterfaceConstants.EmergencyIncidentProperties.ID_JSON_KEY)) {

				EmergencyIncident newModel = createEmergencyIncident(__new);

				EmergencyIncident oldModel = createEmergencyIncident(old);

				updateIncident.set__new(newModel);
				updateIncident.setOld(oldModel);
			}
		}
		return updateIncident;
	}

	/**
	 * Creates {@link Person} instance from the {@link JsonObject}
	 *
	 * @param jsonPerson "person" jsonObject from the json.
	 * @return {@link Person} instance.
	 */
	private Person createEmergencyIncidentPerson(JsonObject jsonPerson) {
		Person person = null;
		if (jsonPerson != null) {
			person = new Person();
			person.setFirstName(utils.getStringByKey(jsonPerson, InterfaceConstants.EmergencyIncidentProperties.FIRST_NAME));
			person.setMiddleName(utils.getStringByKey(jsonPerson, InterfaceConstants.EmergencyIncidentProperties.MIDDLE_NAME));
			person.setLastName(utils.getStringByKey(jsonPerson, InterfaceConstants.EmergencyIncidentProperties.LAST_NAME));
			person.setSuffix(utils.getStringByKey(jsonPerson, InterfaceConstants.EmergencyIncidentProperties.SUFFIX));
			person.setDateOfBirth(getDateByKey(jsonPerson, InterfaceConstants.EmergencyIncidentProperties.BIRTH_DATE, dateFormatter));
			String age = utils.getStringByKey(jsonPerson, InterfaceConstants.EmergencyIncidentProperties.AGE);
			if (StringUtils.isNotBlank(age)) {
				person.setAge(Long.valueOf(age));
			}
			person.setHeight(utils.getStringByKey(jsonPerson, InterfaceConstants.EmergencyIncidentProperties.HEIGHT_IN_INCHES));
			String heightInInches = utils.getStringByKey(jsonPerson, InterfaceConstants.EmergencyIncidentProperties.WEIGHT_IN_POUNDS);
			if (StringUtils.isNotBlank(heightInInches)) {
				person.setWeight(Long.valueOf(heightInInches));
			}
			person.setRace(createLookup(utils.getStringByKey(jsonPerson, InterfaceConstants.EmergencyIncidentProperties.RACE)));
			person.setGender(createLookup(utils.getStringByKey(jsonPerson, InterfaceConstants.EmergencyIncidentProperties.GENDER)));
			person.setBuild(createLookup(utils.getStringByKey(jsonPerson, InterfaceConstants.EmergencyIncidentProperties.PHYSICAL_BUILD)));
			person.setHairColor(createLookup(utils.getStringByKey(jsonPerson, InterfaceConstants.EmergencyIncidentProperties.HAIR_COLOR)));
			person.setEyeColor(createLookup(utils.getStringByKey(jsonPerson, InterfaceConstants.EmergencyIncidentProperties.EYE_COLOR)));

			JsonObject driverLicense = utils.getJsonByKey(jsonPerson, InterfaceConstants.EmergencyIncidentProperties.DRIVER_LICENSE);
			if (driverLicense != null) {
				person.setDriverLicenseNumber(utils.getStringByKey(driverLicense, InterfaceConstants.EmergencyIncidentProperties.NUMBER));
				person.setDriverLicenseState(utils.getStringByKey(driverLicense, InterfaceConstants.EmergencyIncidentProperties.STATE));
			}
		}
		return person;
	}

	/**
	 * Gets the date from json object
	 * @param payload - json object
	 * @return
	 */
	private Date getDateByKey(JsonObject payload, String key, SimpleDateFormat formatter) {
		Date result = null;
		String strDate = utils.getStringByKey(payload, key);
		if (strDate != null) {
			try {
				result = formatter.parse(strDate);
			}
			catch (ParseException e) {
				LOGGER.error("Failed to parse submitted date.");
			}

		}

		return result;
	}

	/**
	 * Creates the {@link Lookup} object.
	 *
	 * @param value to be set as a Uid.
	 * @return {@link Lookup} instance.
	 */
	private Lookup createLookup(String value) {
		Lookup lookup = new Lookup();
		lookup.setUid(value);
		return lookup;
	}

	/**
	 * Creates the  {@link List<Lookup>} from {@link JsonArray} elements by objectName.
	 *
	 * @param array input {@link JsonArray} object.
	 * @param objectName name of object in the <code>array</code>
	 * @return {@link List<Lookup>} instance.
	 */
	private List<Lookup> createLookupList(JsonArray array, String objectName) {
		List<Lookup> result = new ArrayList<>();
		for (JsonElement element : array) {
			JsonObject jsonObject = element.getAsJsonObject();
			result.add(createLookup(utils.getStringByKey(jsonObject, objectName)));
		}
		return result;
	}

	/**
	 * Gets the list with validation result
	 * @return list with validation result
	 */
	public List<ValidationResult> getValidationResults() {
		return validationResults;
	}

	/**
	 * Clears the list with validation result
	 */
	private void clearValidationResults() {
		this.validationResults = new ArrayList<>();
	}

	/**
	 * Checks if field is empty or null. If string is invalid adds error message to the validationResults list
	 * @param field to validate
	 * @param fieldName name of the field
	 * @return true if present, false if not
	 */
	private boolean validateRequiredStringField(String field, String fieldName) {
		if (StringUtils.isBlank(field)) {
			validationResults.add(new ValidationResult(String.format("%s is required field. ", fieldName), ValidationErrorType.MISSING_DATA));
			return false;
		}
		return true;
	}

	/**
	 * Checks if field is null. If so adds error message to the validationResults list
	 * @param field to validate
	 * @param fieldName name of the field
	 * @return true if present, false if not
	 */
	private boolean validateRequiredObjectField(Object field, String fieldName) {
		if (field == null) {
			validationResults.add(new ValidationResult(String.format("%s is required field. ", fieldName), ValidationErrorType.MISSING_DATA));
			return false;
		}
		return true;
	}
}
