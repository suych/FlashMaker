package org.suych.fm.web.service;

import org.suych.fm.util.generate.model.ClassStructure;

public interface IDomainObjectService {

	/**
	 * 生成DO.java入口
	 * 
	 * @param tableName 数据表名
	 * @param localPackage 本地包名
	 * @return
	 */
	ClassStructure generate(String tableName, String localPackage);
}
