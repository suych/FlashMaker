package org.suych.fm.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.suych.fm.base.BaseInfo;
import org.suych.fm.exception.TableNameEmptyException;
import org.suych.fm.util.StringUtil;
import org.suych.fm.web.model.model.TableInfoModel;
import org.suych.fm.web.service.IControllerService;
import org.suych.fm.web.service.IDomainObjectClassService;
import org.suych.fm.web.service.IMapperInterfaceService;
import org.suych.fm.web.service.IMapperXmlService;
import org.suych.fm.web.service.IServiceImplService;
import org.suych.fm.web.service.IServiceInterfaceService;
import org.suych.fm.web.service.ITableInfoService;

@RestController
@RequestMapping(value = "/home")
public class GenerateController {

	@Autowired
	private ITableInfoService tableInfoService;

	@Autowired
	private IDomainObjectClassService domainObjectClassService;

	@Autowired
	private IMapperInterfaceService mapperInterfaceService;

	@Autowired
	private IMapperXmlService mapperXmlService;

	@Autowired
	private IServiceInterfaceService serviceInterfaceService;

	@Autowired
	private IServiceImplService serviceImplService;

	@Autowired
	private IControllerService controllerService;

	/**
	 * 入口方法
	 * 
	 * @param tableName 生成DO类对应的数据库表名称，不可为空
	 * @param localPackage 生成DO类所在的包，可为空
	 */
	@RequestMapping(value = "/generate/{tableName}/{localPackage}", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json;charset=UTF-8")
	public void generate(@PathVariable("tableName") String tableName,
			@PathVariable("localPackage") String localPackage) {
		if ("".equals(StringUtil.null2Empty(tableName))) {
			throw new TableNameEmptyException();
		}
		localPackage = StringUtil.null2Empty(localPackage);
		// 1.加载表信息
		TableInfoModel tableInfo = tableInfoService.getTableInfo(tableName);
		// 2.初始化基础信息
		BaseInfo.init(localPackage, tableInfo);
		// 3.生成DO.java
		domainObjectClassService.generate();
		// 4.生成Mapper.java
		mapperInterfaceService.generate();
		// 5.生成Mapper.xml
		mapperXmlService.generate();
		// 6.生成Service.java接口
		serviceInterfaceService.generate();
		// 7.生成ServiceImpl.java
		serviceImplService.generate();
		// 8.生成Controller.java
		controllerService.generate();
		System.out.println("-------mission completed-------");
	}

}
