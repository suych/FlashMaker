package org.suych.fm.web.service.strategy.mapper.impl;

import static org.suych.fm.constant.ConstantJavaSyntax.LEFT_ANGLE_BRACKETS;
import static org.suych.fm.constant.ConstantJavaSyntax.LIST;
import static org.suych.fm.constant.ConstantJavaSyntax.RIGHT_ANGLE_BRACKETS;
import static org.suych.fm.constant.ConstantJavaSyntax.STRING;
import static org.suych.fm.constant.ConstantJavaSyntax.VOID;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.suych.fm.constant.ConstantMethodName;
import org.suych.fm.constant.ConstantParameterName;
import org.suych.fm.constant.ConstantStrategyComponentName;
import org.suych.fm.util.generate.model.java.MethodStructure;
import org.suych.fm.web.service.strategy.mapper.IMapperMethod;

@Component(ConstantStrategyComponentName.MAPPER_REMOVE_BY_PRIMARYKEYS)
public class MapperMethodRemoveByPrimaryKeys implements IMapperMethod {

	@Override
	public MethodStructure assemble() {
		MethodStructure result = new MethodStructure();
		Map<String, String> parameter = new HashMap<String, String>();
		String type = LIST + LEFT_ANGLE_BRACKETS + STRING + RIGHT_ANGLE_BRACKETS;
		parameter.put(type, ConstantParameterName.PRIMARY_KEY_S);
		result.setReturnValue(VOID);
		result.setName(ConstantMethodName.REMOVE_BY_PRIMARYKEYS);
		result.setParameter(parameter);
		return result;
	}

}
