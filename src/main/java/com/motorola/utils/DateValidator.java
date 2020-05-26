/*
 * Copyright 2020 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

/**
 * Class for validation Date in string format
 */
public class DateValidator {

	/**
	 * returns true if date string is in valid format
	 */
	public static boolean validate(String date) {
		return isDate(date) || isDateTime(date) || isZonedDateTime(date);
	}

	private static boolean isDate(String date) {
		boolean isValid = false;
		try {
			LocalDate.parse(date);
			isValid = true;
		}
		catch (Exception e) {
		}
		return isValid;
	}

	private static boolean isDateTime(String date) {
		boolean isValid = false;
		try {
			LocalDateTime.parse(date);
			isValid = true;
		}
		catch (Exception e) {
		}
		return isValid;
	}

	private static boolean isZonedDateTime(String date) {
		boolean isValid = false;
		try {
			ZonedDateTime.parse(date);
			isValid = true;
		}
		catch (Exception e) {
		}
		return isValid;
	}

}
