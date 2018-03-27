package org.suych.fm.web.service.strategy.serviceimpl.impl;

import static org.suych.fm.constant.ConstantJavaSyntax.ANNOTATIONS_OVERRIDE;
import static org.suych.fm.constant.ConstantJavaSyntax.ANNOTATIONS_TRANSACTIONAL;
import static org.suych.fm.constant.ConstantJavaSyntax.COMMA;
import static org.suych.fm.constant.ConstantJavaSyntax.EQUAL_SIGN;
import static org.suych.fm.constant.ConstantJavaSyntax.INTEGER;
import static org.suych.fm.constant.ConstantJavaSyntax.LEFT_ANGLE_BRACKETS;
import static org.suych.fm.constant.ConstantJavaSyntax.LEFT_BRACKET;
import static org.suych.fm.constant.ConstantJavaSyntax.LIST;
import static org.suych.fm.constant.ConstantJavaSyntax.NEW;
import static org.suych.fm.constant.ConstantJavaSyntax.POINT;
import static org.suych.fm.constant.ConstantJavaSyntax.RETURN;
import static org.suych.fm.constant.ConstantJavaSyntax.RETURN_NEWLINE;
import static org.suych.fm.constant.ConstantJavaSyntax.RIGHT_ANGLE_BRACKETS;
import static org.suych.fm.constant.ConstantJavaSyntax.RIGHT_BRACKET;
import static org.suych.fm.constant.ConstantJavaSyntax.SEMICOLON;
import static org.suych.fm.constant.ConstantJavaSyntax.SPACE;
import static org.suych.fm.constant.ConstantJavaSyntax.TAB;
import static org.suych.fm.constant.ConstantJavaSyntax.TRUE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.suych.fm.base.BaseInfo;
import org.suych.fm.constant.ConstantClassName;
import org.suych.fm.constant.ConstantMethodAccessModifier;
import org.suych.fm.constant.ConstantMethodName;
import org.suych.fm.constant.ConstantParameterName;
import org.suych.fm.constant.ConstantStrategyComponentName;
import org.suych.fm.util.generate.model.java.AnnotationStructure;
import org.suych.fm.util.generate.model.java.MethodStructure;
import org.suych.fm.util.generate.model.java.ParamterStructure;
import org.suych.fm.web.service.strategy.serviceimpl.IServiceImplMethod;

@Component(ConstantStrategyComponentName.SERVICE_IMPL_LIST)
public class ServiceImplMethodList implements IServiceImplMethod {

	@Override
	public MethodStructure assemble() {
		MethodStructure result = new MethodStructure();
		// 注解
		List<AnnotationStructure> annotation = new ArrayList<AnnotationStructure>();
		AnnotationStructure transactional = new AnnotationStructure();
		transactional.setName(ANNOTATIONS_TRANSACTIONAL);
		Map<String, String> attribute = new HashMap<String, String>();
		attribute.put(ConstantParameterName.READONLY, TRUE);
		transactional.setAttribute(attribute);
		AnnotationStructure override = new AnnotationStructure();
		override.setName(ANNOTATIONS_OVERRIDE);
		annotation.add(transactional);
		annotation.add(override);
		// 返回值
		String returnValue = ConstantClassName.PAGE_INFO + LEFT_ANGLE_BRACKETS + BaseInfo.getDomainClassName()
				+ RIGHT_ANGLE_BRACKETS;
		// 方法名
		String methodName = ConstantMethodName.LIST;
		// 参数
		List<ParamterStructure> parameter = new ArrayList<ParamterStructure>();
		ParamterStructure p1 = new ParamterStructure();
		p1.setType(INTEGER);
		p1.setName(ConstantParameterName.PAGE_NUM);
		ParamterStructure p2 = new ParamterStructure();
		p2.setType(INTEGER);
		p2.setName(ConstantParameterName.PAGE_SIZE);
		parameter.add(p1);
		parameter.add(p2);
		// 方法体
		StringBuilder methodBody = new StringBuilder();
		// 第一句
		methodBody.append(TAB + TAB + ConstantClassName.PAGE_HELPER + POINT + ConstantMethodName.START_PAGE
				+ LEFT_BRACKET + ConstantParameterName.PAGE_NUM + COMMA + SPACE + ConstantParameterName.PAGE_SIZE
				+ RIGHT_BRACKET + SEMICOLON + RETURN_NEWLINE);
		// 第二句
		methodBody.append(TAB + TAB + LIST + LEFT_ANGLE_BRACKETS + BaseInfo.getDomainClassName() + RIGHT_ANGLE_BRACKETS
				+ SPACE + ConstantParameterName.LIST + SPACE + EQUAL_SIGN + SPACE
				+ BaseInfo.getMapperInterfaceFieldName() + POINT + ConstantMethodName.LIST + LEFT_BRACKET
				+ RIGHT_BRACKET + SEMICOLON + RETURN_NEWLINE);
		// 第三句
		methodBody.append(TAB + TAB + RETURN + SPACE + NEW + SPACE + ConstantClassName.PAGE_INFO + LEFT_ANGLE_BRACKETS
				+ BaseInfo.getDomainClassName() + RIGHT_ANGLE_BRACKETS + LEFT_BRACKET + ConstantParameterName.LIST
				+ RIGHT_BRACKET + SEMICOLON + RETURN_NEWLINE);

		result.setAnnotation(annotation);
		result.setAccessModifier(ConstantMethodAccessModifier.PUBLIC);
		result.setReturnValue(returnValue);
		result.setName(methodName);
		result.setParameter(parameter);
		result.setMethodBody(methodBody.toString());
		return result;
	}

}
