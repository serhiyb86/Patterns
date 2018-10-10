/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.translation;

import com.motorola.translation.v2019_1.Translator2019_1;
import org.restlet.engine.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.motorola.constants.InterfaceConstants.VERSION_2019_1;

/**
 * Factory class for the on-premise -> Cad Cloud Api translators
 */
public class TranslatorsFactory {

	private static final Logger LOGGER = LoggerFactory.getLogger(TranslatorsFactory.class);

	/**
	 * Method that returns translator for the specified Spillman version
	 * @param spillmanVersion version to set
	 * @return translator for the specified Spillman version or null for unknown version
	 */
	public static BaseTranslator getTranslator(String spillmanVersion) {
		if (!StringUtils.isNullOrEmpty(spillmanVersion)) {
			switch (spillmanVersion) {
				case VERSION_2019_1:
					return new Translator2019_1();
				default:
					LOGGER.error(String.format("Spillman version: %s is missing or unknown.", spillmanVersion));
			}
		}
		return null;
	}
}
