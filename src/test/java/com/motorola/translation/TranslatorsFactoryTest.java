/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.translation;

import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

public class TranslatorsFactoryTest {

	private TranslatorsFactory factory;

	@Before
	public void setUp() {
		factory = new TranslatorsFactory();
	}

	@Test
	public void getTranslator_sameVersion_test() {
		BaseTranslator translator = factory.getTranslator("2019.1.15.0");
		assertNotNull(translator);
		assertTrue(translator instanceof com.motorola.translation.v2019_1_15_0.Translator);
	}

	@Test
	public void getTranslator_greaterVersion_test() {
		BaseTranslator translator = factory.getTranslator("2020.1.15.0");
		assertNotNull(translator);
		assertTrue(translator instanceof com.motorola.translation.v2019_1_15_0.Translator);

		BaseTranslator translator2 = factory.getTranslator("2019.2.15.0");
		assertNotNull(translator2);
		assertTrue(translator2 instanceof com.motorola.translation.v2019_1_15_0.Translator);

		BaseTranslator translator3 = factory.getTranslator("2019.1.20.0");
		assertNotNull(translator3);
		assertTrue(translator3 instanceof com.motorola.translation.v2019_1_15_0.Translator);

		BaseTranslator translator4 = factory.getTranslator("2019.1.15.55");
		assertNotNull(translator4);
		assertTrue(translator4 instanceof com.motorola.translation.v2019_1_15_0.Translator);
	}

	@Test
	public void getTranslator_lesserVersion_test() {
		BaseTranslator translator = factory.getTranslator("0.0.0.0");
		assertNull(translator);

		BaseTranslator translator2 = factory.getTranslator("2019.1.1.0");
		assertNull(translator2);

		BaseTranslator translator3 = factory.getTranslator("2019.0.15.0");
		assertNull(translator3);

		BaseTranslator translator4 = factory.getTranslator("2018.1.15.0");
		assertNull(translator4);
	}

	@Test
	public void getTranslator_shortVersion_test() {
		BaseTranslator translator = factory.getTranslator("2019.1.15");
		assertNotNull(translator);
		assertTrue(translator instanceof com.motorola.translation.v2019_1_15_0.Translator);

		BaseTranslator translator2 = factory.getTranslator("2019.2");
		assertNotNull(translator2);
		assertTrue(translator2 instanceof com.motorola.translation.v2019_1_15_0.Translator);

		BaseTranslator translator3 = factory.getTranslator("2022");
		assertNotNull(translator3);
		assertTrue(translator3 instanceof com.motorola.translation.v2019_1_15_0.Translator);
	}

	@Test
	public void getTranslator_invalidVersion_test() {
		BaseTranslator translator = factory.getTranslator("2019.1.15.0-SNAPSHOT");
		assertNotNull(translator);
		assertTrue(translator instanceof com.motorola.translation.v2019_1_15_0.Translator);

		BaseTranslator translator2 = factory.getTranslator("2019-SNAPSHOT");
		assertNull(translator2);
	}
}