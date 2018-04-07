package org.suych.fm.web.service.strategy.serviceimpl.impl;

import static org.suych.fm.constant.ConstantJavaSyntax.ANNOTATIONS_OVERRIDE;
import static org.suych.fm.constant.ConstantJavaSyntax.ANNOTATIONS_TRANSACTIONAL;
import static org.suych.fm.constant.ConstantJavaSyntax.LEFT_BRACKET;
import static org.suych.fm.constant.ConstantJavaSyntax.POINT;
import static org.suych.fm.constant.ConstantJavaSyntax.RETURN_NEWLINE;
import static org.suych.fm.constant.ConstantJavaSyntax.RIGHT_BRACKET;
import static org.suych.fm.constant.ConstantJavaSyntax.SEMICOLON;
import static org.suych.fm.constant.ConstantJavaSyntax.TAB;
import static org.suych.fm.constant.ConstantJavaSyntax.VOID;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.suych.fm.base.BaseInfo;
import org.suych.fm.constant.ConstantMethodAccessModifier;
import org.suych.fm.constant.ConstantMethodName;
import org.suych.fm.constant.ConstantStrategyComponentName;
import org.suych.fm.util.generate.model.java.AnnotationStructure;
import org.suych.fm.util.generate.model.java.MethodStructure;
import org.suych.fm.util.generate.model.java.ParamterStructure;
import org.suych.fm.web.service.strategy.serviceimpl.IServiceImplMethod;

@Component(ConstantStrategyComponentName.SERVICE_IMPL_SAVE_SELECTIVE)
public class ServiceImplMethodSaveSelective implements IServiceImplMethod {

	@Override
	public MethodStructure assemble() {
		MethodStructure result = new MethodStructure();
		String domainClassName = BaseInfo.getDomainClassName();

		List<AnnotationStructure> annotation = new ArrayList<AnnotationStructure>();
		AnnotationStructure transactional = new AnnotationStructure();
		transactional.setName(ANNOTATIONS_TRANSACTIONAL);
		AnnotationStructure override = new AnnotationStructure();
		override.setName(ANNOTATIONS_OVERRIDE);
		annotation.add(transactional);
		annotation.add(override);

		List<ParamterStructure> parameter = new ArrayList<ParamterStructure>();
		ParamterStructure p1 = new ParamterStructure();
		p1.setType(domainClassName);
		p1.setName(BaseInfo.getDomainClassFieldName());
		parameter.add(p1);

		String methodBody = TAB + TAB + BaseInfo.getMapperInterfaceFieldName() + POINT
				+ ConstantMethodName.SAVE_SELECTIVE + LEFT_BRACKET + BaseInfo.getDomainClassFieldName() + RIGHT_BRACKET
				+ SEMICOLON + RETURN_NEWLINE;

		result.setAnnotation(annotation);
		result.setAccessModifier(ConstantMethodAccessModifier.PUBLIC);
		result.setReturnValue(VOID);
		result.setName(ConstantMethodName.SAVE_SELECTIVE);
		result.setParameter(parameter);
		result.setMethodBody(methodBody);
		return result;
	}

}
