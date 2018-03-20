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
import org.suych.fm.web.service.IDomainObjectService;
import org.suych.fm.web.service.IMapperService;
import org.suych.fm.web.service.IMapperXmlService;
import org.suych.fm.web.service.ITableInfoService;

@RestController
@RequestMapping(value = "/home")
public class GenerateController {

	@Autowired
	private ITableInfoService tableInfoService;

	@Autowired
	private IDomainObjectService domainObjectService;

	@Autowired
	private IMapperService mapperService;

	@Autowired
	private IMapperXmlService mapperXmlService;

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
		domainObjectService.generate();
		// 4.生成Mapper.java
		mapperService.generate();
		// 5.生成Mapper.xml
		mapperXmlService.generate();
		System.out.println("-------mission completed-------");
	}

}
