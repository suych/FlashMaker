package org.suych.fm.base;

import org.suych.fm.constant.ConstantSuffix;
import org.suych.fm.tool.FileNameTool;
import org.suych.fm.web.model.model.TableInfoModel;

public class BaseInfo {

	// 本地包名
	private static String localPackage = "";

	// DO类名称
	private static String domainClassName = "";
	// Mapper接口名称
	private static String mapperInterfaceName = "";
	// Mapper.xml名称
	private static String mapperXmlName = "";

	// 数据库表信息
	private static TableInfoModel tableInfo;

	/**
	 * 初始化基础信息
	 * 
	 * @param localPackage 本地包名
	 * @param tableInfo 表信息
	 */
	public static void init(String localPackage, TableInfoModel tableInfo) {
		// 1.初始化本地包名
		BaseInfo.localPackage = localPackage;
		// 2.初始化文件名信息
		initFileName(tableInfo.getTableName());
		// 3.初始化数据库表信息
		BaseInfo.tableInfo = tableInfo;
	}

	/**
	 * 获取本地包名
	 * 
	 * @return
	 */
	public static String getLocalPackage() {
		return localPackage;
	}

	/**
	 * 获取DO类名称
	 * 
	 * @return
	 */
	public static String getDomainClassName() {
		return domainClassName;
	}

	/**
	 * 获取Mapper接口名称
	 * 
	 * @return
	 */
	public static String getMapperInterfaceName() {
		return mapperInterfaceName;
	}

	/**
	 * 获取MapperXml名称
	 * 
	 * @return
	 */
	public static String getMapperXmlName() {
		return mapperXmlName;
	}

	/**
	 * 获取数据库表信息
	 * 
	 * @return
	 */
	public static TableInfoModel getTableInfo() {
		return tableInfo;
	}

	private static void initFileName(String tableName) {
		domainClassName = FileNameTool.assembleClassOrInterfaceName(tableName, ConstantSuffix.DOMAIN_OBJECT);
		mapperInterfaceName = FileNameTool.assembleClassOrInterfaceName(tableName, ConstantSuffix.MAPPER);
		mapperXmlName = FileNameTool.assembleClassOrInterfaceName(tableName, ConstantSuffix.MAPPER);
	}

}
