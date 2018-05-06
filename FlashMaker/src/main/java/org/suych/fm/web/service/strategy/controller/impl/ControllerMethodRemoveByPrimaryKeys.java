package org.suych.fm.web.service.strategy.controller.impl;

import static org.suych.fm.constant.ConstantJavaSyntax.ANNOTATIONS_REQUEST_MAPPING;
import static org.suych.fm.constant.ConstantJavaSyntax.ARRAYLIST;
import static org.suych.fm.constant.ConstantJavaSyntax.COMMA;
import static org.suych.fm.constant.ConstantJavaSyntax.DOUBLE_QUOTATION;
import static org.suych.fm.constant.ConstantJavaSyntax.EQUAL_SIGN;
import static org.suych.fm.constant.ConstantJavaSyntax.LEFT_ANGLE_BRACKETS;
import static org.suych.fm.constant.ConstantJavaSyntax.LEFT_BRACE;
import static org.suych.fm.constant.ConstantJavaSyntax.LEFT_BRACKET;
import static org.suych.fm.constant.ConstantJavaSyntax.LIST;
import static org.suych.fm.constant.ConstantJavaSyntax.NEW;
import static org.suych.fm.constant.ConstantJavaSyntax.NULL;
import static org.suych.fm.constant.ConstantJavaSyntax.POINT;
import static org.suych.fm.constant.ConstantJavaSyntax.RETURN;
import static org.suych.fm.constant.ConstantJavaSyntax.RETURN_NEWLINE;
import static org.suych.fm.constant.ConstantJavaSyntax.RIGHT_ANGLE_BRACKETS;
import static org.suych.fm.constant.ConstantJavaSyntax.RIGHT_BRACE;
import static org.suych.fm.constant.ConstantJavaSyntax.RIGHT_BRACKET;
import static org.suych.fm.constant.ConstantJavaSyntax.SEMICOLON;
import static org.suych.fm.constant.ConstantJavaSyntax.SLASH;
import static org.suych.fm.constant.ConstantJavaSyntax.SPACE;
import static org.suych.fm.constant.ConstantJavaSyntax.STRING;
import static org.suych.fm.constant.ConstantJavaSyntax.TAB;
import static org.suych.fm.constant.ConstantJavaSyntax.TODO;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.suych.fm.base.BaseInfo;
import org.suych.fm.constant.ConstantMethodAccessModifier;
import org.suych.fm.constant.ConstantMethodName;
import org.suych.fm.constant.ConstantParameterName;
import org.suych.fm.constant.ConstantParameterType;
import org.suych.fm.constant.ConstantParameterValue;
import org.suych.fm.constant.ConstantStrategyComponentName;
import org.suych.fm.util.PropertyUtils;
import org.suych.fm.util.generate.model.java.AnnotationStructure;
import org.suych.fm.util.generate.model.java.MethodStructure;
import org.suych.fm.util.generate.model.java.ParamterStructure;
import org.suych.fm.web.service.strategy.controller.IControllerMethod;
import org.suych.fm.web.service.strategy.controller.impl.common.LoggerBody;

@Component(ConstantStrategyComponentName.CONTROLLER_REMOVE_BY_PRIMARYKEYS)
public class ControllerMethodRemoveByPrimaryKeys implements IControllerMethod {

	@Override
	public MethodStructure assemble() {
		MethodStructure result = new MethodStructure();
		// 注解
		List<AnnotationStructure> annotation = new ArrayList<AnnotationStructure>();
		AnnotationStructure requestMapping = new AnnotationStructure();
		// 注解属性
		Map<String, String> attribute = new LinkedHashMap<String, String>();
		String value = DOUBLE_QUOTATION + SLASH + ConstantMethodName.REMOVE_BY_PRIMARYKEYS + DOUBLE_QUOTATION;
		String method = LEFT_BRACE + SPACE + ConstantParameterType.REQUEST_METHOD + POINT + ConstantParameterName.POST
				+ COMMA + SPACE + ConstantParameterType.REQUEST_METHOD + POINT + ConstantParameterName.GET + SPACE
				+ RIGHT_BRACE;
		String produces = ConstantParameterValue.PRODUCES_VALUE;
		attribute.put(ConstantParameterName.VALUE, value);
		attribute.put(ConstantParameterName.METHOD, method);
		attribute.put(ConstantParameterName.PRODUCES, DOUBLE_QUOTATION + produces + DOUBLE_QUOTATION);
		requestMapping.setName(ANNOTATIONS_REQUEST_MAPPING);
		requestMapping.setAttribute(attribute);
		annotation.add(requestMapping);
		// 返回值
		String returnValue = STRING;
		// 方法名
		String methodName = ConstantMethodName.REMOVE_BY_PRIMARYKEYS;
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
		methodBody.append(TAB + TAB + LIST + LEFT_ANGLE_BRACKETS + STRING + RIGHT_ANGLE_BRACKETS + SPACE
				+ ConstantParameterName.PRIMARY_KEY_S + SPACE + EQUAL_SIGN + SPACE + NEW + SPACE + ARRAYLIST
				+ LEFT_ANGLE_BRACKETS + STRING + RIGHT_ANGLE_BRACKETS + LEFT_BRACKET + RIGHT_BRACKET + SEMICOLON
				+ RETURN_NEWLINE);
		// L2
		LoggerBody.addTab(methodBody, useLogger);
		methodBody.append(TAB + TAB + ConstantParameterName.PRIMARY_KEY_S + POINT + ConstantMethodName.ADD
				+ LEFT_BRACKET + DOUBLE_QUOTATION + DOUBLE_QUOTATION + RIGHT_BRACKET + SEMICOLON + SPACE + SLASH + SLASH
				+ TODO + RETURN_NEWLINE);
		// L3
		LoggerBody.addTab(methodBody, useLogger);
		methodBody.append(TAB + TAB + BaseInfo.getServiceInterfaceFieldName() + POINT
				+ ConstantMethodName.REMOVE_BY_PRIMARYKEYS + LEFT_BRACKET + ConstantParameterName.PRIMARY_KEY_S
				+ RIGHT_BRACKET + SEMICOLON + RETURN_NEWLINE);
		// Logger5~7
		LoggerBody.addSuffix(methodBody, useLogger);
		// L4
		methodBody.append(TAB + TAB + RETURN + SPACE + NULL + SEMICOLON + RETURN_NEWLINE);

		result.setAnnotation(annotation);
		result.setAccessModifier(ConstantMethodAccessModifier.PUBLIC);
		result.setReturnValue(returnValue);
		result.setName(methodName);
		result.setParameter(parameter);
		result.setMethodBody(methodBody.toString());
		return result;
	}

}
