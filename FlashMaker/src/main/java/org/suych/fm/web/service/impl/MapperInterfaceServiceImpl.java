package org.suych.fm.web.service.impl;

import static org.suych.fm.constant.ConstantJavaSyntax.POINT;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.suych.fm.base.BaseInfo;
import org.suych.fm.constant.ConstantImportPackage;
import org.suych.fm.constant.ConstantInterfaceAccessModifier;
import org.suych.fm.constant.ConstantStrategyComponentName;
import org.suych.fm.util.generate.GenerateInterfaceUtil;
import org.suych.fm.util.generate.model.java.InterfaceStructure;
import org.suych.fm.util.generate.model.java.MethodStructure;
import org.suych.fm.web.service.IMapperInterfaceService;
import org.suych.fm.web.service.strategy.mapper.MapperMethodFactory;

@Service
public class MapperInterfaceServiceImpl implements IMapperInterfaceService {

	@Autowired
	MapperMethodFactory factory;

	@Override
	public void generate() {
		// 1.组装方法结构
		List<MethodStructure> method = assembleMethodStructure();
		// 2.组装Mapper接口结构
		InterfaceStructure mapperInterface = assembleMapperInterface(method);
		// 3.按规范输出至文件
		GenerateInterfaceUtil.generate(mapperInterface);
	}

	private List<MethodStructure> assembleMethodStructure() {
		List<MethodStructure> result = new ArrayList<MethodStructure>();
		// 1.List<DO类名> list()
		result.add(factory.assemble(ConstantStrategyComponentName.MAPPER_LIST));
		// 2.DO类名 getByPrimaryKey(String id);
		result.add(factory.assemble(ConstantStrategyComponentName.MAPPER_GET_BY_PRIMARYKEY));
		// 3.void save(DO类名 DO类名首字母小写);
		result.add(factory.assemble(ConstantStrategyComponentName.MAPPER_SAVE));
		// 4.void saveSelective(DO类名 DO类名首字母小写);
		result.add(factory.assemble(ConstantStrategyComponentName.MAPPER_SAVE_SELECTIVE));
		// 5.void updateByPrimaryKeySelective(DO类名 DO类名首字母小写);
		result.add(factory.assemble(ConstantStrategyComponentName.MAPPER_UPDATE_BY_PRIMARYKEY_SELECTIVE));
		// 6.void removeByPrimaryKeys(List<String> ids);
		result.add(factory.assemble(ConstantStrategyComponentName.MAPPER_REMOVE_BY_PRIMARYKEYS));
		return result;
	}

	private InterfaceStructure assembleMapperInterface(List<MethodStructure> method) {
		InterfaceStructure result = new InterfaceStructure();
		String localPackage = BaseInfo.getLocalPackage();
		Set<String> importPackage = new HashSet<String>();
		importPackage.add(ConstantImportPackage.LIST);
		String domainPackage = localPackage + POINT + BaseInfo.getDomainClassName(); // DO文件包名
		importPackage.add(domainPackage);
		String interfaceName = BaseInfo.getMapperInterfaceName();

		result.setLocalPackage(localPackage);
		result.setImportPackage(importPackage);
		result.setAcessModifier(ConstantInterfaceAccessModifier.PUBLIC);
		result.setName(interfaceName);
		result.setMethod(method);
		return result;
	}

}
