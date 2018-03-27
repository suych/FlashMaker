package org.suych.fm.web.service.strategy.service.impl;

import static org.suych.fm.constant.ConstantJavaSyntax.INTEGER;
import static org.suych.fm.constant.ConstantJavaSyntax.LEFT_ANGLE_BRACKETS;
import static org.suych.fm.constant.ConstantJavaSyntax.RIGHT_ANGLE_BRACKETS;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.suych.fm.base.BaseInfo;
import org.suych.fm.constant.ConstantClassName;
import org.suych.fm.constant.ConstantMethodName;
import org.suych.fm.constant.ConstantParameterName;
import org.suych.fm.constant.ConstantStrategyComponentName;
import org.suych.fm.util.generate.model.java.MethodStructure;
import org.suych.fm.util.generate.model.java.ParamterStructure;
import org.suych.fm.web.service.strategy.service.IServiceMethod;

@Component(ConstantStrategyComponentName.SERVICE_LIST)
public class ServiceMethodList implements IServiceMethod {

	@Override
	public MethodStructure assemble() {
		MethodStructure result = new MethodStructure();
		String returnValue = ConstantClassName.PAGE_INFO + LEFT_ANGLE_BRACKETS + BaseInfo.getDomainClassName()
				+ RIGHT_ANGLE_BRACKETS;

		List<ParamterStructure> parameter = new ArrayList<ParamterStructure>();
		ParamterStructure p1 = new ParamterStructure();
		p1.setType(INTEGER);
		p1.setName(ConstantParameterName.PAGE_NUM);
		ParamterStructure p2 = new ParamterStructure();
		p2.setType(INTEGER);
		p2.setName(ConstantParameterName.PAGE_SIZE);
		parameter.add(p1);
		parameter.add(p2);

		result.setReturnValue(returnValue);
		result.setName(ConstantMethodName.LIST);
		result.setParameter(parameter);
		return result;
	}

}
