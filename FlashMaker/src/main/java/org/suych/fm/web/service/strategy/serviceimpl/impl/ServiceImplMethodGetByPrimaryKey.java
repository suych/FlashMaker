package org.suych.fm.web.service.strategy.serviceimpl.impl;

import static org.suych.fm.constant.ConstantJavaSyntax.ANNOTATIONS_ATTRIBUTE_READONLY;
import static org.suych.fm.constant.ConstantJavaSyntax.ANNOTATIONS_OVERRIDE;
import static org.suych.fm.constant.ConstantJavaSyntax.ANNOTATIONS_TRANSACTIONAL;
import static org.suych.fm.constant.ConstantJavaSyntax.LEFT_BRACKET;
import static org.suych.fm.constant.ConstantJavaSyntax.POINT;
import static org.suych.fm.constant.ConstantJavaSyntax.RETURN;
import static org.suych.fm.constant.ConstantJavaSyntax.RETURN_NEWLINE;
import static org.suych.fm.constant.ConstantJavaSyntax.RIGHT_BRACKET;
import static org.suych.fm.constant.ConstantJavaSyntax.SEMICOLON;
import static org.suych.fm.constant.ConstantJavaSyntax.SPACE;
import static org.suych.fm.constant.ConstantJavaSyntax.STRING;
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
import org.suych.fm.constant.ConstantParameterName;
import org.suych.fm.constant.ConstantStrategyComponentName;
import org.suych.fm.tool.FileNameTool;
import org.suych.fm.util.generate.model.java.AnnotationStructure;
import org.suych.fm.util.generate.model.java.MethodStructure;
import org.suych.fm.web.service.strategy.serviceimpl.IServiceImplMethod;

@Component(ConstantStrategyComponentName.SERVICE_IMPL_GET_BY_PRIMARYKEY)
public class ServiceImplMethodGetByPrimaryKey implements IServiceImplMethod {

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

		Map<String, String> parameter = new HashMap<String, String>();
		parameter.put(STRING, ConstantParameterName.PRIMARY_KEY);

		String mapperInterfaceName = BaseInfo.getMapperInterfaceName();
		String methodBody = TAB + TAB + RETURN + SPACE + FileNameTool.firstLetterToLowerCase(mapperInterfaceName)
				+ POINT + ConstantMethodName.GET_BY_PRIMARYKEY + LEFT_BRACKET + ConstantParameterName.PRIMARY_KEY + RIGHT_BRACKET
				+ SEMICOLON + RETURN_NEWLINE;

		result.setAnnotation(annotation);
		result.setAccessModifier(ConstantMethodAccessModifier.PUBLIC);
		result.setReturnValue(BaseInfo.getDomainClassName());
		result.setName(ConstantMethodName.GET_BY_PRIMARYKEY);
		result.setParameter(parameter);
		result.setMethodBody(methodBody);
		return result;
	}

}
