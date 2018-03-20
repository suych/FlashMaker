package org.suych.fm.web.mapper;

import java.util.List;

import org.suych.fm.web.model.domain.FieldInfoDO;
import org.suych.fm.web.model.domain.TableBaseInfoDO;

public interface TableInfoMapper {

	/**
	 * 查询表基本信息
	 * 
	 * @param tableName 数据库表名
	 * @return
	 */
	TableBaseInfoDO getTableBaseInfo(String tableName);

	/**
	 * 查询字段信息
	 * 
	 * @param tableName 数据库表名
	 * @return
	 */
	List<FieldInfoDO> listFieldInfo(String tableName);

	/**
	 * 查询主键名称
	 * 
	 * @param tableName 数据库表名
	 * @return
	 */
	String getPrimaryKey(String tableName);

}
