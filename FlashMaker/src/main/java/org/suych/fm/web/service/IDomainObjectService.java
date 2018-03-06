package org.suych.fm.web.service;

import java.util.List;
import java.util.Set;

import org.suych.fm.web.model.bo.FieldInfoBO;
import org.suych.fm.web.model.domain.TableInfoDO;
import org.suych.fm.web.model.dto.DomainObjectDTO;
import org.suych.fm.web.model.dto.ResultDoubleDTO;

public interface IDomainObjectService {

	/**
	 * 获得引入包名和数据库表信息
	 * 
	 * @param tableName 数据库表名
	 * @return
	 */
	ResultDoubleDTO<Set<String>, List<FieldInfoBO>> getImportPackageAndlistTableInfo(String tableName);

	/**
	 * 获得表信息
	 * 
	 * @param tableName
	 * @return
	 */
	TableInfoDO getTableInfo(String tableName);

	/**
	 * 组装DO的DTO
	 * 
	 * @param localPackage 本地包名
	 * @param importPackage 引入包名
	 * @param tableName 表名
	 * @param fieldInfo 数据库表信息，包含字段名/字段类型/字段注释
	 * @return
	 */
	DomainObjectDTO assembleDO(String localPackage, Set<String> importPackage, TableInfoDO tableInfo,
			String tableName, List<FieldInfoBO> fieldInfo);

	/**
	 * 生成 Java文件
	 * 
	 * @param doDTO
	 */
	void generateJavaFile(DomainObjectDTO doDTO);

}
