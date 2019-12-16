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

		public static final String VERSION_2019_1_15_0 = "2019.1.15.0";
		public static final String REQUEST_TYPE = "requestType";
		public static final String UID_JSON_KEY = "uid";
		public static final String DATA_JSON_KEY = "data";
		public static final String DATE_FORMAT = "yyyy-MM-dd";
		public static final String ZONED_DATE_TIME_WITH_MS_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
		public static final String ZONED_DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ssXXX";
		public static final String DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
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
		public static final String DEVICE = "device";
		public static final String DEVICE_KEY = "key";
		public static final String DEVICE_AGENCY_ALIAS = "agencyAlias";
		public static final String DEVICE_ALIAS = "alias";
		public static final String CAD_USER = "cadUser";
		public static final String CAD_USER_KEY = "key";
		public static final String CAD_USER_ALIAS = "alias";
		public static final String CAD_USER_AGENCY_ALIAS = "agencyAlias";
		public static final String ROLE = "role";
		public static final String WHEN_SESSION_CREATED = "whenSessionCreated";
		public static final String WHEN_SESSION_UPDATED = "whenSessionUpdated";
		public static final String API_ACCESS_SCOPE = "apiAccessScope";
		public static final String API_ACCESS_LIST = "apiAccessList";
		public static final String AGENCY_KEY = "agencyKey";
		public static final String MONITOR_AREAS = "monitoringArea";
		public static final String CORRELATION_ID = "correlationId";
		public static final String CUSTOMER_ID = "customerId";
		public static final String SESSION_ID = "sessionId";
		public static final String SERVICE_ID = "serviceId";
		public static final String ROLE_KEY_VAL = "user";
		public static final String UNIT_JSON_KEY = "unit";
		public static final String UNIT_AGENCY_ALIAS = "agency";
		public static final String UNIT_KEY = "key";
		public static final String UNIT_CALLSIGN = "callSign";
		public static final String CAD_USER_AGENCY_KEY = "cadUserAgencyKey";
		public static final String DEVICE_AGENCY_KEY = "deviceAgencyKey";
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
		public static final String RESPONSE_DATA = "responseData";
		public static final String CORRELATION_ID = "correlationId";
		public static final String CUSTOMER_ID = "customerId";
		public static final String SESSION_ID = "sessionId";
		public static final String RESPONSE_TYPE = "responseType";
	}

	/**
	 * Contains constants related to the Emergency Incident processing.
	 */
	public final class EmergencyIncident {

		public static final String CUSTOMER_ID = "customerId";
		public static final String WHEN_CREATED = "whenCreated";
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
			public static final String LOCATIONS = "locations";
			public static final String PHONES = "phones";

			public final class Phone {
				public static final String NUMBER = "number";
			}
		}

		/**
		 *  Contains EmergencyIncident Subject properties.
		 */
		public final class Subject {

			public static final String INVOLVED_CAD_SUBJECTS = "involvedCADSubjects";
			public static final String ROLE = "role";

			public final class NestedSubject {

				public static final String NESTED_SUBJECT = "subject";
				public static final String ID = "id";
			}
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
			public static final String CLEARANCE = "clearance";
			public static final String DISPOSITION = "disposition";
			public static final String OBSERVED = "observed";
			public static final String DETERMINANT = "determinant";
			public static final String SCHEDULED_FOR = "scheduledFor";
			public static final String TYPE = "type";
			public static final String RESPONSIBLE_UNIT_ID = "responsibleUnitId";
			public static final String RESPONDING_UNIT_IDS = "respondingUnitIds";
			public static final String RELATED_RECORDS = "relatedRecords";
			public static final String INCIDENT_LOCATION = "incidentLocation";
			public static final String ZONE = "zone";
			public static final String STATUS_CATEGORY = "statusCategory";

			/**
			 * Contains RelatedRecord properties
			 */
			public final class RelatedRecord {

				public static final String REPORT_NUMBER = "reportNumber";
				public static final String ID = "id";
				public static final String AGENCY = "agency";
			}

			public final class IncidentLocation {

				public static final String ADDRESS = "address";
				public static final String TYPE = "type";
				public static final String ID = "id";
				public static final String ID_DEFAULT_VALUE = "UNKN";

				public final class Address {

					public static final String STREET_FULL_TEXT = "streetFullText";
					public static final String CROSS_STREET = "crossStreets";
					public static final String CITY = "city";
					public static final String STATE = "state";
					public static final String ZIP = "zip";
					public static final String LATITUDE = "latitude";
					public static final String LONGITUDE = "longitude";
					public static final String ID = "id";
					public static final String ALERTS = "alerts";
					public static final String HOUSE_NUMBER = "houseNumber";

					public final class Alert {

						public static final String TYPE = "type";
						public static final String ALERT = "alert";
						public static final String COMMENT = "comment";
						public static final String LEVEL = "level";
						public static final String START_DATE = "startDate";
						public static final String END_DATE = "endDate";
					}
				}
			}
		}

		/**
		 *  Contains EmergencyIncident Vehicle properties.
		 */
		public final class Vehicle {

			public static final String INVOLVED_VEHICLES = "involvedVehicles";
			public static final String INVOLVED_KEY = "key";
			public static final String INVOLVED_RELATION = "relation";
			public static final String INVOLVED_VEHICLE = "vehicle";
			public static final String LICENCE_PLATE = "licensePlate";
			public static final String LICENCE_STATE = "licenseState";
			public static final String LICENCE_TYPE = "licenseType";
			public static final String LICENSE_EXPIRATION = "licenseExpiration";
			public static final String YEAR = "year";
			public static final String MAKE = "make";
			public static final String MODEL = "model";
			public static final String PRIMARY_COLOR = "primaryColor";
			public static final String SECONDARY_COLOR = "secondaryColor";
			public static final String VIN = "vin";
			public static final String OWNER = "owner";
			public static final String COMMENT = "comment";
			public static final String TYPE = "type";
		}

		/**
		 * Contains general properties related to the EmergencyIncident.
		 */
		public final class GeneralProperties{
			public static final String UPDATE_INCIDENT_REQUEST_TYPE = "UpdateIncidentRequest";
			public static final String CREATE_INCIDENT_REQUEST_TYPE = "CreateIncidentRequest";
			public static final String UNIT_STATUS_CREATE_REQUEST_TYPE = "CreateUnitStatusRequest";
			public static final String UNIT_STATUS_UPDATE_REQUEST_TYPE = "UpdateUnitStatusRequest";
			public static final String UNIT_STATUS_DELETE_REQUEST_TYPE = "DeleteUnitStatusRequest";
			public static final String BULK_UNITS_UPDATE_REQUEST_TYPE = "BulkUnitsUpdateRequest";
			public static final String BULK_INCIDENTS_UPDATE_REQUEST_TYPE = "BulkIncidentsUpdateRequest";
			public static final String ID_JSON_KEY = "id";
			public static final String OLD_JSON_KEY = "old";
			public static final String NEW_JSON_KEY = "new";
			public static final String REPORTED_EMERGENCY_LOCATION_KEY = "reportedEmergencyLocation";
		}

		/**
		 * Contains IncidentComment properties.
		 */
		public final class Comment {
			public static final String INCIDENT_COMMENTS = "incidentComments";
			public static final String ID = "id";
			public static final String RECORD_TITLE = "recordTitle";
			public static final String CALL_NUMBER = "callNumber";
			public static final String WHEN_ENTERED = "whenEntered";
			public static final String URGENCY = "urgency";
			public static final String COMMENT = "comment";
			public static final String ROOT_COMMENT = "rootCommentId";
			public static final String AUDIENCE = "audience";
			public static final String SOURCE = "source";
			public static final String ON_BEHALF_OF_UNIT = "onBehalfOfUnit";
			public static final String ON_BEHALF_OF_UNIT_ID = "id";
			public static final String ON_BEHALF_OF_USER = "onBehalfOfUser";
			public static final String ON_BEHALF_OF_USER_SNAME = "shortName";

			public final class EnteredBy {
				public static final String ENTERED_BY = "enteredBy";
				public static final String ABBR = "abbr";
				public static final String DESC = "desc";
				public static final String SNAME = "shortName";
				public static final String AGENCY = "agency";
			}
		}

	}

	public final class Unit {
		/**
		 * Contains general properties related to the EmergencyIncident.
		 */
		public final class GeneralProperties{
			public static final String CUSTOMER_ID = "customerId";
			public static final String UNIT = "unit";
			public static final String DATA = "data";
			public static final String UNIT_AGENCY = "unitAgency";
			public static final String UNIT_STATUS_CODE = "unitStatusCode";
			public static final String UNIT_STATUS_ACTION = "unitStatusAction";
			public static final String UNIT_TYPE_CODE = "unitType";
			public static final String WHEN_STATUS_DECLARED = "whenStatusDeclared";
			public static final String MINIMUM_STAFFING_LEVEL = "minimumStaffingLevel";
			public static final String IS_PERSONNEL_OUTSIDE_UNIT = "isPersonnelOutsideUnit";
			public static final String OLD_JSON_KEY = "old";
			public static final String NEW_JSON_KEY = "new";
			public static final String UNIT_ZONE = "unitZone";
			public static final String SHIFT_ID = "shiftId";
			public static final String UNIT_DESCRIPTION = "unitDescription";
			public static final String EQUIPMENT = "equipment";
			public static final String ASSIGNED_PERSONNEL = "assignedPersonnel";
			public static final String CURRENT_GPS_DATA = "currentGpsData";
			public static final String NEXT_STATUS_KEY = "nextStatusKey";
		}

		public final class AssignedIncident {
			public static final String CALL_ID = "callId";
			public static final String KEY = "key";
			public static final String ID = "id";
			public static final String DISPATCH_KEY = "dispatchKey";
			public static final String DISPATCH_ALIAS = "dispatchAlias";
		}

		public final class Equipment {

			private Equipment() {
			}

			public static final String AGENCY_KEY = "agencyKey";
			public static final String TYPE_KEY = "typeKey";
			public static final String EQUIPMENT_ITEM_NAME = "equipmentItemName";
			public static final String KEY = "key";
		}

		public final class Personnel {

			private Personnel() {
			}

			public static final String PERSONNEL_HANDLE = "personnelHandle";
			public static final String EQUIPMENT = "equipment";
			public static final String CAPABILITIES = "capabilities";
		}

		public final class PersonnelHandle {

			private PersonnelHandle() {
			}

			public static final String KEY = "key";
			public static final String ALIAS = "alias";
			public static final String AGENCY_ALIAS = "agencyAlias";
		}

		public final class GpsData {

			private GpsData() {
			}

			public static final String LATITUDE = "latitude";
			public static final String LONGITUDE = "longitude";
			public static final String HEADING = "heading";
			public static final String SPEED = "speed";
			public static final String WHEN_REPORTED = "whenReported";
		}
	}

	public final class RefreshUnitData {

		private RefreshUnitData() {}

		public static final String UNIT_LIST = "unitList";
		public static final String IS_FIRST_BATCH_UPDATE = "isFirstBatchUpdate";
		public static final String IS_LAST_BATCH_UPDATE = "isLastBatchUpdate";
	}

	public final class RefreshIncidentData {

		private RefreshIncidentData() {}

		public static final String EMERGENCY_INCIDENT_LIST = "emergencyIncidentList";
	}

}
