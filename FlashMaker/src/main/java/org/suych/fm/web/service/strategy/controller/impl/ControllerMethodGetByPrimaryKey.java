package org.suych.fm.web.service.strategy.controller.impl;

import static org.suych.fm.constant.ConstantJavaSyntax.DOUBLE_QUOTATION;
import static org.suych.fm.constant.ConstantJavaSyntax.EQUAL_SIGN;
import static org.suych.fm.constant.ConstantJavaSyntax.LEFT_BRACKET;
import static org.suych.fm.constant.ConstantJavaSyntax.NULL;
import static org.suych.fm.constant.ConstantJavaSyntax.POINT;
import static org.suych.fm.constant.ConstantJavaSyntax.RETURN;
import static org.suych.fm.constant.ConstantJavaSyntax.RETURN_NEWLINE;
import static org.suych.fm.constant.ConstantJavaSyntax.RIGHT_BRACKET;
import static org.suych.fm.constant.ConstantJavaSyntax.SEMICOLON;
import static org.suych.fm.constant.ConstantJavaSyntax.SLASH;
import static org.suych.fm.constant.ConstantJavaSyntax.SPACE;
import static org.suych.fm.constant.ConstantJavaSyntax.STRING;
import static org.suych.fm.constant.ConstantJavaSyntax.TAB;
import static org.suych.fm.constant.ConstantJavaSyntax.TODO;

import java.util.List;

import org.springframework.stereotype.Component;
import org.suych.fm.base.BaseInfo;
import org.suych.fm.constant.ConstantMethodAccessModifier;
import org.suych.fm.constant.ConstantMethodName;
import org.suych.fm.constant.ConstantParameterName;
import org.suych.fm.constant.ConstantParameterType;
import org.suych.fm.constant.ConstantStrategyComponentName;
import org.suych.fm.util.PropertyUtils;
import org.suych.fm.util.generate.model.java.AnnotationStructure;
import org.suych.fm.util.generate.model.java.MethodStructure;
import org.suych.fm.util.generate.model.java.ParamterStructure;
import org.suych.fm.web.service.strategy.controller.IControllerMethod;
import org.suych.fm.web.service.strategy.controller.impl.common.LoggerBody;

@Component(ConstantStrategyComponentName.CONTROLLER_GET_BY_PRIMARYKEY)
public class ControllerMethodGetByPrimaryKey implements IControllerMethod {

	@Override
	public MethodStructure assemble() {
		MethodStructure result = new MethodStructure();
		// 注解
		List<AnnotationStructure> methodAnnotation = assembleMethodAnnotation(ConstantMethodName.GET_BY_PRIMARYKEY);
		// 返回值
		String returnValue = STRING;
		// 方法名
		String methodName = ConstantMethodName.GET_BY_PRIMARYKEY;
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
		methodBody.append(TAB + TAB + STRING + SPACE + ConstantParameterName.PRIMARY_KEY + SPACE + EQUAL_SIGN + SPACE
				+ DOUBLE_QUOTATION + DOUBLE_QUOTATION + SEMICOLON + SPACE + SLASH + SLASH + SPACE + TODO
				+ RETURN_NEWLINE);
		// L2
		LoggerBody.addTab(methodBody, useLogger);
		methodBody.append(TAB + TAB + BaseInfo.getDomainClassName() + SPACE + BaseInfo.getDomainClassFieldName() + SPACE
				+ EQUAL_SIGN + SPACE + BaseInfo.getServiceInterfaceFieldName() + POINT
				+ ConstantMethodName.GET_BY_PRIMARYKEY + LEFT_BRACKET + ConstantParameterName.PRIMARY_KEY
				+ RIGHT_BRACKET + SEMICOLON + RETURN_NEWLINE);
		// L3
		LoggerBody.addTab(methodBody, useLogger);
		methodBody.append(TAB + TAB + ConstantParameterType.SYSTEM + POINT + ConstantParameterName.OUT + POINT
				+ ConstantMethodName.PRINTLN + LEFT_BRACKET + BaseInfo.getDomainClassFieldName() + RIGHT_BRACKET
				+ SEMICOLON + RETURN_NEWLINE);
		// Logger5~7
		LoggerBody.addSuffix(methodBody, useLogger);
		// L4
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
