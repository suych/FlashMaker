package org.suych.fm.web.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.suych.fm.base.BaseInfo;
import org.suych.fm.constant.ConstantImportPackage;
import org.suych.fm.constant.ConstantInterfaceAccessModifier;
import org.suych.fm.constant.ConstantStrategyComponentName;
import org.suych.fm.util.generate.GenerateInterfaceUtil;
import org.suych.fm.util.generate.model.java.ImportPackageStructure;
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
		List<MethodStructure> method = assembleMethod();
		// 2.组装Mapper接口结构
		InterfaceStructure mapperInterface = assembleInterface(method);
		// 3.按规范输出至文件
		GenerateInterfaceUtil.generate(mapperInterface);
	}

	private List<MethodStructure> assembleMethod() {
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

	private InterfaceStructure assembleInterface(List<MethodStructure> method) {
		InterfaceStructure result = new InterfaceStructure();

		ImportPackageStructure importPackage = new ImportPackageStructure();
		Set<String> javaPackage = new LinkedHashSet<String>();
		javaPackage.add(ConstantImportPackage.LIST);
		Set<String> customPackage = new LinkedHashSet<String>();
		customPackage.add(BaseInfo.getDomainClassImportPath());
		importPackage.setJavaPackage(javaPackage);
		importPackage.setCustomPackage(customPackage);

		result.setLocalPackage(BaseInfo.getMapperInterfaceLocalPackage());
		result.setImportPackage(importPackage);
		result.setAcessModifier(ConstantInterfaceAccessModifier.PUBLIC);
		result.setName(BaseInfo.getMapperInterfaceName());
		result.setMethod(method);
		return result;
	}

}
