/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.translation.v2019_1_15_0;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.motorola.constants.InterfaceConstants;
import com.motorola.translation.BaseTranslator;
import com.motorola.translation.TranslatorsFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Unit test for verify translator.
 */
class TranslatorBase {

	private static final Logger LOGGER = LogManager.getLogger(TranslatorBase.class);

	private final BaseTranslator translator = new TranslatorsFactory().getTranslator(InterfaceConstants.GeneralProperties.VERSION_2019_1_15_0);

	public JsonObject initInputPayload(String inputName) {
		JsonObject result = null;
		//  read the file input for current translator version (2019.1)
		String path = InterfaceConstants.GeneralProperties.VERSION_2019_1_15_0 + "/" + inputName;
		File file = getResourceFile(path);

		try {
			result = (JsonObject) JsonParser.parseReader(new FileReader(file));
		}
		catch (FileNotFoundException ex) {
			LOGGER.error("Parsing input json file error.");
		}

		return result;
	}

	public final File getResourceFile(String name) {
		URL resource = getClass().getClassLoader().getResource(name);
		if (resource == null) {
			return null;
		}

		return new File(resource.getFile());
	}

	public BaseTranslator getTranslator() {
		return translator;
	}

}
