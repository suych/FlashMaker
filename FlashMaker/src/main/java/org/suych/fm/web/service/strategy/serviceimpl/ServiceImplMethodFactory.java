package org.suych.fm.web.service.strategy.serviceimpl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.suych.fm.util.generate.model.java.MethodStructure;

/**
 * Serivce实现类方法策略工厂
 */
@Component
public class ServiceImplMethodFactory {

	@Autowired
	Map<String, IServiceImplMethod> serviceImplMethodStrategy = new HashMap<String, IServiceImplMethod>();

	/**
	 * 组装方法
	 * 
	 * @param methodName
	 * @return
	 */
	public MethodStructure assemble(String methodName) {
		IServiceImplMethod method = serviceImplMethodStrategy.get(methodName);
		return method.assemble();
	}

}
