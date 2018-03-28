package org.suych.fm.base;

import static org.suych.fm.constant.ConstantJavaSyntax.POINT;

import org.suych.fm.constant.ConstantJavaSyntax;
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
	// Service接口名称
	private static String serviceInterfaceName = "";
	// Service实现类名称
	private static String serviceImplName = "";
	// Controller类名称
	private static String controllerName = "";

	// DO类本地包名
	private static String domainClassLocalPackage = "";
	// Mapper接口本地包名
	private static String mapperInterfaceLocalPackage = "";
	// Service接口本地包名
	private static String serviceInterfaceLocalPackage = "";
	// Service实现类本地包名
	private static String serviceImplLocalPackage = "";
	// Controller类本地包名
	private static String controllerLocalPackage = "";

	// DO类引入包路径
	private static String domainClassImportPath = "";
	// Mapper接口引入路径
	private static String mapperInterfaceImportPath = "";
	// Service接口引入路径
	private static String serviceInterfaceImportPath = "";

	// DO类作为字段时名称
	private static String domainClassFieldName = "";
	// Mapper接口作为字段时名称
	private static String mapperInterfaceFieldName = "";
	// Service接口作为字段时名称
	private static String serviceInterfaceFieldName = "";

	// 数据库表信息
	private static TableInfoModel tableInfo;

	private static final String INTERFACE_PREFIX = "I";
	private static final String MODEL = "model";
	private static final String MAPPER = "mapper";
	private static final String SERVICE = "service";
	private static final String CONTROLLER = "controller";
	private static final String IMPL = "impl";

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
		// 3.初始化本地包名信息
		initLocalPackage();
		// 4.初始化引入包路径信息
		initImportPath();
		// 5.初始化接口作为字段使用时的名称
		initInterfaceFieldName();
		// 6.初始化数据库表信息
		BaseInfo.tableInfo = tableInfo;
	}

	/**
	 * 获取本地包名
	 * 
	 * @return
	 */
	@Deprecated
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
	 * 获取Service接口名称
	 * 
	 * @return
	 */
	public static String getServiceInterfaceName() {
		return serviceInterfaceName;
	}

	/**
	 * 获得ServiceImpl实现类名称
	 * 
	 * @return
	 */
	public static String getServiceImplName() {
		return serviceImplName;
	}

	/**
	 * 获得Controller类名称
	 * @return
	 */
	public static String getControllerName() {
		return controllerName;
	}

	/**
	 * 获取数据库表信息
	 * 
	 * @return
	 */
	public static TableInfoModel getTableInfo() {
		return tableInfo;
	}

	/**
	 * 获取DO类本地包名
	 * 
	 * @return
	 */
	public static String getDomainClassLocalPackage() {
		return domainClassLocalPackage;
	}

	/**
	 * 获取Mapper接口本地包名
	 * 
	 * @return
	 */
	public static String getMapperInterfaceLocalPackage() {
		return mapperInterfaceLocalPackage;
	}

	/**
	 * 获取Service接口本地包名
	 * 
	 * @return
	 */
	public static String getServiceInterfaceLocalPackage() {
		return serviceInterfaceLocalPackage;
	}

	/**
	 * 获取Service实现类本地包名
	 * 
	 * @return
	 */
	public static String getServiceImplLocalPackage() {
		return serviceImplLocalPackage;
	}

	/**
	 * 获取Controller类本地包名
	 * 
	 * @return
	 */
	public static String getControllerLocalPackage() {
		return controllerLocalPackage;
	}

	/**
	 * 获取DO类引入包路径
	 * 
	 * @return
	 */
	public static String getDomainClassImportPath() {
		return domainClassImportPath;
	}

	/**
	 * 获取Mapper接口引入路径
	 * 
	 * @return
	 */
	public static String getMapperInterfaceImportPath() {
		return mapperInterfaceImportPath;
	}

	/**
	 * 获取Service接口引入路径
	 * 
	 * @return
	 */
	public static String getServiceInterfaceImportPath() {
		return serviceInterfaceImportPath;
	}

	/**
	 * 获取Mapper接口作为字段时名称
	 * 
	 * @return
	 */
	public static String getMapperInterfaceFieldName() {
		return mapperInterfaceFieldName;
	}

	/**
	 * 获取Service接口作为字段时名称
	 * 
	 * @return
	 */
	public static String getServiceInterfaceFieldName() {
		return serviceInterfaceFieldName;
	}

	/**
	 * 获得DO类作为字段时名称 
	 * 
	 * @return
	 */
	public static String getDomainClassFieldName() {
		return domainClassFieldName;
	}

	private static void initFileName(String tableName) {
		domainClassName = FileNameTool.assembleClassOrInterfaceName(tableName, ConstantSuffix.DOMAIN_OBJECT);
		mapperInterfaceName = FileNameTool.assembleClassOrInterfaceName(tableName, ConstantSuffix.MAPPER);
		mapperXmlName = FileNameTool.assembleClassOrInterfaceName(tableName, ConstantSuffix.MAPPER);
		serviceInterfaceName = INTERFACE_PREFIX
				+ FileNameTool.assembleClassOrInterfaceName(tableName, ConstantSuffix.SERVICE);
		serviceImplName = FileNameTool.assembleClassOrInterfaceName(tableName, ConstantSuffix.SERVICE_IMPL);
		controllerName = FileNameTool.assembleClassOrInterfaceName(tableName, ConstantSuffix.CONTROLLER);
	}

	private static void initLocalPackage() {
		domainClassLocalPackage = localPackage + ConstantJavaSyntax.POINT + MODEL;
		mapperInterfaceLocalPackage = localPackage + ConstantJavaSyntax.POINT + MAPPER;
		serviceInterfaceLocalPackage = localPackage + ConstantJavaSyntax.POINT + SERVICE;
		serviceImplLocalPackage = localPackage + ConstantJavaSyntax.POINT + SERVICE + ConstantJavaSyntax.POINT + IMPL;
		controllerLocalPackage = localPackage + ConstantJavaSyntax.POINT + CONTROLLER;
	}

	private static void initImportPath() {
		domainClassImportPath = BaseInfo.getDomainClassLocalPackage() + POINT + BaseInfo.getDomainClassName();
		mapperInterfaceImportPath = BaseInfo.getMapperInterfaceLocalPackage() + POINT
				+ BaseInfo.getMapperInterfaceName();
		serviceInterfaceImportPath = BaseInfo.getServiceInterfaceLocalPackage() + POINT
				+ BaseInfo.getServiceInterfaceName();
	}

	private static void initInterfaceFieldName() {
		domainClassFieldName = FileNameTool.firstLetterToLowerCase(domainClassName);
		mapperInterfaceFieldName = FileNameTool.firstLetterToLowerCase(mapperInterfaceName);
		String temp = serviceInterfaceName.substring(1, serviceInterfaceName.length());
		serviceInterfaceFieldName = FileNameTool.firstLetterToLowerCase(temp);
	}

}
