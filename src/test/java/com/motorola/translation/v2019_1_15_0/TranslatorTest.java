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

/**
 * Unit test for verify translator.
 */
public class TranslatorTest {

//	private static final Logger LOGGER = LoggerFactory.getLogger(TranslatorTest.class);

	private final BaseTranslator translator = new TranslatorsFactory().getTranslator(InterfaceConstants.GeneralProperties.VERSION_2019_1_15_0);

	public JsonObject initInputPayload(String inputName) {
		JsonObject result = null;
		//  read the file input for current translator version (2019.1)
		String path = InterfaceConstants.GeneralProperties.VERSION_2019_1_15_0 + "/" + inputName;
		File file = getResourceFile(path);
		JsonParser parser = new JsonParser();
		try {
			result = (JsonObject) parser.parse(new FileReader(file));
		}
		catch (FileNotFoundException ex) {
//			LOGGER.error("Parsing input json file error.");
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
