package org.suych.fm.util;

import org.apache.commons.lang.StringUtils;

public class StringUtil {

	public static String null2Empty(Object input) {
		if (input == null || StringUtils.isBlank(input.toString()) || "null".equals(input.toString())) {
			return "";
		} else {
			return input.toString().trim();
		}
	}

}
