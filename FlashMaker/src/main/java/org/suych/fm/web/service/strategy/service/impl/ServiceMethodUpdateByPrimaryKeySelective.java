package org.suych.fm.web.service.strategy.service.impl;

import static org.suych.fm.constant.ConstantJavaSyntax.VOID;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.suych.fm.base.BaseInfo;
import org.suych.fm.constant.ConstantMethodName;
import org.suych.fm.constant.ConstantStrategyComponentName;
import org.suych.fm.tool.FileNameTool;
import org.suych.fm.util.generate.model.java.MethodStructure;
import org.suych.fm.web.service.strategy.service.IServiceMethod;

@Component(ConstantStrategyComponentName.SERVICE_UPDATE_BY_PRIMARYKEY_SELECTIVE)
public class ServiceMethodUpdateByPrimaryKeySelective implements IServiceMethod {

	@Override
	public MethodStructure assemble() {
		MethodStructure result = new MethodStructure();
		String domainClassName = BaseInfo.getDomainClassName();
		Map<String, String> parameter = new HashMap<String, String>();
		parameter.put(domainClassName, FileNameTool.firstLetterToLowerCase(domainClassName));
		result.setReturnValue(VOID);
		result.setName(ConstantMethodName.UPDATE_BY_PRIMARYKEY_SELECTIVE);
		result.setParameter(parameter);
		return result;
	}

}
