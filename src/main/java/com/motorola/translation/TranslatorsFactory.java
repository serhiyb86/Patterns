/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.translation;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.motorola.constants.InterfaceConstants;

/**
 * Factory class for the on-premise -> Cad Cloud Api translators
 */
public class TranslatorsFactory {

	private static final Logger LOGGER = LoggerFactory.getLogger(TranslatorsFactory.class);

	private static final List<TranslatorVersion> TRANSLATOR_VERSIONS = Collections.unmodifiableList(Arrays.asList(
		//Place the newest versions on top
		//The order here is important for correct work
		new TranslatorVersion(InterfaceConstants.GeneralProperties.VERSION_2019_1_15_0)
	));

	/**
	 * Method that returns translator for the specified Spillman version
	 * @param spillmanVersion version to set
	 * @return translator for the specified Spillman version or null for unknown version
	 */
	public BaseTranslator getTranslator(String spillmanVersion) {
		switch (findLeastVersionFor(spillmanVersion)) {
			case InterfaceConstants.GeneralProperties.VERSION_2019_1_15_0:
				return new com.motorola.translation.v2019_1_15_0.Translator();
			default:
				LOGGER.error("Spillman version: {} is missing or unknown.", spillmanVersion);
		}
		return null;
	}

	/**
	 * Parse version from request header and find the closest corresponding version from available.
	 */
	private static String findLeastVersionFor(String versionFromRequest) {
		TranslatorVersion requiredVersion = new TranslatorVersion(versionFromRequest);
		return TRANSLATOR_VERSIONS.stream()
			.filter(requiredVersion::notLessThan)
			.findFirst()
			.orElse(requiredVersion)
			.toString();
	}
}
