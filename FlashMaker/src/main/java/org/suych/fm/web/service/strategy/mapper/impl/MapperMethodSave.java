package org.suych.fm.web.service.strategy.mapper.impl;

import static org.suych.fm.constant.ConstantJavaSyntax.VOID;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.suych.fm.base.BaseInfo;
import org.suych.fm.constant.ConstantMethodName;
import org.suych.fm.constant.ConstantStrategyComponentName;
import org.suych.fm.tool.FileNameTool;
import org.suych.fm.util.generate.model.java.MethodStructure;
import org.suych.fm.web.service.strategy.mapper.IMapperMethod;

@Component(ConstantStrategyComponentName.MAPPER_SAVE)
public class MapperMethodSave implements IMapperMethod {

	@Override
	public MethodStructure assemble() {
		MethodStructure result = new MethodStructure();
		String domainClassName = BaseInfo.getDomainClassName();
		Map<String, String> parameter = new HashMap<String, String>();
		parameter.put(domainClassName, FileNameTool.firstLetterToLowerCase(domainClassName));
		result.setReturnValue(VOID);
		result.setName(ConstantMethodName.SAVE);
		result.setParameter(parameter);
		return result;
	}

}
