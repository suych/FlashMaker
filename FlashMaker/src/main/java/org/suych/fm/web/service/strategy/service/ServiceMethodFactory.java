package org.suych.fm.web.service.strategy.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.suych.fm.util.generate.model.java.MethodStructure;

/**
 * Serivce方法策略工厂
 */
@Component
public class ServiceMethodFactory {

	@Autowired
	Map<String, IServiceMethod> serviceMethodStrategy = new HashMap<String, IServiceMethod>();

	/**
	 * 组装方法
	 * 
	 * @param methodName
	 * @return
	 */
	public MethodStructure assemble(String methodName) {
		IServiceMethod method = serviceMethodStrategy.get(methodName);
		return method.assemble();
	}

}
