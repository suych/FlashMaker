package org.suych.fm.web.service;

public interface IMapperService {

	/**
	 * 生成Mapper.java入口
	 * 
	 * @param tableName 数据表名
	 * @param localPackage 本地包名
	 * @param doClassName DO.java文件类名
	 */
	void generate(String tableName, String localPackage, String doClassName);

}
