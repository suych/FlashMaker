package org.suych.fm.web.service.strategy.controller.impl.common;

import static org.suych.fm.constant.ConstantJavaSyntax.CATCH;
import static org.suych.fm.constant.ConstantJavaSyntax.COMMA;
import static org.suych.fm.constant.ConstantJavaSyntax.DOUBLE_QUOTATION;
import static org.suych.fm.constant.ConstantJavaSyntax.IF;
import static org.suych.fm.constant.ConstantJavaSyntax.LEFT_BRACE;
import static org.suych.fm.constant.ConstantJavaSyntax.LEFT_BRACKET;
import static org.suych.fm.constant.ConstantJavaSyntax.POINT;
import static org.suych.fm.constant.ConstantJavaSyntax.RETURN_NEWLINE;
import static org.suych.fm.constant.ConstantJavaSyntax.RIGHT_BRACE;
import static org.suych.fm.constant.ConstantJavaSyntax.RIGHT_BRACKET;
import static org.suych.fm.constant.ConstantJavaSyntax.SEMICOLON;
import static org.suych.fm.constant.ConstantJavaSyntax.SPACE;
import static org.suych.fm.constant.ConstantJavaSyntax.TAB;
import static org.suych.fm.constant.ConstantJavaSyntax.TRY;

import org.suych.fm.constant.ConstantMethodName;
import org.suych.fm.constant.ConstantParameterName;
import org.suych.fm.constant.ConstantParameterType;

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
			methodBody.append(TAB + TAB + TAB + TAB + ConstantParameterName.LOGGER + POINT + ConstantMethodName.DEBUG
					+ LEFT_BRACKET + DOUBLE_QUOTATION + DOUBLE_QUOTATION + RIGHT_BRACKET + SEMICOLON + RETURN_NEWLINE);
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
}
