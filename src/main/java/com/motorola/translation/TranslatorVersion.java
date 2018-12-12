/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.translation;

import org.apache.commons.lang3.StringUtils;

/**
 * Encapsulates methods for parsing and comparing SpillmanApi versions received in request headers.
 */
class TranslatorVersion {

	private int[] version;

	public TranslatorVersion(int major, int minor, int sprint, int build) {
		this.version = new int[4];
		this.version[0] = major;
		this.version[1] = minor;
		this.version[2] = sprint;
		this.version[3] = build;
	}

	/**
	 * Parse version string and return new {@link TranslatorVersion}.
	 * This method allows to use shorter versions like: '2019.1.15', '2019.1' or '2019'.
	 * The missing parts will be considered to equal '0' (Exmpl.: '2019.1' => '2019.1.0.0', etc.).
	 * If input string contains unrecognizable value - the process will stop and all remaining parts will be set to '0'.
	 */
	public static TranslatorVersion parse(String version) {
		int[] ver = {0, 0, 0, 0};
		if (StringUtils.isNotBlank(version)) {
			try {
				for (int i = 0; i < 4; i++) {
					int dotPos = version.indexOf('.');
					if (dotPos == -1) {
						dotPos = version.length();
					}
					ver[i] = Integer.parseInt(version.substring(0, dotPos));
					version = version.substring(dotPos + 1);
				}
			}
			catch (NumberFormatException | IndexOutOfBoundsException ex) {
				// abort parsing process
			}
		}
		return new TranslatorVersion(ver[0], ver[1], ver[2], ver[3]);
	}

	/**
	 * Check if this version is not older than other.
	 */
	public boolean notOlderThan(TranslatorVersion version) {
		boolean result = true;
		for (int i = 0; i < 4; i++) {
			if (this.version[i] < version.version[i]) {
				result = false;
			}
			if (!result || this.version[i] > version.version[i]) {
				break;
			}
		}
		return result;
	}

	@Override
	public String toString() {
		return version[0] + "." + version[1] + "." + version[2] + "." + version[3];
	}
}
