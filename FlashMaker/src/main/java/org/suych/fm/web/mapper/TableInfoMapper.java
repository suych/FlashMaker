package org.suych.fm.web.mapper;

import java.util.List;

import org.suych.fm.web.model.domain.FieldInfoDO;
import org.suych.fm.web.model.domain.TableInfoDO;

public interface TableInfoMapper {

	/**
	 * 查询表信息
	 * 
	 * @param tableName 数据表名
	 * @return
	 */
	TableInfoDO getTableInfo(String tableName);

	/**
	 * 查询字段信息
	 * 
	 * @param tableName 数据表名
	 * @return
	 */
	List<FieldInfoDO> listFieldInfo(String tableName);

}
