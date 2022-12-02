/*
 * CAD Cloud Ingest API
 * CAD Ingest API in Azure cloud
 *
 * OpenAPI spec version: 1.0.0
 * Contact: onemobileapiarchitects@motorolasolutions.com
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */
package com.motorola.api.utils;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class RFC3339DateFormat extends SimpleDateFormat {
    @Override
	public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition fieldPosition) {
		DateFormat value = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		value.setTimeZone(TimeZone.getTimeZone("GMT"));
		toAppendTo.append(value);
		return toAppendTo;
	}
}