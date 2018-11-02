/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.models.representation;

/**
 * Possible error codes
 */
public enum ErrorCodes {
	HTTP("HTTP"),
	SERVICE_ERROR("Service_Error"),
	BOOKON_USER_NOT_EXIST("BookOn_User_Not_Exist"),
	BOOKON_SESSION_EXIST("BookOn_Session_Exist"),
	BOOKON_USER_ASSIGN_CONFLICT("BookOn_User_Assign_Conflict"),
	BOOKON_NO_UNIT("BookOn_No_Unit"),
	BOOKON_NO_AREA("BookOn_No_Area"),
	BOOKON_RADIO_ASSIGNED("BookOn_Radio_Assigned"),
	BOOKON_VEHICLE_ASSIGNED("BookOn_Vehicle_Assigned"),
	BOOKOFF_ASSIGNED_INCEDENT("BookOff_AssignedIncident");

	ErrorCodes(String code) {
		this.code = code;
	}
	private final String code;
}
