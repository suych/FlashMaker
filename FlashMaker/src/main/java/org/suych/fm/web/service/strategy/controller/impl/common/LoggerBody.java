package org.suych.fm.web.service.strategy.controller.impl.common;

import static org.suych.fm.constant.ConstantJavaSyntax.*;

import org.suych.fm.constant.ConstantMethodName;
import org.suych.fm.constant.ConstantParameterName;
import org.suych.fm.constant.ConstantParameterType;
import org.suych.fm.util.PropertyUtils;
import org.suych.fm.util.StringUtil;

public class LoggerBody {

	public static void addPrefix(StringBuilder methodBody, Boolean useLogger) {
		if (useLogger) {
			// Logger1
			methodBody.append(TAB + TAB + TRY + SPACE + LEFT_BRACE + RETURN_NEWLINE);
			// Logger2
			methodBody.append(TAB + TAB + TAB + IF + SPACE + LEFT_BRACKET + ConstantParameterName.LOGGER + POINT
					+ ConstantMethodName.IS_DEBUG_ENABLED + LEFT_BRACKET + RIGHT_BRACKET + RIGHT_BRACKET + SPACE
					+ LEFT_BRACE + RETURN_NEWLINE);
			// Logger3
			// 是否使用自定义参数
			assembleLogger3(methodBody);
			methodBody.append(RIGHT_BRACKET + SEMICOLON + RETURN_NEWLINE);
			// Logger4
			methodBody.append(TAB + TAB + TAB + RIGHT_BRACE + RETURN_NEWLINE);
		}
	}

	public static void addSuffix(StringBuilder methodBody, Boolean useLogger) {
		if (useLogger) {
			// Logger5
			methodBody.append(
					TAB + TAB + RIGHT_BRACE + SPACE + CATCH + SPACE + LEFT_BRACKET + ConstantParameterType.EXCEPTION
							+ SPACE + ConstantParameterName.E + RIGHT_BRACKET + SPACE + LEFT_BRACE + RETURN_NEWLINE);
			// Logger6
			methodBody.append(TAB + TAB + TAB + ConstantParameterName.LOGGER + POINT + ConstantMethodName.ERROR
					+ LEFT_BRACKET + DOUBLE_QUOTATION + DOUBLE_QUOTATION + COMMA + SPACE + ConstantParameterName.E
					+ RIGHT_BRACKET + SEMICOLON + RETURN_NEWLINE);
			// Logger7
			methodBody.append(TAB + TAB + RIGHT_BRACE + RETURN_NEWLINE);
		}
	}

	public static void addTab(StringBuilder methodBody, Boolean useLogger) {
		if (useLogger) {
			methodBody.append(TAB);
		}
	}

	private static void assembleLogger3(StringBuilder methodBody) {
		methodBody.append(
				TAB + TAB + TAB + TAB + ConstantParameterName.LOGGER + POINT + ConstantMethodName.DEBUG + LEFT_BRACKET);
		if (Boolean.valueOf(PropertyUtils.getProperty("controller.method.specifed.param.use"))) {
			// 参数个数
			int count = Integer.valueOf(PropertyUtils.getProperty("controller.method.specifed.param.count"));
			// 参数名称
			String[] nameArray = StringUtil
					.null2Empty(PropertyUtils.getProperty("controller.method.specifed.param.name")).split(",");
			methodBody.append(DOUBLE_QUOTATION);
			for (int i = 0; i < count; i++) {
				String name = nameArray[i];
				methodBody.append(name + EQUAL_SIGN + LEFT_BRACE + RIGHT_BRACE);
				if (i != count - 1) {
					methodBody.append(COMMA + SPACE);
				}
			}
			methodBody.append(DOUBLE_QUOTATION);
			for (int i = 0; i < count; i++) {
				String name = nameArray[i];
				methodBody.append(COMMA + SPACE + name);
			}
		} else {
			methodBody.append(DOUBLE_QUOTATION + DOUBLE_QUOTATION);
		}
	}
}
