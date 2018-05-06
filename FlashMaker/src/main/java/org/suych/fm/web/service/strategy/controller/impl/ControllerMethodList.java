package org.suych.fm.web.service.strategy.controller.impl;

import static org.suych.fm.constant.ConstantJavaSyntax.COMMA;
import static org.suych.fm.constant.ConstantJavaSyntax.EQUAL_SIGN;
import static org.suych.fm.constant.ConstantJavaSyntax.INTEGER;
import static org.suych.fm.constant.ConstantJavaSyntax.LEFT_ANGLE_BRACKETS;
import static org.suych.fm.constant.ConstantJavaSyntax.LEFT_BRACE;
import static org.suych.fm.constant.ConstantJavaSyntax.LEFT_BRACKET;
import static org.suych.fm.constant.ConstantJavaSyntax.LINE;
import static org.suych.fm.constant.ConstantJavaSyntax.NULL;
import static org.suych.fm.constant.ConstantJavaSyntax.POINT;
import static org.suych.fm.constant.ConstantJavaSyntax.RETURN;
import static org.suych.fm.constant.ConstantJavaSyntax.RETURN_NEWLINE;
import static org.suych.fm.constant.ConstantJavaSyntax.RIGHT_ANGLE_BRACKETS;
import static org.suych.fm.constant.ConstantJavaSyntax.RIGHT_BRACE;
import static org.suych.fm.constant.ConstantJavaSyntax.RIGHT_BRACKET;
import static org.suych.fm.constant.ConstantJavaSyntax.SEMICOLON;
import static org.suych.fm.constant.ConstantJavaSyntax.SPACE;
import static org.suych.fm.constant.ConstantJavaSyntax.STRING;
import static org.suych.fm.constant.ConstantJavaSyntax.TAB;

import java.util.List;

import org.springframework.stereotype.Component;
import org.suych.fm.base.BaseInfo;
import org.suych.fm.constant.ConstantMethodAccessModifier;
import org.suych.fm.constant.ConstantMethodName;
import org.suych.fm.constant.ConstantParameterName;
import org.suych.fm.constant.ConstantParameterType;
import org.suych.fm.constant.ConstantParameterValue;
import org.suych.fm.constant.ConstantStrategyComponentName;
import org.suych.fm.constant.ConstantSuffix;
import org.suych.fm.util.PropertyUtils;
import org.suych.fm.util.generate.model.java.AnnotationStructure;
import org.suych.fm.util.generate.model.java.MethodStructure;
import org.suych.fm.util.generate.model.java.ParamterStructure;
import org.suych.fm.web.service.strategy.controller.IControllerMethod;
import org.suych.fm.web.service.strategy.controller.impl.common.LoggerBody;

@Component(ConstantStrategyComponentName.CONTROLLER_LIST)
public class ControllerMethodList implements IControllerMethod {

	@Override
	public MethodStructure assemble() {
		MethodStructure result = new MethodStructure();
		// 注解
		List<AnnotationStructure> methodAnnotation = assembleMethodAnnotation(ConstantMethodName.LIST);
		// 返回值
		String returnValue = STRING;
		// 方法名
		String methodName = ConstantMethodName.LIST;
		// 参数
		List<ParamterStructure> parameter = assembleParameter();
		// 方法体
		StringBuilder methodBody = new StringBuilder();
		// 是否使用Logger
		Boolean useLogger = Boolean.valueOf(PropertyUtils.getProperty("controller.logger.use"));
		// Logger1~4
		LoggerBody.addPrefix(methodBody, useLogger);
		// L1
		LoggerBody.addTab(methodBody, useLogger);
		methodBody.append(TAB + TAB + INTEGER + SPACE + ConstantParameterName.PAGE_NUM + SPACE + EQUAL_SIGN + SPACE
				+ ConstantParameterValue.ONE + SEMICOLON + RETURN_NEWLINE);
		// L2
		LoggerBody.addTab(methodBody, useLogger);
		methodBody.append(TAB + TAB + INTEGER + SPACE + ConstantParameterName.PAGE_SIZE + SPACE + EQUAL_SIGN + SPACE
				+ ConstantParameterValue.TWENTY + SEMICOLON + RETURN_NEWLINE);
		// L3
		LoggerBody.addTab(methodBody, useLogger);
		String domainClassName = BaseInfo.getDomainClassName();
		String resultField = BaseInfo.getDomainClassFieldName() + ConstantSuffix.S.getType();
		methodBody.append(TAB + TAB + ConstantParameterType.PAGE_INFO + LEFT_ANGLE_BRACKETS + domainClassName
				+ RIGHT_ANGLE_BRACKETS + SPACE + resultField + SPACE + EQUAL_SIGN + SPACE
				+ BaseInfo.getServiceInterfaceFieldName() + POINT + ConstantMethodName.LIST + LEFT_BRACKET
				+ ConstantParameterName.PAGE_NUM + COMMA + SPACE + ConstantParameterName.PAGE_SIZE + RIGHT_BRACKET
				+ SEMICOLON + RETURN_NEWLINE);
		// L4
		LoggerBody.addTab(methodBody, useLogger);
		methodBody.append(TAB + TAB + resultField + POINT + ConstantMethodName.GET_LIST + LEFT_BRACKET + RIGHT_BRACKET
				+ POINT + ConstantMethodName.FOREACH + LEFT_BRACKET + LEFT_BRACKET + ConstantParameterName.N
				+ RIGHT_BRACKET + SPACE + LINE + RIGHT_ANGLE_BRACKETS + SPACE + LEFT_BRACE + RETURN_NEWLINE);
		// L5
		LoggerBody.addTab(methodBody, useLogger);
		methodBody.append(TAB + TAB + TAB + ConstantParameterType.SYSTEM + POINT + ConstantParameterName.OUT + POINT
				+ ConstantMethodName.PRINTLN + LEFT_BRACKET + ConstantParameterName.N + RIGHT_BRACKET + SEMICOLON
				+ RETURN_NEWLINE);
		// L6
		LoggerBody.addTab(methodBody, useLogger);
		methodBody.append(TAB + TAB + RIGHT_BRACE + RIGHT_BRACKET + SEMICOLON + RETURN_NEWLINE);
		// Logger5~7
		LoggerBody.addSuffix(methodBody, useLogger);
		// L7
		methodBody.append(TAB + TAB + RETURN + SPACE + NULL + SEMICOLON + RETURN_NEWLINE);

		result.setAnnotation(methodAnnotation);
		result.setAccessModifier(ConstantMethodAccessModifier.PUBLIC);
		result.setReturnValue(returnValue);
		result.setName(methodName);
		result.setParameter(parameter);
		result.setMethodBody(methodBody.toString());
		return result;
	}

}
