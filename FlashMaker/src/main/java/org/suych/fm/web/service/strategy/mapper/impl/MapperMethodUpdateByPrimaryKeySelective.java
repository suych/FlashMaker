package org.suych.fm.web.service.strategy.mapper.impl;

import static org.suych.fm.constant.ConstantJavaSyntax.VOID;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.suych.fm.base.BaseInfo;
import org.suych.fm.constant.ConstantMethodName;
import org.suych.fm.constant.ConstantStrategyComponentName;
import org.suych.fm.tool.FileNameTool;
import org.suych.fm.util.generate.model.java.MethodStructure;
import org.suych.fm.util.generate.model.java.ParamterStructure;
import org.suych.fm.web.service.strategy.mapper.IMapperMethod;

@Component(ConstantStrategyComponentName.MAPPER_UPDATE_BY_PRIMARYKEY_SELECTIVE)
public class MapperMethodUpdateByPrimaryKeySelective implements IMapperMethod {

	@Override
	public MethodStructure assemble() {
		MethodStructure result = new MethodStructure();
		String domainClassName = BaseInfo.getDomainClassName();

		List<ParamterStructure> parameter = new ArrayList<ParamterStructure>();
		ParamterStructure p1 = new ParamterStructure();
		p1.setType(domainClassName);
		p1.setName(FileNameTool.firstLetterToLowerCase(domainClassName));
		parameter.add(p1);

		result.setReturnValue(VOID);
		result.setName(ConstantMethodName.UPDATE_BY_PRIMARYKEY_SELECTIVE);
		result.setParameter(parameter);
		return result;
	}

}
