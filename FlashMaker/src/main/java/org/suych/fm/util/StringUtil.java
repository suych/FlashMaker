package org.suych.fm.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class StringUtil {

	public static final char UNDERLINE = '_';

	public static String null2Empty(Object input) {
		if (input == null || StringUtils.isBlank(input.toString()) || "null".equals(input.toString())) {
			return "";
		} else {
			return input.toString().trim();
		}
	}

	/**
	 * 将字符串首字母大写
	 * 
	 * @param param
	 * @return
	 */
	public static String firstLetterToUpperCase(String param) {
		String result = "";
		if (param.length() > 1) {
			String firstChar = param.substring(0, 1).toUpperCase(); // 类名首字母小写
			String otherChar = param.substring(1);
			result = firstChar + otherChar;
		} else {
			result = param.toUpperCase();
		}
		return result;
	}

	/**
	 * 将字符串首字母小写
	 * 
	 * @param param
	 * @return
	 */
	public static String firstLetterToLowerCase(String param) {
		String result = "";
		if (param.length() > 1) {
			String firstChar = param.substring(0, 1).toLowerCase(); // 类名首字母小写
			String otherChar = param.substring(1);
			result = firstChar + otherChar;
		} else {
			result = param.toUpperCase();
		}
		return result;
	}

	/**
	 * 驼峰格式字符串转换为下划线格式字符串
	 * 
	 * @param param
	 * @return
	 */
	public static String camelToUnderline(String param) {
		if (param == null || "".equals(param.trim())) {
			return "";
		}
		int len = param.length();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			char c = param.charAt(i);
			if (Character.isUpperCase(c)) {
				sb.append(UNDERLINE);
				sb.append(Character.toLowerCase(c));
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	/**
	 * 下划线格式字符串转换为驼峰格式字符串
	 * 
	 * @param param
	 * @return
	 */
	public static String underlineToCamel(String param) {
		if (param == null || "".equals(param.trim())) {
			return "";
		}
		int len = param.length();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			char c = param.charAt(i);
			if (c == UNDERLINE) {
				if (++i < len) {
					sb.append(Character.toUpperCase(param.charAt(i)));
				}
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	/**
	 * 下划线格式字符串转换为驼峰格式字符串2
	 * 
	 * @param param
	 * @return
	 */
	public static String underlineToCamel2(String param) {
		if (param == null || "".equals(param.trim())) {
			return "";
		}
		StringBuilder sb = new StringBuilder(param);
		Matcher mc = Pattern.compile("_").matcher(param);
		int i = 0;
		while (mc.find()) {
			int position = mc.end() - (i++);
			sb.replace(position - 1, position + 1, sb.substring(position, position + 1).toUpperCase());
		}
		return sb.toString();
	}

	/**
	 * 下划线格式字符串转换为驼峰格式字符串，并把首字母小写
	 * 
	 * @param param
	 * @return
	 */
	public static String underlineToCamelAndFirstLetterToLowerCase(String param) {
		return firstLetterToLowerCase(underlineToCamel(param));
	}
}
