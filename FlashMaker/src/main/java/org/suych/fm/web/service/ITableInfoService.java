package org.suych.fm.web.service;

import org.suych.fm.web.model.model.TableInfoModel;

public interface ITableInfoService {

	/**
	 * 获取表信息
	 * 
	 * @param tableName 数据库表信息
	 * @return
	 */
	TableInfoModel getTableInfo(String tableName);

}
