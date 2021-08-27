/*
 * Copyright 2019 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;

/**
 * Utility class for hashing codes to the OneRms format
 */
public class OneRmsHashUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger(OneRmsHashUtils.class);

	private static final String ALGORITHM_SHA_256 = "SHA-256";

	private OneRmsHashUtils() {
	}

	/**
	 * Generates id for Flex code value
	 * @param code coded value
	 * @return id of the OneRms entry
	 */
	public static String convertCodeToOneRmsFormat(String code) {
		if (StringUtils.isNotBlank(code)) {
			try {
				MessageDigest digest = MessageDigest.getInstance(ALGORITHM_SHA_256);
				byte[] encodedHash = digest.digest(code.getBytes(StandardCharsets.UTF_8));
				return Base64.getEncoder().encodeToString(encodedHash).replace("-", "");
			}
			catch (Exception e) {
				LOGGER.error("Error during code converting", e);
			}
		}
		return "";
	}

}
