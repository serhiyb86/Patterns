/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.translation;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Parse SpillmanApi version from request header.
 *
 * Allows to use shorter versions like: '2019.1.15', '2019.1' or '2019'.
 * The missing parts will be considered to equal '0' (Exmpl.: '2019.1' => '2019.1.0.0', etc.).
 * All chunks of unrecognizable values will be skipped ('2019.1.33-SNAPSHOT.5' => '2019.1.5.0', '2019-SNAP-SNAP' => "0.0.0.0").
 */
class TranslatorVersion {

	private static final String DELIMITER = ".";
	private static final String DELIMITER_REGEXP = "\\.";
	private static final int LENGTH = 4;
	private static final Integer DEFAULT_CHUNK = 0;

	private final List<Integer> versionChunks;

	TranslatorVersion(String version) {
		List<Integer> ver = Stream.of(version.split(DELIMITER_REGEXP))
			.filter(chunk -> chunk.matches("\\d+")) //Exclude non-integer values
			.map(Integer::parseInt)
			.limit(LENGTH)
			.collect(Collectors.toList());

		while (ver.size() < LENGTH) {
			ver.add(DEFAULT_CHUNK);
		}
		this.versionChunks = ver;
	}

	/**
	 * Check if this version is not older than other.
	 */
	boolean notLessThan(TranslatorVersion version) {
		return compare(version) >= 0;
	}

	/**
	 * Compare version with another.
	 *
	 * @param version {@link TranslatorVersion} obj
	 * @return 0 - equals, < 0 - when less than, > 0 - greater than.
	 */
	private int compare(TranslatorVersion version) {
		int result = 0;
		for (int index = 0; index < LENGTH; index++) {
			result = versionChunks.get(index) - version.versionChunks.get(index);
			if (result != 0) {
				return result;
			}
		}
		return result;
	}

	@Override
	public String toString() {
		return versionChunks.stream().map(Object::toString).collect(Collectors.joining(DELIMITER));
	}
}
