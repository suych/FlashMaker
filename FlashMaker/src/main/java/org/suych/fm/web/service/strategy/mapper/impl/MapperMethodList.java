package org.suych.fm.web.service.strategy.mapper.impl;

import static org.suych.fm.constant.ConstantJavaSyntax.LEFT_ANGLE_BRACKETS;
import static org.suych.fm.constant.ConstantJavaSyntax.LIST;
import static org.suych.fm.constant.ConstantJavaSyntax.RIGHT_ANGLE_BRACKETS;

import org.springframework.stereotype.Component;
import org.suych.fm.base.BaseInfo;
import org.suych.fm.constant.ConstantMethodName;
import org.suych.fm.constant.ConstantStrategyComponentName;
import org.suych.fm.util.generate.model.java.MethodStructure;
import org.suych.fm.web.service.strategy.mapper.IMapperMethod;

@Component(ConstantStrategyComponentName.MAPPER_LIST)
public class MapperMethodList implements IMapperMethod {

	@Override
	public MethodStructure assemble() {
		MethodStructure result = new MethodStructure();
		String returnValue = LIST + LEFT_ANGLE_BRACKETS + BaseInfo.getDomainClassName() + RIGHT_ANGLE_BRACKETS;
		result.setReturnValue(returnValue);
		result.setName(ConstantMethodName.LIST);
		return result;
	}

}
