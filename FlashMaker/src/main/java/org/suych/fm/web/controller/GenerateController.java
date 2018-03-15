package org.suych.fm.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.suych.fm.exception.TableNameEmptyException;
import org.suych.fm.util.StringUtil;
import org.suych.fm.util.generate.model.ClassStructure;
import org.suych.fm.web.service.IDomainObjectService;
import org.suych.fm.web.service.IMapperService;

@RestController
@RequestMapping(value = "/home")
public class GenerateController {

	@Autowired
	private IDomainObjectService domainObjectService;

	@Autowired
	private IMapperService mapperService;

	// DO类结构，在生成其他类或文件时使用
	private ClassStructure doClassStructure;

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
		// 1.生成DO.java
		doClassStructure = domainObjectService.generate(tableName, localPackage);
		// 2.生成Mapper.java
		String doClassName = doClassStructure.getName();
		mapperService.generate(tableName, localPackage, doClassName);
		System.out.println("-------mission completed-------");
	}

}
