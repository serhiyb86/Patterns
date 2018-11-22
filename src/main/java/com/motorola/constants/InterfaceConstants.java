/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.constants;

/**
 * Placeholder class for constants
 */
public final class InterfaceConstants {

	/**
	 * Contains general constants related to whole interface.
	 */
	public final class GeneralProperties {

		public static final String VERSION_2019_1 = "2019.1";
		public static final String REQUEST_TYPE = "requestType";
		public static final String UID_JSON_KEY = "uid";
		public static final String DATA_JSON_KEY = "data";
	}

	/**
	 * Contains constants for the Http headers.
	 */
	public final class HttpHeaderProperties {

		public static final String ACCESS_TOKEN = "accessToken";
		public static final String API_URL = "cloudApiUrl";
		public static final String SPILLMAN_VERSION = "spillmanVersion";
	}

	/**
	 * Contains constants related to the BookOn processing.
	 */
	public final class BookOnProperties {

		public static final String BOOK_ON_REQUEST_TYPE = "BookOnRequest";
		public static final String DEVICE_ID = "deviceId";
		public static final String USER_ID = "userId";
		public static final String WHEN_SESSION_CREATED = "whenSessionCreated";
		public static final String WHEN_SESSION_UPDATED = "whenSessionUpdated";
		public static final String API_ACCESS_LIST = "apiAccessList";
		public static final String PERMISSION_ID = "permissionId";
		public static final String MONITOR_AREAS = "monitorAreas";
		public static final String AREAS = "areas";
		public static final String CORRELATION_ID = "correlationId";
		public static final String CUSTOMER_ID = "customerId";
		public static final String SESSION_ID = "sessionId";
		public static final String ROLE_KEY_VAL = "user";
		public static final String ADDITIONAL_INFO_JSON_KEY = "additionalInfo";
		public static final String UNIT_JSON_KEY = "unit";
		public static final String AGENCY_JSON_KEY = "agency";
		public static final String KEY_JSON_KEY = "key";
	}

	/**
	 * Contains constants related to the BookOff and Notification processing.
	 */
	public final class NotificationProperties {

		public static final String ERROR_NOTIFICATION_REQUEST_TYPE = "ErrorNotificationRequest";
		public static final String BOOK_OFF_REQUEST_TYPE = "BookOffRequest";
		public static final String ERROR = "error";
		public static final String ERROR_CODE = "errorCode";
		public static final String MESSAGE = "message";
		public static final String SERVICE_ID = "serviceId";
		public static final String NOTIFICATION_TYPE = "notificationType";
		public static final String WHEN_SUBMITTED = "whenSubmitted";
		public static final String RESULT_NATURE = "resultNature";
		public static final String CORRELATION_ID = "correlationId";
		public static final String CUSTOMER_ID = "customerId";
		public static final String SESSION_ID = "sessionId";
	}

	/**
	 * Contains constants related to the Emergency Incident processing.
	 */
	public final class EmergencyIncident {

		/**
		 *  Contains EmergencyIncident Person properties.
		 */
		public final class Person {

			public static final String PERSON = "person";
			public static final String FIRST_NAME = "firstName";
			public static final String MIDDLE_NAME = "middleName";
			public static final String LAST_NAME = "lastName";
			public static final String SUFFIX = "suffix";
			public static final String BIRTH_DATE = "birthDate";
			public static final String AGE = "age";
			public static final String HEIGHT_IN_INCHES = "heightInInches";
			public static final String WEIGHT_IN_POUNDS = "weightInPounds";
			public static final String RACE = "race";
			public static final String GENDER = "gender";
			public static final String PHYSICAL_BUILD = "physicalBuild";
			public static final String HAIR_COLOR = "hairColor";
			public static final String EYE_COLOR = "eyeColor";
			public static final String DRIVER_LICENSE = "driverLicense";
			public static final String NUMBER = "number";
			public static final String STATE = "state";
		}

		/**
		 *  Contains EmergencyIncident Subject properties.
		 */
		public final class Subject {

			public static final String INVOLVED_CAD_SUBJECTS = "involvedCADSubjects";
			public static final String SUBJECT = "subject";
			public static final String ROLE = "role";
		}

		/**
		 *  Contains EmergencyIncident Dispatches properties.
		 */
		public final class Dispatches {

			public static final String DISPATCHES = "dispatches";
			public static final String ACTIVE_CALL_NUMBER = "activeCallNumber";
			public static final String ID = "id";
			public static final String DISCIPLINE = "discipline";
			public static final String DISCIPLINE_NATURE = "disciplineNature";
			public static final String PRIORITY = "priority";
			public static final String AGENCY = "agency";
			public static final String STATUS = "status";
			public static final String WHEN_STATUS_DECLARED = "whenStatusDeclared";
			public static final String DETERMINANT = "determinant";
			public static final String SCHEDULED_FOR = "scheduledFor";
			public static final String TYPE = "type";
			public static final String RESPONSIBLE_UNIT_ID = "responsibleUnitId";
		}

		/**
		 * Contains general properties related to the EmergencyIncident.
		 */
		public final class GeneralProperties{
			public static final String UPDATE_INCIDENT_REQUEST_TYPE = "UpdateIncidentRequest";
			public static final String CREATE_INCIDENT_REQUEST_TYPE = "CreateIncidentRequest";
			public static final String ID_JSON_KEY = "id";
			public static final String OLD_JSON_KEY = "old";
			public static final String NEW_JSON_KEY = "new";
			public static final String DATE_FORMAT = "yyyy-MM-dd";
			public static final String ZONED_DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
		}

	}

}
