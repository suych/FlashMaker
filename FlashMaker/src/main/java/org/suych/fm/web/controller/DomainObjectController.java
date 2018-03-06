package org.suych.fm.web.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.suych.fm.exception.TableNameEmptyException;
import org.suych.fm.web.model.bo.FieldInfoBO;
import org.suych.fm.web.model.domain.TableInfoDO;
import org.suych.fm.web.model.dto.DomainObjectDTO;
import org.suych.fm.web.model.dto.ResultDoubleDTO;
import org.suych.fm.web.service.IDomainObjectService;

@RestController
@RequestMapping(value = "/domainobject")
public class DomainObjectController {

	@Autowired
	private IDomainObjectService domainObjectService;

	/**
	 * @param tableName 生成DO类对应的数据库表名称，不可为空
	 * @param localPackage 生成DO类所在的包，可为空
	 */
	@RequestMapping(value = "/generate/{tableName}/{localPackage}", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json;charset=UTF-8")
	public void generate(@PathVariable("tableName") String tableName,
			@PathVariable("localPackage") String localPackage) {
		if ("".equals(tableName)) {
			throw new TableNameEmptyException();
		}
		// 1.获取引入的包名和字段的信息
		ResultDoubleDTO<Set<String>, List<FieldInfoBO>> importPackageAndTableInfo = domainObjectService
				.getImportPackageAndlistTableInfo(tableName);
		// 2.获得表信息
		TableInfoDO tableInfo = domainObjectService.getTableInfo(tableName);
		// 3.整合获得的信息
		DomainObjectDTO domainObjectDTO = domainObjectService.assembleDO(localPackage, importPackageAndTableInfo.first,
				tableInfo, tableName, importPackageAndTableInfo.second);
		// 4.按规范输出至文件
		domainObjectService.generateJavaFile(domainObjectDTO);
		System.out.println("-------mission completed-------");
	}
}
