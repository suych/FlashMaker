package org.suych.fm.web.service.strategy.service.impl;

import static org.suych.fm.constant.ConstantJavaSyntax.STRING;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.suych.fm.base.BaseInfo;
import org.suych.fm.constant.ConstantMethodName;
import org.suych.fm.constant.ConstantParameterName;
import org.suych.fm.constant.ConstantStrategyComponentName;
import org.suych.fm.util.generate.model.java.MethodStructure;
import org.suych.fm.util.generate.model.java.ParamterStructure;
import org.suych.fm.web.service.strategy.service.IServiceMethod;

@Component(ConstantStrategyComponentName.SERVICE_GET_BY_PRIMARYKEY)
public class ServiceMethodGetByPrimaryKey implements IServiceMethod {

	@Override
	public MethodStructure assemble() {
		MethodStructure result = new MethodStructure();

		List<ParamterStructure> parameter = new ArrayList<ParamterStructure>();
		ParamterStructure p1 = new ParamterStructure();
		p1.setType(STRING);
		p1.setName(ConstantParameterName.PRIMARY_KEY);
		parameter.add(p1);

		result.setReturnValue(BaseInfo.getDomainClassName());
		result.setName(ConstantMethodName.GET_BY_PRIMARYKEY);
		result.setParameter(parameter);
		return result;
	}

}
