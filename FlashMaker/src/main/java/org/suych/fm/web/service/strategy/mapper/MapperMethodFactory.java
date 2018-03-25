package org.suych.fm.web.service.strategy.mapper;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.suych.fm.util.generate.model.java.MethodStructure;

/**
 * Mapper方法策略工厂
 */
@Component
public class MapperMethodFactory {

	@Autowired
	Map<String, IMapperMethod> mapperMethodStrategy = new HashMap<String, IMapperMethod>();

	/**
	 * 组装方法
	 * 
	 * @param methodName
	 * @return
	 */
	public MethodStructure assemble(String methodName) {
		IMapperMethod method = mapperMethodStrategy.get(methodName);
		return method.assemble();
	}

}
