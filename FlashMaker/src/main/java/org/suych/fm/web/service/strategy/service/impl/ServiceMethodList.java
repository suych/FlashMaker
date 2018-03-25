package org.suych.fm.web.service.strategy.service.impl;

import static org.suych.fm.constant.ConstantJavaSyntax.LEFT_ANGLE_BRACKETS;
import static org.suych.fm.constant.ConstantJavaSyntax.LIST;
import static org.suych.fm.constant.ConstantJavaSyntax.RIGHT_ANGLE_BRACKETS;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.suych.fm.base.BaseInfo;
import org.suych.fm.constant.ConstantMethodName;
import org.suych.fm.constant.ConstantStrategyComponentName;
import org.suych.fm.util.generate.model.java.MethodStructure;
import org.suych.fm.web.service.strategy.service.IServiceMethod;

@Component(ConstantStrategyComponentName.SERVICE_LIST)
public class ServiceMethodList implements IServiceMethod {

	@Override
	public MethodStructure assemble() {
		MethodStructure result = new MethodStructure();
		String domainClassName = BaseInfo.getDomainClassName();
		String returnValue = LIST + LEFT_ANGLE_BRACKETS + domainClassName + RIGHT_ANGLE_BRACKETS;
		Map<String, String> parameter = new HashMap<String, String>();
		result.setReturnValue(returnValue);
		result.setName(ConstantMethodName.LIST);
		result.setParameter(parameter);
		return result;
	}

}
