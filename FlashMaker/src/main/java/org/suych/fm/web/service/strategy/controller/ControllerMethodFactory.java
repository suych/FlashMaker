package org.suych.fm.web.service.strategy.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.suych.fm.util.generate.model.java.MethodStructure;

/**
 * Controller类方法策略工厂
 */
@Component
public class ControllerMethodFactory {

	@Autowired
	Map<String, IControllerMethod> controllerMethodStrategy = new HashMap<String, IControllerMethod>();

	/**
	 * 组装方法
	 * 
	 * @param methodName
	 * @return
	 */
	public MethodStructure assemble(String methodName) {
		IControllerMethod method = controllerMethodStrategy.get(methodName);
		return method.assemble();
	}

}
