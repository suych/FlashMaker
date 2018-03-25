package org.suych.fm.web.service.strategy.serviceimpl.impl;

import static org.suych.fm.constant.ConstantJavaSyntax.ANNOTATIONS_ATTRIBUTE_READONLY;
import static org.suych.fm.constant.ConstantJavaSyntax.ANNOTATIONS_OVERRIDE;
import static org.suych.fm.constant.ConstantJavaSyntax.ANNOTATIONS_TRANSACTIONAL;
import static org.suych.fm.constant.ConstantJavaSyntax.LEFT_ANGLE_BRACKETS;
import static org.suych.fm.constant.ConstantJavaSyntax.LEFT_BRACKET;
import static org.suych.fm.constant.ConstantJavaSyntax.LIST;
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
import org.suych.fm.constant.ConstantMethodAccessModifier;
import org.suych.fm.constant.ConstantMethodName;
import org.suych.fm.constant.ConstantStrategyComponentName;
import org.suych.fm.tool.FileNameTool;
import org.suych.fm.util.generate.model.java.AnnotationStructure;
import org.suych.fm.util.generate.model.java.MethodStructure;
import org.suych.fm.web.service.strategy.serviceimpl.IServiceImplMethod;

@Component(ConstantStrategyComponentName.SERVICE_IMPL_LIST)
public class ServiceImplMethodList implements IServiceImplMethod {

	@Override
	public MethodStructure assemble() {
		MethodStructure result = new MethodStructure();

		List<AnnotationStructure> annotation = new ArrayList<AnnotationStructure>();
		AnnotationStructure transactional = new AnnotationStructure();
		transactional.setName(ANNOTATIONS_TRANSACTIONAL);
		Map<String, String> attribute = new HashMap<String, String>();
		attribute.put(ANNOTATIONS_ATTRIBUTE_READONLY, TRUE);
		transactional.setAttribute(attribute);
		AnnotationStructure override = new AnnotationStructure();
		override.setName(ANNOTATIONS_OVERRIDE);
		annotation.add(transactional);
		annotation.add(override);

		String returnValue = LIST + LEFT_ANGLE_BRACKETS + BaseInfo.getDomainClassName() + RIGHT_ANGLE_BRACKETS;
		String methodName = ConstantMethodName.LIST;

		String mapperInterfaceName = BaseInfo.getMapperInterfaceName();
		String methodBody = TAB + TAB + RETURN + SPACE + FileNameTool.firstLetterToLowerCase(mapperInterfaceName)
				+ POINT + ConstantMethodName.LIST + LEFT_BRACKET + RIGHT_BRACKET + SEMICOLON + RETURN_NEWLINE;

		result.setAnnotation(annotation);
		result.setAccessModifier(ConstantMethodAccessModifier.PUBLIC);
		result.setReturnValue(returnValue);
		result.setName(methodName);
		result.setMethodBody(methodBody);
		return result;
	}

}
