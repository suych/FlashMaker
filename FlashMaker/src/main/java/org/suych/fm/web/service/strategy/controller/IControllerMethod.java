package org.suych.fm.web.service.strategy.controller;

import org.suych.fm.util.generate.model.java.MethodStructure;

/**
 * 组装Controller类方法接口
 */
public interface IControllerMethod {

	/**
	 * 组装方法
	 * 
	 * @return
	 */
	MethodStructure assemble();
}
